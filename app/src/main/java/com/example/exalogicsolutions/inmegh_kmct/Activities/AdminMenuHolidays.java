package com.example.exalogicsolutions.inmegh_kmct.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Adapters.HolidayAdapter;
import com.example.exalogicsolutions.inmegh_kmct.Api.RetrofitAPI;
import com.example.exalogicsolutions.inmegh_kmct.Models.HolidayResponse.HolidayDeleteResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.HolidayResponse.HolidayView;
import com.example.exalogicsolutions.inmegh_kmct.Models.HolidayResponse.HolidayViewStatus;
import com.example.exalogicsolutions.inmegh_kmct.R;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.UIUtil;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AdminMenuHolidays extends AppCompatActivity {

    private ActionBar toolbar;

    @BindView(R.id.etHolidaySearchBox)
    EditText etHolidaySearchBox;

    ArrayList<HolidayView> holidayList, searchArrayList;
    private Context mContext;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private HolidayAdapter holidayAdapter;
    private long fromDate1;

    private TextInputEditText etHolidayName, etStartDate, etEndDate, etDate;
    private Switch swtMultiDay;
    private FloatingActionButton flbtnUpdate, fbtnHolidayCreate;
    private MaterialButton btnChooseFile;
    String str_date;
    private int editpos;
    private int currentPosition;
    private TextInputLayout lyEndDate, lyStartDate, lyDate;
    private boolean isHalfDay;
    private Handler handler;
    public static final int TIME_OUT = 1000;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_holidays);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Holidays");
        // Request focus and show soft keyboard automatically
        ButterKnife.bind(this);
        /*getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);*/
        fbtnHolidayCreate = findViewById(R.id.fbtnHolidayCreate);
        etHolidaySearchBox = (EditText) findViewById(R.id.etHolidaySearchBox);
        holidayList = new ArrayList<>();
        handler = new Handler();
        searchArrayList = new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        holidayAdapter = new HolidayAdapter(this, holidayList, mContext);
        mRecyclerView.setAdapter(holidayAdapter);

