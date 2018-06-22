package com.example.exalogicsolutions.inmegh_kmct.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Api.RetrofitAPI;
import com.example.exalogicsolutions.inmegh_kmct.Database.PreferencesManger;
import com.example.exalogicsolutions.inmegh_kmct.LoginActivity;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.UIUtil;


import com.example.exalogicsolutions.inmegh_kmct.Fragments.AdminHomeFragment;
import com.example.exalogicsolutions.inmegh_kmct.Fragments.AdminSubstituteFragment;
import com.example.exalogicsolutions.inmegh_kmct.Fragments.AdminMenuFragment;
import com.example.exalogicsolutions.inmegh_kmct.Fragments.AdminNoticeFragment;
import com.example.exalogicsolutions.inmegh_kmct.Fragments.AdminEventFragment;
import com.example.exalogicsolutions.inmegh_kmct.R;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BottomBarActivityAdmin extends AppCompatActivity {

    private TextView mTextMessage;
    private ActionBar toolbar;
    /*BottomBar mBottomBar;*/

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Home");
                    AdminHomeFragment homeFragment = new AdminHomeFragment();
                    loadFragment(homeFragment);
                    return true;
                case R.id.navigation_substitute:
                    toolbar.setTitle("Substitute");
                    AdminSubstituteFragment substituteFragment = new AdminSubstituteFragment();
                    loadFragment(substituteFragment);

                    return true;
                case R.id.navigation_event:
                    toolbar.setTitle("Event");
                    AdminEventFragment eventFragment = new AdminEventFragment();
                    loadFragment(eventFragment);
                    return true;

                case R.id.navigation_notice:
                    toolbar.setTitle("Notice");
                    AdminNoticeFragment noticeFragment = new AdminNoticeFragment();
                    loadFragment(noticeFragment);
                    return true;

                case R.id.navigation_menu:
                    toolbar.setTitle("Menu");
                    AdminMenuFragment menuFragment = new AdminMenuFragment();
                    loadFragment(menuFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (item.getItemId()) {

            case R.id.logout:
                showLogoutConfirmation();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar_admin);

        /* getSupportActionBar().hide();*/

        toolbar = getSupportActionBar();

        // load the store fragment by default
        assert toolbar != null;
        toolbar.setTitle("Home");
        loadFragment(new AdminHomeFragment());

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void loadFragment(Fragment fragment) {

        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void showLogoutConfirmation() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirmation");
        String message = "Do you want to logout?";
        builder.setMessage(message);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
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

    private void logout() {
        try {
            if (UIUtil.isInternetAvailable(this)) {
                UIUtil.startProgressDialog(this, "Please Wait.. logging out..");

                RetrofitAPI.getInstance(this).getApi().logout(new Callback<JSONObject>() {
                    @Override
                    public void success(JSONObject object, Response response) {
                        try {
                            UIUtil.stopProgressDialog(getApplicationContext());
                            Log.e("API", "logout-" + object.toString());
                            Toast.makeText(getApplicationContext(), "Logout successfully..", Toast.LENGTH_SHORT).show();
                            PreferencesManger.clearPreferences(getApplicationContext());
/*                    SugarContext.terminate();
                    SchemaGenerator schemaGenerator = new SchemaGenerator(getApplicationContext());
                    schemaGenerator.deleteTables(new SugarDb(getApplicationContext()).getDB());
                    SugarContext.init(getApplicationContext());
                    schemaGenerator.createDatabase(new SugarDb(getApplicationContext()).getDB());*/
                            startActivity(new Intent(BottomBarActivityAdmin.this, LoginActivity.class));
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        PreferencesManger.clearPreferences(getApplicationContext());
                        UIUtil.stopProgressDialog(getApplicationContext());
                        Toast.makeText(getApplicationContext(), "Logout successfully..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BottomBarActivityAdmin.this, LoginActivity.class));
                        finish();
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
