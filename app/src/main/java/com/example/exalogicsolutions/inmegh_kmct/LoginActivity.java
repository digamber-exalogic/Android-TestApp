package com.example.exalogicsolutions.inmegh_kmct;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminDashboardActivity;
import com.example.exalogicsolutions.inmegh_kmct.Activities.BottomBarActivityAdmin;
import com.example.exalogicsolutions.inmegh_kmct.Api.RetrofitAPI;
import com.example.exalogicsolutions.inmegh_kmct.Database.PreferencesManger;
import com.example.exalogicsolutions.inmegh_kmct.Models.SignInResponse;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.UIUtil;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView loginButtonLayout;
    private ImageView logImage, visiblePassword, invisiblePassword;
    private AutoCompleteTextView etUserName;
    private TextInputEditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        logImage = findViewById(R.id.logImage);
        loginButtonLayout = findViewById(R.id.loginButtonLayout);
        visiblePassword = findViewById(R.id.visibilityOnImage);
        invisiblePassword = findViewById(R.id.visibilityOofImage);
        etUserName = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
       /* etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);*/
        etPassword.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);


        ObjectAnimator animation = ObjectAnimator.ofFloat(logImage, "rotationY", 0.0f, 360f);
        animation.setDuration(3600);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();

        loginButtonLayout.setOnClickListener((View.OnClickListener) this);

        invisiblePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int start, end;

                visiblePassword.setVisibility(View.VISIBLE);
                invisiblePassword.setVisibility(View.GONE);
                /*etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());*/
                start = etPassword.getSelectionStart();
                end=etPassword.getSelectionEnd();
                etPassword.setTransformationMethod(null);
                etPassword.setSelection(start,end);

            }
        });

        visiblePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int start, end;

                visiblePassword.setVisibility(View.GONE);
                invisiblePassword.setVisibility(View.VISIBLE);
                /*etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());*/
                /*etPassword.setTransformationMethod(new PasswordTransformationMethod());*/
                /*etPassword.setTransformationMethod(null);*/
                /*etPassword.setTransformationMethod(new PasswordTransformationMethod());*/
                start = etPassword.getSelectionStart();
                end=etPassword.getSelectionEnd();
                etPassword.setTransformationMethod(new PasswordTransformationMethod());
                etPassword.setSelection(start,end);
            }
        });

        etUserName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                // If the event is a key-down event on the "enter" button
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) ||
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        if (validateCredentials()) {
                            login();
                        }
                        // Toast.makeText(HelloFormStuff.this, edittext.getText(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                return false;
            }
        });

        etPassword.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                // If the event is a key-down event on the "enter" button
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) ||
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        if (validateCredentials()) {
                            login();
                        }
                        // Toast.makeText(HelloFormStuff.this, edittext.getText(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                return false;
            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButtonLayout:
                final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
                v.startAnimation(myAnim);
                if (validateCredentials())
                    login();
                break;
        }
    }

    private boolean validateCredentials() {

        if (etUserName.getText().toString().trim().isEmpty()) {
            etUserName.setError("Please Enter Username!");
            return false;
        }

        if (etPassword.getText().toString().trim().isEmpty()) {
            etPassword.setError("Please Enter Password!");
            return false;
        }

        return true;
    }

    private void login() {
        try {
            if (UIUtil.isInternetAvailable(this)) {
                UIUtil.startProgressDialog(this, "Trying Signing .....");

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("email", etUserName.getText().toString());
                jsonObject.addProperty("password", etPassword.getText().toString());
                //jsonObject.addProperty("devise_id", ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
                /*jsonObject.addProperty("registration_id", FirebaseInstanceId.getInstance().getToken());*/
                jsonObject.addProperty("mobile_os", "Android");

                Log.e("jsonObject", "jsonObject : " + jsonObject.toString());

                RetrofitAPI.getInstance(this).getApi().signIn(jsonObject, new Callback<SignInResponse>() {

                    @Override
                    public void success(SignInResponse jsonObject, Response response) {
                        try {
                            UIUtil.stopProgressDialog(getApplicationContext());

                            Log.e("Json ", "Hhd---" + jsonObject.toString());
                            Log.e("Json ", "response---" + response.getBody());

                            if (jsonObject.getStatus() == Constants.SUCCESS) {

                               /* Toast.makeText(getApplicationContext(), "Login successfully...", Toast.LENGTH_SHORT).show();*/

                                Log.e("Token", "-=-=-=-=-=----=-=-=-=-=-= jsonObject " + jsonObject.toString());

                                PreferencesManger.addStringFields(getApplicationContext(), Constants.Pref.KEY_USERNAME, jsonObject.getUserName());
                                PreferencesManger.addStringFields(getApplicationContext(), Constants.Pref.KEY_EMAIL, jsonObject.getEmail());
                                PreferencesManger.addStringFields(getApplicationContext(), Constants.Pref.KEY_TOKEN, jsonObject.getToken());
                                PreferencesManger.addStringFields(getApplicationContext(), Constants.Pref.KEY_USER_TYPE, jsonObject.getUserType());
                                if (PreferencesManger.getStringFields(getApplicationContext(), Constants.Pref.KEY_USER_TYPE).equalsIgnoreCase("Admin")) {
                                    /*startActivity(new Intent(getApplicationContext(), BottomBarActivityAdmin.class));*/
                                    Toast.makeText(getApplicationContext(), "Permission Denied For Super Admin Access...", Toast.LENGTH_SHORT).show();
                                }
                                if (PreferencesManger.getStringFields(getApplicationContext(), Constants.Pref.KEY_USER_TYPE).equalsIgnoreCase("Branch Admin")) {
                                    startActivity(new Intent(getApplicationContext(), BottomBarActivityAdmin.class));
                                    Toast.makeText(getApplicationContext(), "Login successfully...", Toast.LENGTH_SHORT).show();
                                }
                                /*if (PreferencesManger.getStringFields(getApplicationContext(), Constants.Pref.KEY_USER_TYPE).equalsIgnoreCase("Hod")) {
                                    startActivity(new Intent(getApplicationContext(), NewEmployeeLanding.class));
                                }
                                if (PreferencesManger.getStringFields(getApplicationContext(), Constants.Pref.KEY_USER_TYPE).equalsIgnoreCase("Student")) {
                                    startActivity(new Intent(getApplicationContext(), StudentLandingActivity.class));
                                }
                                if (PreferencesManger.getStringFields(getApplicationContext(), Constants.Pref.KEY_USER_TYPE).equalsIgnoreCase("Admin")) {
                                    startActivity(new Intent(getApplicationContext(), AdminLandingActivity.class));
                                }*/
                            } else if (jsonObject.getStatus() == Constants.SWR) {
                                Toast.makeText(getApplicationContext(), jsonObject.getMessage(), Toast.LENGTH_SHORT).show();
                            } else if (jsonObject.getStatus() == Constants.NDF) {
                                Toast.makeText(getApplicationContext(), jsonObject.getMessage(), Toast.LENGTH_SHORT).show();
                            } else if (jsonObject.getStatus() == Constants.CMF) {
                                Toast.makeText(getApplicationContext(), jsonObject.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), jsonObject.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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
}
