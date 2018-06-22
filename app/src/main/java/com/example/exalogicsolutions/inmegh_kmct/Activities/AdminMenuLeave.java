package com.example.exalogicsolutions.inmegh_kmct.Activities;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Adapters.AdminLeaveAdapter;
import com.example.exalogicsolutions.inmegh_kmct.Api.RetrofitAPI;
import com.example.exalogicsolutions.inmegh_kmct.Models.LeaveResponse.AdminLeaveApplication;
import com.example.exalogicsolutions.inmegh_kmct.Models.LeaveResponse.AdminLeaveResponse;
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

public class AdminMenuLeave extends AppCompatActivity {
    private RecyclerView recyclerViewLeave;
    private EditText etLeaveSearchBox;
    private TextInputEditText etRejectReason;
    private AdminLeaveAdapter adminLeaveAdapter;
    private ArrayList<AdminLeaveApplication> adminLeaveApplications, searchArrayList;
    private int rejectpos, approvepos;
    public static final int TIME_OUT = 1000;
    private Handler handler;

    private ActionBar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_leave);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Leave Applications");
        /*getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);*/

        etLeaveSearchBox = findViewById(R.id.etLeaveSearchBox);
        recyclerViewLeave = findViewById(R.id.recyclerViewLeave);

        recyclerViewLeave.setHasFixedSize(true);

        recyclerViewLeave.setLayoutManager(new LinearLayoutManager(this));

        handler = new Handler();

        adminLeaveApplications = new ArrayList<>();
        searchArrayList = new ArrayList<>();

        adminLeaveAdapter = new AdminLeaveAdapter(this, adminLeaveApplications);
        recyclerViewLeave.setAdapter(adminLeaveAdapter);
        adminLeaveAdapter.notifyDataSetChanged();
        /*getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);*/

        etLeaveSearchBox.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                return false;
            }
        });
        etLeaveSearchBox.addTextChangedListener(new TextWatcher() {
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
                    if (searchArrayList.size() > 0) {
                        adminLeaveApplications.clear();
                        adminLeaveApplications.addAll(searchArrayList);
                        adminLeaveAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getLeaveApplication();

        adminLeaveAdapter.SetOnItemClickListener(new AdminLeaveAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onApproveClick(View view, int position) {
                approvepos = position;
                onApproved();
            }

            @Override
            public void onRejectClick(View view, int position) {
                rejectpos = position;
                OnRejectbox();

            }
        });
    }

    private void filterSearch(String search) {
        try{ adminLeaveApplications.clear();
            for (int i = 0; i < searchArrayList.size(); i++) {
                AdminLeaveApplication adminleave = searchArrayList.get(i);
                if (adminleave.getAvailable().toLowerCase().contains(search.toLowerCase())
                        || adminleave.getEmpName().toLowerCase().contains(search.toLowerCase())
                        || adminleave.getCourseName().toLowerCase().contains(search.toLowerCase())
                        || adminleave.getLeaveType().toLowerCase().contains(search.toLowerCase())
                        || adminleave.getStartDate().toLowerCase().contains(search.toLowerCase())
                        || adminleave.getMaxCount().toLowerCase().contains(search.toLowerCase())
                        || adminleave.getReason().toLowerCase().contains(search.toLowerCase())) {
                    adminLeaveApplications.add(adminleave);
                }
            }
            if (adminLeaveApplications.size() <= 0) {
                etLeaveSearchBox.setError("No Record found");
            }
            adminLeaveAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }}

    private AlertDialog OnRejectbox() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View promptView = inflater.inflate(R.layout.leave_reject_dialogue, null);
        // final DisciplinaryShowAction disciplinaryShowAction = arrayList.get(pos);
        etRejectReason = promptView.findViewById(R.id.etRejectReason);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        etRejectReason.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                return false;
            }
        });
        builder.setView(promptView).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onsubmitforReject();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return builder.create();

    }

    private void getLeaveApplication() {
        try {
            RetrofitAPI.getInstance(this).getApi().getAdminLeaveDetails(new Callback<AdminLeaveResponse>() {
                @Override
                public void success(AdminLeaveResponse adminLeaveResponse, Response response) {
                    try {
                        if (adminLeaveResponse.getStatus() == Constants.SUCCESS) {
                            adminLeaveApplications.clear();
                            searchArrayList.clear();
                            adminLeaveApplications.addAll(adminLeaveResponse.getLeaveApplications());
                            searchArrayList.addAll(adminLeaveResponse.getLeaveApplications());
                            adminLeaveAdapter.notifyDataSetChanged();

                        } else if (adminLeaveResponse.getStatus() == Constants.SWR) {
                            Toast.makeText(getApplicationContext(), "Something went wrong redirect to back ", Toast.LENGTH_SHORT).show();
                        } else if (adminLeaveResponse.getStatus() == Constants.NDF) {
                            Toast.makeText(getApplicationContext(), " NO Datafound ", Toast.LENGTH_SHORT).show();
                        } else if (adminLeaveResponse.getStatus() == Constants.CMF) {
                            Toast.makeText(getApplicationContext(), "Check all the mandatory fields", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onApproved() {
        try {
            if (UIUtil.isInternetAvailable(this)) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", adminLeaveApplications.get(approvepos).getId().toString());
                RetrofitAPI.getInstance(this).getApi().adminleaveapproved(params, new Callback<AdminLeaveResponse>() {
                    @Override
                    public void success(AdminLeaveResponse jsonObject1, Response response) {
                        try {
                            getLeaveApplication();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        UIUtil.stopProgressDialog(getApplicationContext());
                        Toast.makeText(getApplicationContext(), "Something went wrong, try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Please Connect to Internet", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onsubmitforReject() {
        try {
            if (UIUtil.isInternetAvailable(this)) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", adminLeaveApplications.get(rejectpos).getId().toString());
                params.put("manager_remarks", etRejectReason.getText().toString());
                RetrofitAPI.getInstance(this).getApi().adminleaveReject(params, new Callback<AdminLeaveResponse>() {
                    @Override
                    public void success(AdminLeaveResponse jsonObject1, Response response) {
                        try {
                            getLeaveApplication();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        UIUtil.stopProgressDialog(getApplicationContext());
                        Toast.makeText(getApplicationContext(), "Something went wrong, try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Please Connect to Internet", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
