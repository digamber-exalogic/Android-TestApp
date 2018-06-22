package com.example.exalogicsolutions.inmegh_kmct.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Adapters.AdminEventViewAdapter;
import com.example.exalogicsolutions.inmegh_kmct.Adapters.AdminNoticeBoardAdapter;
import com.example.exalogicsolutions.inmegh_kmct.Api.RetrofitAPI;
import com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.Adminevents;
import com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.Department;
import com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.ShowEvent.ShowImage;
import com.example.exalogicsolutions.inmegh_kmct.Models.NoticeBoardResponse.AdminNoticeResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.NoticeBoardResponse.NoticeBoard;
import com.example.exalogicsolutions.inmegh_kmct.R;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.UIUtil;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminNoticeFragment extends Fragment {

    private EditText etNotice, etMessage;
    private Spinner spinnerDepartment;
    private RecyclerView rvNoticBoard;
    private TextView btnCreate, tv_nodata, headingtv;
    private ArrayList<NoticeBoard> responseArraylist;
    private AdminNoticeBoardAdapter adminNoticeBoardAdapter;
    private ArrayAdapter<String> departmentAdapter;
    private ArrayAdapter<String> departmentAdapter1;
    private ArrayList<Department> departmentResponseArrayList;
    private ArrayList<Department> departmentResponseArrayList1;
    private ArrayList<String> departmentArraylist;
    private ArrayList<String> departmentArraylist1;
    private String depart, department, depart1, department1;
    private final AdminNoticeFragment context = this;


    public AdminNoticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_notice, container, false);

        tv_nodata = view.findViewById(R.id.txNoData);
        rvNoticBoard = view.findViewById(R.id.recyclerViewNotice);

        departmentArraylist = new ArrayList();
        departmentArraylist1 = new ArrayList();

        responseArraylist = new ArrayList<>();
        rvNoticBoard.setHasFixedSize(true);
        rvNoticBoard.setLayoutManager(new LinearLayoutManager(getContext()));
        adminNoticeBoardAdapter = new AdminNoticeBoardAdapter(getActivity(), responseArraylist);
        rvNoticBoard.setAdapter(adminNoticeBoardAdapter);

        departmentResponseArrayList = new ArrayList<>();
        departmentResponseArrayList1 = new ArrayList<>();
        getNoticeBoard();

        // Inflate the layout for this fragment
        return view;
    }

    private void getNoticeBoard() {
        try {
            if (UIUtil.isInternetAvailable(getContext())) {

                //   UIUtil.startProgressDialog(this, "Please Wait.. Getting Details");
                RetrofitAPI.getInstance(getContext()).getApi().getNoticeBoard(new Callback<AdminNoticeResponse>() {
                    @Override
                    public void success(AdminNoticeResponse noticeBoardResponse, Response response) {

                        Log.e("Json ", "Hhd --- " + noticeBoardResponse.toString());
                        Log.e("Json ", "Response --- " + response.getBody());
                        try {
                            if (noticeBoardResponse.getStatus() == Constants.SUCCESS) {
                                //  UIUtil.stopProgressDialog(getApplicationContext());
                                responseArraylist.clear();
                                responseArraylist.addAll(noticeBoardResponse.getNoticeBoards());
                                if (responseArraylist.size() <= 0) {
                                    rvNoticBoard.setVisibility(View.GONE);
                                    tv_nodata.setVisibility(View.VISIBLE);
                                } else {
                                    rvNoticBoard.setVisibility(View.VISIBLE);
                                    tv_nodata.setVisibility(View.GONE);
                                }
                                adminNoticeBoardAdapter.notifyDataSetChanged();

                            } else if (noticeBoardResponse.getStatus() == Constants.SWR) {
                                rvNoticBoard.setVisibility(View.GONE);
                                tv_nodata.setVisibility(View.VISIBLE);
                                Toast.makeText(getContext(), "Something went wrong redirect to back ", Toast.LENGTH_SHORT).show();
                            } else if (noticeBoardResponse.getStatus() == Constants.NDF) {
                                rvNoticBoard.setVisibility(View.GONE);
                                tv_nodata.setVisibility(View.VISIBLE);
                                Toast.makeText(getContext(), " NO Datafound ", Toast.LENGTH_SHORT).show();
                            } else if (noticeBoardResponse.getStatus() == Constants.CMF) {
                                rvNoticBoard.setVisibility(View.GONE);
                                tv_nodata.setVisibility(View.VISIBLE);
                                Toast.makeText(getContext(), "Check all the mandatory fields", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.e("Json ", "Hhd --- " + noticeBoardResponse.toString());
                        Log.e("Json ", "Response --- " + response.getBody());
                        Log.e("spinner1132", "----" + responseArraylist.toString());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // UIUtil.stopProgressDialog(getApplicationContext());
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
