package com.example.exalogicsolutions.inmegh_kmct.Fragments;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuEmailInboxShowActivity;
import com.example.exalogicsolutions.inmegh_kmct.Adapters.EmailAdapters.SentAdapter;
import com.example.exalogicsolutions.inmegh_kmct.Api.RetrofitAPI;
import com.example.exalogicsolutions.inmegh_kmct.Helper.RecyclerItemTouchHelperListener;
import com.example.exalogicsolutions.inmegh_kmct.Helper.RecyclerItemTouchHelperSentbox;
import com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse.MailResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse.SentEmail;
import com.example.exalogicsolutions.inmegh_kmct.R;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.UIUtil;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SentboxFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RecyclerItemTouchHelperListener, SentAdapter.ClickListener {

    private ArrayList<SentEmail> sentboxArrayList;
    private SentAdapter sentboxAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe_refresh_layout;
    private TextView txNoData;
    private ConstraintLayout rootLayout;
    private Handler handler = new Handler();
    private int sentpos;


    public SentboxFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mail_sentbox, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        swipe_refresh_layout = view.findViewById(R.id.swipe_refresh_layout);
        txNoData = view.findViewById(R.id.txNoData);
        rootLayout = view.findViewById(R.id.constraintLayout);

        recyclerView.setHasFixedSize(true);
        swipe_refresh_layout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        sentboxArrayList = new ArrayList<>();
        sentboxAdapter = new SentAdapter(getActivity(), sentboxArrayList);
        sentboxAdapter.setClickListener(this);
        recyclerView.setAdapter(sentboxAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new RecyclerItemTouchHelperSentbox(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        getSentdetail();

        swipe_refresh_layout.post(new Runnable() {
            @Override
            public void run() {

                getSentdetail();
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void getSentdetail() {
        try {
            if (UIUtil.isInternetAvailable(Objects.requireNonNull(getContext()))) {

                swipe_refresh_layout.setRefreshing(true);

                /*UIUtil.startProgressDialog(getContext(), "Please Wait.. Getting Details");*/
                RetrofitAPI.getInstance(getContext()).getApi().getMaildetails(new Callback<MailResponse>() {
                    @Override
                    public void success(MailResponse mailResponse, retrofit.client.Response response) {
                        try {
                            UIUtil.stopProgressDialog(getContext());

                            Log.e("Json ", "Hhd --- " + mailResponse.toString());
                            Log.e("Json ", "Response --- " + response.getBody());

                            if (mailResponse.getStatus() == Constants.SUCCESS) {
                                sentboxArrayList.clear();
                                sentboxArrayList.addAll(mailResponse.getSentLists());
                                sentboxAdapter.notifyDataSetChanged();
                                swipe_refresh_layout.setRefreshing(false);

                            } else if (mailResponse.getStatus() == Constants.SWR) {

                                txNoData.setText(View.VISIBLE);
                                Toast.makeText(getContext(), "Something went wrong redirect to back", Toast.LENGTH_SHORT).show();
                                swipe_refresh_layout.setRefreshing(false);
                            } else if (mailResponse.getStatus() == Constants.NDF) {

                                txNoData.setText(View.VISIBLE);
                                Toast.makeText(getContext(), "NO Datafound", Toast.LENGTH_SHORT).show();
                                swipe_refresh_layout.setRefreshing(false);
                            } else if (mailResponse.getStatus() == Constants.CMF) {

                                txNoData.setText(View.VISIBLE);
                                Toast.makeText(getContext(), " Check all the mandatory fields", Toast.LENGTH_SHORT).show();
                                swipe_refresh_layout.setRefreshing(false);
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.e("Json ", "Hhd --- " + mailResponse.toString());
                        Log.e("Json ", "Response --- " + response.getBody());

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        UIUtil.stopProgressDialog(getContext());
                        swipe_refresh_layout.setRefreshing(false);
                    }
                });
            } else {
                Toast.makeText(getContext(), "Please Connect to Internet", Toast.LENGTH_SHORT).show();
                swipe_refresh_layout.setRefreshing(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteSent() {
        try {
            if (UIUtil.isInternetAvailable(getContext())) {
                swipe_refresh_layout.setRefreshing(true);

                Map<String, String> params = new HashMap<>();
                params.put("id", sentboxArrayList.get(sentpos).getId().toString());

                RetrofitAPI.getInstance(getContext()).getApi().sentdeleteMail(params, new Callback<JsonObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void success(JsonObject object, Response response) {
                        try {
                            Log.e("jsonObject", "jsonObject --- " + object.toString());
                            getSentdetail();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        swipe_refresh_layout.setRefreshing(false);

                    }
                });
            } else {

                Toast.makeText(getContext(), "Please Connect to Internet", Toast.LENGTH_SHORT).show();
                swipe_refresh_layout.setRefreshing(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onRefresh() {
        getSentdetail();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof SentAdapter.MyViewHolder) {

            String name = sentboxArrayList.get(viewHolder.getAdapterPosition()).getSender();
            final SentEmail deleteItem = sentboxArrayList.get(viewHolder.getAdapterPosition());
            final int deleteIndex = viewHolder.getAdapterPosition();
            sentpos = deleteIndex;
            deleteSent();
            sentboxAdapter.removeItem(deleteIndex);

            Log.e("pos1", "----" + position);
            Log.e("pos2", "----" + deleteIndex);
            Snackbar snackbar = Snackbar.make(rootLayout, "Mail removed from sentbox", Snackbar.LENGTH_LONG);
            /*snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sentboxAdapter.restoreItem(deleteItem, deleteIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);*/
            snackbar.show();
        }

    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent =new Intent(getActivity(),AdminMenuEmailInboxShowActivity.class);
        intent.putExtra("name","sent");
        intent.putExtra("id",sentboxArrayList.get(position).getId().toString());
        startActivity(intent);
    }
}
