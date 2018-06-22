package com.example.exalogicsolutions.inmegh_kmct.Activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Adapters.EmailAdapters.ComposeAdapter;
import com.example.exalogicsolutions.inmegh_kmct.Api.RetrofitAPI;
import com.example.exalogicsolutions.inmegh_kmct.Database.PreferencesManger;
import com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse.ComposemailResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse.UserEmail;
import com.example.exalogicsolutions.inmegh_kmct.R;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.UIUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.Objects;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AdminMenuEmailCompose extends AppCompatActivity {

    private ActionBar toolbar;

    private ComposeAdapter composeAdapter;
    private ArrayList<UserEmail> composeArrayList;

    private RecyclerView emailrv;

    private int spinpos;
    private ArrayList<String> items;
    private CheckBox allCb;
    private EditText etSubject, etdata, etTo;
    private ImageView etAttachment;
    private JsonArray allStudents;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_email_compose);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Compose");
        allStudents=new JsonArray();
        emailrv = (RecyclerView) findViewById(R.id.emailrv);
        allCb = findViewById(R.id.chbEmailAll);
        etSubject = findViewById(R.id.etEmailSubject);
        etdata = findViewById(R.id.etEmailBody);
        etTo = findViewById(R.id.etEmailTo);
        composeArrayList = new ArrayList<>();
        items = new ArrayList<>();
        composeAdapter = new ComposeAdapter(this, composeArrayList);
        emailrv.setHasFixedSize(true);
        emailrv.setLayoutManager(new LinearLayoutManager(this));
        emailrv.setAdapter(composeAdapter);

        getComposedetail();
        etSubject.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                return false;
            }
        });

        etTo.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                return false;
            }
        });

        etdata.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                return false;
            }
        });


        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        allCb.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         items.clear();
                                         allStudents=new JsonArray();
                                         if (allCb.isChecked()) {
                                             for (int i = 0; i < composeArrayList.size(); i++) {
                                                 composeArrayList.get(i).setCheck_box(1);
                                                 allStudents.add(new JsonPrimitive(composeArrayList.get(spinpos).getId().toString()));
                                             }
                                             stringpass();
                                             composeAdapter.notifyDataSetChanged();

                                         } else {
                                             for (int i = 0; i < composeArrayList.size(); i++) {
                                                 composeArrayList.get(i).setCheck_box(0);
                                                 allStudents.remove(new JsonPrimitive(composeArrayList.get(spinpos).getId().toString()));
                                             }
                                             etTo.setText("");
                                             composeAdapter.notifyDataSetChanged();
                                         }
                                     }


                                 }

        );
        composeAdapter.SetOnItemClickListener(new ComposeAdapter.OnItemClickListener()

                                              {
                                                  @Override
                                                  public void onItemClick(View view, int position) {

                                                  }

                                                  @Override
                                                  public void onCheckClick(View view, int position) {
                                                      spinpos = position;
                                                      if (((CheckBox) view).isChecked()) {
                                                          composeArrayList.get(position).setCheck_box(1);
                                                          items.add(composeArrayList.get(spinpos).getId());
                                                          allStudents.add(new JsonPrimitive(composeArrayList.get(spinpos).getId().toString()));
                                                          System.out.println("selected checkboxes" + items);
                                                          etTo.setText(items.toString());

                                                      } else {
                                                          composeArrayList.get(position).setCheck_box(0);
                                                          items.remove(composeArrayList.get(spinpos).getId());
                                                          allStudents.remove(new JsonPrimitive(composeArrayList.get(spinpos).getId().toString()));
                                                          System.out.println("un selected checkboxes" + items);
                                                          if (items.size() <= 0) {
                                                              etTo.setText("");
                                                          } else {
                                                              etTo.setText(items.toString());
                                                          }

                                                      }
                                                  }
                                              }

        );
    }

    private boolean validateFields() {

        if (etTo.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Select Items which is present in the list", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private String stringpass() {
        String numbers = "";

        for (int i = 0; i < composeArrayList.size(); i++) {
            numbers = numbers + composeArrayList.get(i).getId() + ",";
            items.add(composeArrayList.get(i).getId());

        }
        Log.e("Strimnf", "=========" + numbers);
        Log.e("items", "=========" + items);
        etTo.setText(items.toString());
        return numbers;

    }

    private void sendData() {
        try {
            if (UIUtil.isInternetAvailable(this)) {
                if (validateFields()) {
                    UIUtil.startProgressDialog(this, "Please Wait.. Getting Details");

                    JsonObject jsonObject = new JsonObject();
                    JsonObject jsonObject1 = new JsonObject();
                    jsonObject.addProperty("sender_id", PreferencesManger.getStringFields(getApplicationContext(), Constants.Pref.KEY_EMAIL));
                    // jsonObject.addProperty("receiver_id", items.toString());
                    jsonObject.add("receiver_id", allStudents);
                    jsonObject.addProperty("subject", etSubject.getText().toString());
                    jsonObject.addProperty("body", etdata.getText().toString());

                    jsonObject1.add("email", jsonObject);
                    RetrofitAPI.getInstance(this).getApi().OnSend(jsonObject1, new Callback<JsonObject>() {
                        @Override
                        public void success(JsonObject object, Response response) {
                            Log.e("jsonObject", "jsonObject --- " + object.toString());
                            UIUtil.stopProgressDialog(getApplicationContext());
                            finish();
                            startActivity(new Intent(getApplicationContext(), AdminMenuMail.class));
                            overridePendingTransition(R.anim.left_to_center, R.anim.center_to_right);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            UIUtil.stopProgressDialog(getApplicationContext());
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

    private void getComposedetail() {
        try {
            if (UIUtil.isInternetAvailable(this)) {

                UIUtil.startProgressDialog(this, "Please Wait.. Getting Details");
                RetrofitAPI.getInstance(this).getApi().getComposedetails(new Callback<ComposemailResponse>() {
                    @Override
                    public void success(ComposemailResponse composemailResponse, retrofit.client.Response response) {
                        UIUtil.stopProgressDialog(getApplicationContext());
                        try {
                            Log.e("Json ", "Hhd --- " + composemailResponse.toString());
                            Log.e("Json ", "Response --- " + response.getBody());

                            if (composemailResponse.getStatus() == Constants.SUCCESS) {
                                composeArrayList.clear();
                                composeArrayList.addAll(composemailResponse.getUserEmails());
                                Log.e("array","-----"+composeArrayList);
                                composeAdapter.notifyDataSetChanged();

                            } else if (composemailResponse.getStatus() == Constants.SWR) {
                                Toast.makeText(getApplicationContext(), "Something went wrong redirect to back", Toast.LENGTH_SHORT).show();
                            } else if (composemailResponse.getStatus() == Constants.NDF) {
                                Toast.makeText(getApplicationContext(), "NO Datafound", Toast.LENGTH_SHORT).show();
                            } else if (composemailResponse.getStatus() == Constants.CMF) {
                                Toast.makeText(getApplicationContext(), " Check all the mandatory fields", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.e("Json ", "Hhd --- " + composemailResponse.toString());
                        Log.e("Json ", "Response --- " + response.getBody());

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        UIUtil.stopProgressDialog(getApplicationContext());
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
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.email_compose, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuEmailAttach:
                Toast.makeText(this, "Comming Soon...", Toast.LENGTH_SHORT)
                        .show();
                return true;

            case R.id.menuEmailSend:
                sendData();
                return true;

            default:
                onBackPressed();
                return true;
        }

    }

}
