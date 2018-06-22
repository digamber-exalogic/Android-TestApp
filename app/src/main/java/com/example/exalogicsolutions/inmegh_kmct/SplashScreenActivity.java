package com.example.exalogicsolutions.inmegh_kmct;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Activities.BottomBarActivityAdmin;
import com.example.exalogicsolutions.inmegh_kmct.Database.PreferencesManger;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants;

import butterknife.BindView;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class SplashScreenActivity extends AppCompatActivity {

    /*@BindView(R.id.imgCapSplashScreen)
    ImageView imgCapSplashScreen;*/

    private ImageView imgCapSplashScreen;
    public static final int TIME_OUT = 2000;
    public Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //handler = new Handler();
        //getSupportActionBar().hide();

        imgCapSplashScreen = findViewById(R.id.imgCapSplashScreen);

        ObjectAnimator animation = ObjectAnimator.ofFloat(imgCapSplashScreen, "rotationY", 0.0f, 360f);
        animation.setDuration(3600);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();


    }

    @Override
    protected void onResume() {
        super.onResume();
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              /*  try {

                    if (playstoreVersion.compareTo(currentAppVersionCode) > 0) {
//Show update popup or whatever best for you
                        startActivity(new Intent(getApplicationContext(), SecondActivity.class));

                    } else*/
                {
//                  startActivity(new Intent(SplashScreenActivity.this, ClassTeacherLeaveTabActivity.class));
                    if (TextUtils.isEmpty(PreferencesManger.getStringFields(getApplicationContext(), Constants.Pref.KEY_TOKEN))) {
                        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    } else if (PreferencesManger.getStringFields(getApplicationContext(), Constants.Pref.KEY_USER_TYPE).equalsIgnoreCase("Admin")) {
                        /*startActivity(new Intent(getApplicationContext(), BottomBarActivityAdmin.class));*/
                        Toast.makeText(getApplicationContext(), "Permission Denied For Super Admin Access...", Toast.LENGTH_SHORT).show();
                    } else if (PreferencesManger.getStringFields(getApplicationContext(), Constants.Pref.KEY_USER_TYPE).equalsIgnoreCase("Branch Admin")) {
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
                }
               /* } catch (NullPointerException e) {
                    e.printStackTrace();
                }*/
                overridePendingTransition(R.anim.right_to_center, R.anim.center_to_left);
                finish();
            }
        }, TIME_OUT);
    }

}