/*
        holidayList.add(new HolidayView(holidayList.get(0).getmImageResorceId(), holidayList.get(0).getName(),holidayList.get(0).getStartDate(), holidayList.get(0).getmDivider(), holidayList.get(0).getEndDate(), holidayList.get(0).getmDeleteButton(), holidayList.get(0).getmEditButton()));
*/
        etHolidaySearchBox.addTextChangedListener(new TextWatcher() {
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
                        holidayList.clear();
                        holidayList.addAll(searchArrayList);
                        holidayAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getholidays();
        holidayAdapter.SetOnItemClickListener(new HolidayAdapter.OnItemClickListener() {
            @Override
            public void onEditHoliday(View view, int position) {
                editpos = position;
                oneditHoliday(editpos);

            }

            @Override
            public void onDeleteHoliday(View view, int position) {
                DeleteHoliday(position);
            }
        });

        fbtnHolidayCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onCreateHoliday();
            }
        });
    }

    private void filterSearch(String search) {
        try {
            holidayList.clear();
            for (int i = 0; i < searchArrayList.size(); i++) {
                HolidayView holidayView = searchArrayList.get(i);
                if (holidayView.getName().toLowerCase().contains(search.toLowerCase())) {
                    holidayList.add(holidayView);
                }
            }
            if (holidayList.size() <= 0) {
                etHolidaySearchBox.setError("No Record found");
            }
            holidayAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private AlertDialog onCreateHoliday() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View promptView = inflater.inflate(R.layout.holiday_create_dialogue_box, null);

        etDate = promptView.findViewById(R.id.etDate);
        etStartDate = promptView.findViewById(R.id.etStartDate);

        etEndDate = promptView.findViewById(R.id.etEndDate);

        swtMultiDay = promptView.findViewById(R.id.swtMultiDay);
        etHolidayName = promptView.findViewById(R.id.etHolidayName);
        lyEndDate = promptView.findViewById(R.id.lyEndDate);
        lyStartDate = promptView.findViewById(R.id.lyStartDate);
        lyDate = promptView.findViewById(R.id.lyDate);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        etHolidayName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                return false;
            }
        });


        swtMultiDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isHalfDay = isChecked;

                if (isChecked) {
                    lyStartDate.setVisibility(View.VISIBLE);
                    lyEndDate.setVisibility(View.VISIBLE);
                    lyDate.setVisibility(View.GONE);
                } else {
                    lyStartDate.setVisibility(View.GONE);
                    lyEndDate.setVisibility(View.GONE);
                    lyDate.setVisibility(View.VISIBLE);
                }
            }
        });
        etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFromDatePicker(false);
            }
        });
        etEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openToDatePicker();
            }
        });
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFromDatePicker(true);
            }
        });
        builder.setView(promptView)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        onCreate();
                        dialog.dismiss();
                    }
                });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return builder.create();
    }

    private AlertDialog oneditHoliday(final int pos) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View promptView = inflater.inflate(R.layout.holiday_edit_dialogue_box, null);
        final HolidayView holidayviewmodel = holidayList.get(pos);

        etDate = promptView.findViewById(R.id.etDate);
        etStartDate = promptView.findViewById(R.id.etStartDate);

        etEndDate = promptView.findViewById(R.id.etEndDate);

        swtMultiDay = promptView.findViewById(R.id.swtMultiDay);
        etHolidayName = promptView.findViewById(R.id.etHolidayName);
        lyEndDate = promptView.findViewById(R.id.lyEndDate);
        lyStartDate = promptView.findViewById(R.id.lyStartDate);
        lyDate = promptView.findViewById(R.id.lyDate);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        etHolidayName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                return false;
            }
        });


        etHolidayName.setText(holidayviewmodel.getName());
        if (holidayviewmodel.getSingleDay() == null) {
            etStartDate.setText(holidayviewmodel.getmStartDate().toString());
            str_date = holidayviewmodel.getmStartDate().toString();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date date = (Date) formatter.parse(str_date);
                fromDate1 = date.getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }
            etEndDate.setText(holidayviewmodel.getmStartDate().toString());
        } else {
            etDate.setText(holidayviewmodel.getSingleDay());
        }
        if (holidayList.get(editpos).getSingleDay() == null) {
            swtMultiDay.setChecked(true);
            lyStartDate.setVisibility(View.VISIBLE);
            lyEndDate.setVisibility(View.VISIBLE);
            lyDate.setVisibility(View.GONE);
        } else {
            swtMultiDay.setChecked(false);
            lyStartDate.setVisibility(View.GONE);
            lyEndDate.setVisibility(View.GONE);
            lyDate.setVisibility(View.VISIBLE);
        }
        swtMultiDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isHalfDay = isChecked;

                if (isChecked) {
                    lyStartDate.setVisibility(View.VISIBLE);
                    lyEndDate.setVisibility(View.VISIBLE);
                    lyDate.setVisibility(View.GONE);
                } else {
                    lyStartDate.setVisibility(View.GONE);
                    lyEndDate.setVisibility(View.GONE);
                    lyDate.setVisibility(View.VISIBLE);
                }
            }
        });
        etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFromDatePicker(false);
            }
        });
        etEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openToDatePicker();
            }
        });
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFromDatePicker(true);
            }
        });
        builder.setView(promptView)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        onUpdate();
                        dialog.dismiss();
                    }
                });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return builder.create();
    }

    private void getholidays() {
        try {
            RetrofitAPI.getInstance(this).getApi().holidays(new Callback<HolidayViewStatus>() {
                @Override
                public void success(HolidayViewStatus holidayviewstatusmodel, Response response) {
                    try {
                        if (holidayviewstatusmodel.getStatus() == Constants.SUCCESS) {
                            holidayList.clear();
                            searchArrayList.clear();
                            searchArrayList.addAll(holidayviewstatusmodel.getHolidays());
                            holidayList.addAll(holidayviewstatusmodel.getHolidays());
                            holidayAdapter.notifyDataSetChanged();
                        } else if (holidayviewstatusmodel.getStatus() == Constants.SWR) {
                            Toast.makeText(getApplicationContext(), "Something went wrong redirect to back ", Toast.LENGTH_SHORT).show();
                        } else if (holidayviewstatusmodel.getStatus() == Constants.NDF) {
                            Toast.makeText(getApplicationContext(), " NO Datafound ", Toast.LENGTH_SHORT).show();
                        } else if (holidayviewstatusmodel.getStatus() == Constants.CMF) {
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

    private void openFromDatePicker(final boolean isOneDay) {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                monthOfYear = monthOfYear + 1;

                String month, day;
                //Calendar myCalendar = Calendar.getInstance();
                if (monthOfYear < 10) {
                    month = "0" + String.valueOf(monthOfYear);
                } else {
                    month = String.valueOf(monthOfYear);
                }

                if (dayOfMonth < 10) {
                    day = "0" + String.valueOf(dayOfMonth);
                } else {
                    day = String.valueOf(dayOfMonth);
                }

                try {
                    String str_date = day + "-" + month + "-" + String.valueOf(year);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = (Date) formatter.parse(str_date);
                    fromDate1 = date.getTime();
                    System.out.println("Today is " + date.getTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (isOneDay) {
                    etDate.setText(day + "-" + month + "-" + String.valueOf(year));
                } else {
                    etStartDate.setText(day + "-" + month + "-" + String.valueOf(year));
                }
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(fromDate1);
        datePickerDialog.show();
    }

    private void openToDatePicker() {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                String month, day;
                monthOfYear = monthOfYear + 1;
                if (monthOfYear < 10) {
                    month = "0" + String.valueOf(monthOfYear);
                } else {
                    month = String.valueOf(monthOfYear);
                }

                if (dayOfMonth < 10) {
                    day = "0" + String.valueOf(dayOfMonth);
                } else {
                    day = String.valueOf(dayOfMonth);
                }


                Log.e("Date", " Text --" + day + "/" + month + "/" + String.valueOf(year));
                etEndDate.setText(day + "-" + month + "-" + String.valueOf(year));

//                edDOB.setText(String.valueOf(year) + "-" + month + "-" + day);
//                tvDob.setText(newDate.toString());

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.getDatePicker().setMinDate(fromDate1);

        datePickerDialog.show();
    }

    public void onUpdate() {
        try {
            if (UIUtil.isInternetAvailable(this)) {

                UIUtil.startProgressDialog(this, "Please Wait.. Getting Details");
                JsonObject jsonObject = new JsonObject();
                JsonObject jsonObject1 = new JsonObject();
                if (swtMultiDay.isChecked() == true) {
                    jsonObject.addProperty("start_date", etStartDate.getText().toString());
                    jsonObject.addProperty("end_date", etEndDate.getText().toString());
                    jsonObject.addProperty("is_vacation", true);
                } else if (swtMultiDay.isChecked() == false) {
                    jsonObject.addProperty("hdate", etDate.getText().toString());
                    jsonObject.addProperty("is_vacation", false);
                }
                jsonObject.addProperty("name", etHolidayName.getText().toString());
                jsonObject.addProperty("id", holidayList.get(editpos).getId());
                //jsonObject.addProperty("image", etDescription.getText().toString());


                jsonObject1.add("holiday", jsonObject);
                RetrofitAPI.getInstance(this).getApi().updateHoliday(jsonObject1, new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject jsonObj, Response response) {
                        try {
                            UIUtil.stopProgressDialog(getApplicationContext());

                            Log.e("Json ", "Hhd --- " + jsonObj.toString());
                            Log.e("Json ", "Response --- " + response.getBody());
                            holidayList.clear();
                            getholidays();
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

    private void DeleteHoliday(final int position) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        currentPosition = position;

        builder.setTitle("Confirmation");
        String message = "Are you sure  " + " ?";
        builder.setMessage(message);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /// TODO call Api here
                deleteHoliday();


            }
        });
        builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    private void deleteHoliday() {
        try {
            if (UIUtil.isInternetAvailable(this)) {

                UIUtil.startProgressDialog(this, "Please Wait.. Getting Details");

                Map<String, String> params = new HashMap<String, String>();
                params.put("id", String.valueOf(holidayList.get(currentPosition).getId()));

                RetrofitAPI.getInstance(this).getApi().holidaydelete(params, new Callback<HolidayDeleteResponse>() {
                    @Override
                    public void success(HolidayDeleteResponse jsonObj, Response response) {
                        try {
                            UIUtil.stopProgressDialog(getApplicationContext());
                            if (jsonObj.getStatus() == Constants.SUCCESS) {
                                getholidays();
                                UIUtil.stopProgressDialog(getApplicationContext());
                                Toast.makeText(getApplicationContext(), "Holiday Removed Successfully...", Toast.LENGTH_SHORT).show();
                                Log.e("Json ", "Hhd --- " + jsonObj.toString());
                                Log.e("Json ", "Response --- " + response.getBody());
                            } else if (jsonObj.getStatus() == Constants.SWR) {
                                Toast.makeText(getApplicationContext(), "Something went wrong redirect to back ", Toast.LENGTH_SHORT).show();
                            } else if (jsonObj.getStatus() == Constants.NDF) {
                                Toast.makeText(getApplicationContext(), " NO Datafound ", Toast.LENGTH_SHORT).show();
                            } else if (jsonObj.getStatus() == Constants.CMF) {
                                Toast.makeText(getApplicationContext(), "Check all the mandatory fields", Toast.LENGTH_SHORT).show();
                            }
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
                Toast.makeText(getApplicationContext(), "Please Connect to Internet", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCreate() {
        try {
            if (validateFields()) {
                if (UIUtil.isInternetAvailable(this)) {

                    UIUtil.startProgressDialog(this, "Please Wait.. Getting Details");
                    JsonObject jsonObject = new JsonObject();
                    JsonObject jsonObject1 = new JsonObject();
                    if (swtMultiDay.isChecked()) {
                        jsonObject.addProperty("start_date", etStartDate.getText().toString());
                        jsonObject.addProperty("end_date", etEndDate.getText().toString());
                        jsonObject.addProperty("is_vacation", true);
                    } else if (!swtMultiDay.isChecked()) {
                        jsonObject.addProperty("hdate", etDate.getText().toString());
                        jsonObject.addProperty("is_vacation", false);
                    }
                    jsonObject.addProperty("name", etHolidayName.getText().toString());
                    /*jsonObject.addProperty("id", holidayList.get(editpos).getId());*/
                    jsonObject1.add("holiday", jsonObject);
                    RetrofitAPI.getInstance(this).getApi().CreateHoliday(jsonObject1, new Callback<JsonObject>() {
                        @Override
                        public void success(JsonObject jsonObj, Response response) {
                            try {

                                UIUtil.stopProgressDialog(getApplicationContext());
                                getholidays();
                                Log.e("Json ", "Hhd --- " + jsonObj.toString());
                                Log.e("Json ", "Response --- " + response.getBody());

                                clearFieldData();
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFieldData() {
        etHolidayName.setText("");

    }

    private boolean validateFields() {

        if (etHolidayName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Enter Holiday", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
