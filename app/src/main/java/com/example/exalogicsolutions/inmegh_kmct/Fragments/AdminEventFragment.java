package com.example.exalogicsolutions.inmegh_kmct.Fragments;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Adapters.AdminEventViewAdapter;
import com.example.exalogicsolutions.inmegh_kmct.Api.RetrofitAPI;
import com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.AdminEventViewResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.Adminevents;
import com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.Department;
import com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.ShowEvent.ShowEvent;
import com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.ShowEvent.ShowImage;
import com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.ShowEvent.ShowResponse;
import com.example.exalogicsolutions.inmegh_kmct.R;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.UIUtil;

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
public class AdminEventFragment extends Fragment {

    private RecyclerView rveventlist;
    private EditText etSearch;
    private TextView nodata;
    private Handler handler;
    private ArrayList<Adminevents> searchArraylist, eventviewArraylist;
    private AdminEventViewAdapter adminEventViewAdapter;
    public static final int TIME_OUT = 1000;


    public AdminEventFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_event, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        rveventlist = (RecyclerView)view.findViewById(R.id.recyclerViewEvent);

        etSearch = view.findViewById(R.id.etEventSearchBox);

        nodata = view.findViewById(R.id.txNoData);
        rveventlist.setHasFixedSize(true);
        rveventlist.setLayoutManager(new LinearLayoutManager(getActivity()));

        handler = new Handler();
        // imageArrayList = new ArrayList<>();

        searchArraylist = new ArrayList<>();
        eventviewArraylist = new ArrayList();

        adminEventViewAdapter = new AdminEventViewAdapter(getActivity(), eventviewArraylist);
        rveventlist.setAdapter(adminEventViewAdapter);

        adminEventViewAdapter.notifyDataSetChanged();

        getEventView();
        /*getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);*/


        etSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                return false;
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {

                if (!TextUtils.isEmpty(s) && s.length() > 2) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            filterSearch(s.toString());
                        }
                    }, TIME_OUT);
                } else {
                    if (searchArraylist.size() > 0) {
                        eventviewArraylist.clear();
                        eventviewArraylist.addAll(searchArraylist);
                        adminEventViewAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        // Inflate the layout for this fragment
        return view;
    }

    private void filterSearch(String search) {
        try {
            eventviewArraylist.clear();
            for (int i = 0; i < searchArraylist.size(); i++) {
                Adminevents adminevents = searchArraylist.get(i);
                if (adminevents.getName().toLowerCase().contains(search.toLowerCase()) ||
                        adminevents.getStartDate().toLowerCase().contains(search.toLowerCase())) {
                    eventviewArraylist.add(adminevents);
                }
            }
            if (eventviewArraylist.size() <= 0) {
                etSearch.setError("No Record found");
            }
            adminEventViewAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void getEventView() {
        try {
            if (UIUtil.isInternetAvailable(Objects.requireNonNull(getContext()))) {

                UIUtil.startProgressDialog(getContext(), "Please Wait.. Getting Details");
                Map<String, String> params = new HashMap<String, String>();
                RetrofitAPI.getInstance(getContext()).getApi().getEventViewList(params, new Callback<AdminEventViewResponse>() {
                    @Override
                    public void success(AdminEventViewResponse adminEventViewResponse, Response response) {

                        Log.e("Json ", "Hhd --- " + adminEventViewResponse.toString());
                        Log.e("Json ", "Response --- " + response.getBody());
                        try {
                            if (adminEventViewResponse.getStatus() == Constants.SUCCESS) {
                                UIUtil.stopProgressDialog(getContext());
                                eventviewArraylist.clear();
                                searchArraylist.clear();

                                searchArraylist.addAll(adminEventViewResponse.getEvents());
                                eventviewArraylist.addAll(adminEventViewResponse.getEvents());
                                for (int i = 0; i < eventviewArraylist.size(); i++) {

                                }
                                Log.e("daata", "--------" + eventviewArraylist.toString());
                                if (eventviewArraylist.size() <= 0) {
                                    rveventlist.setVisibility(View.GONE);
                                    nodata.setVisibility(View.VISIBLE);
                                } else {
                                    rveventlist.setVisibility(View.VISIBLE);
                                    nodata.setVisibility(View.GONE);
                                }
                                adminEventViewAdapter.notifyDataSetChanged();

                            } else if (adminEventViewResponse.getStatus() == Constants.SWR) {
                                UIUtil.stopProgressDialog(getContext());
                                nodata.setVisibility(View.VISIBLE);
                                rveventlist.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "Something went wrong redirect to back ", Toast.LENGTH_SHORT).show();
                            } else if (adminEventViewResponse.getStatus() == Constants.NDF) {
                                UIUtil.stopProgressDialog(getContext());
                                nodata.setVisibility(View.VISIBLE);
                                rveventlist.setVisibility(View.GONE);
                                Toast.makeText(getContext(), " NO Datafound ", Toast.LENGTH_SHORT).show();
                            } else if (adminEventViewResponse.getStatus() == Constants.CMF) {
                                UIUtil.stopProgressDialog(getContext());
                                nodata.setVisibility(View.VISIBLE);
                                rveventlist.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "Check all the mandatory fields", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        UIUtil.stopProgressDialog(getContext());
                        Toast.makeText(getContext(), "Something went wrong, try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(getContext(), "Please Connect to Internet", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
