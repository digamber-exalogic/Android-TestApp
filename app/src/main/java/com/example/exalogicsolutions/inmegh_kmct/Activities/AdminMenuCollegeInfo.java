package com.example.exalogicsolutions.inmegh_kmct.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Api.RetrofitAPI;

import com.example.exalogicsolutions.inmegh_kmct.Models.CollegeInfoResponse.CollegeInfo;
import com.example.exalogicsolutions.inmegh_kmct.Models.CollegeInfoResponse.InfoResponse;
import com.example.exalogicsolutions.inmegh_kmct.R;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.UIUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants.context;

public class AdminMenuCollegeInfo extends AppCompatActivity {

    private ActionBar toolbar;

    @BindView(R.id.imgCollegeInfo)
    ImageView imgCollegeInfo;

    @BindView(R.id.txCollegeInfoName)
    TextView txCollegeInfoName;

    @BindView(R.id.txCollegeInfoAlias)
    TextView txCollegeInfoAlias;

    @BindView(R.id.txCollegeInfoAddress1)
    TextView txCollegeInfoAddress1;

    @BindView(R.id.txCollegeInfoAddress2)
    TextView txCollegeInfoAddress2;

    @BindView(R.id.txCollegeInfoCountry)
    TextView txCollegeInfoCountry;

    @BindView(R.id.txCollegeInfoState)
    TextView txCollegeInfoState;

    @BindView(R.id.txCollegeInfoCity)
    TextView txCollegeInfoCity;

    @BindView(R.id.txCollegeInfoPincode)
    TextView txCollegeInfoPincode;

    @BindView(R.id.txCollegeInfoEmail)
    TextView txCollegeInfoEmail;

    @BindView(R.id.txCollegeInfoWebsite)
    TextView txCollegeInfoWebsite;

    @BindView(R.id.txCollegeInfoPhone)
    TextView txCollegeInfoPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_college_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        toolbar = getSupportActionBar();
        // load the store fragment by default
        toolbar.setTitle("College Info");

        getCollegeInfo();
        /*Log.i("myModule_list3", String.valueOf(myModule_list));*/

    }

    private void bindData(InfoResponse infoResponse) {
        try {

            txCollegeInfoName.setText(infoResponse.getName());
            txCollegeInfoAlias.setText(infoResponse.getCode());
            txCollegeInfoAddress1.setText(infoResponse.getAddressLine1());
            txCollegeInfoAddress2.setText(infoResponse.getAddressLine2());
            txCollegeInfoCountry.setText(infoResponse.getCountry());
            txCollegeInfoState.setText(infoResponse.getState());
            txCollegeInfoCity.setText(infoResponse.getCity());
            txCollegeInfoPincode.setText(infoResponse.getPincode());
            txCollegeInfoEmail.setText(infoResponse.getEmail());
            txCollegeInfoWebsite.setText(infoResponse.getWebsite());
            txCollegeInfoPhone.setText(infoResponse.getPhone());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCollegeInfo() {
        try {
            if (UIUtil.isInternetAvailable(this)) {
                UIUtil.startProgressDialog(this, "Please Wait.. Getting Details");


                RetrofitAPI.getInstance(this).getApi().getCollegeInfo(new Callback<CollegeInfo>() {
                    @Override
                    public void success(CollegeInfo collegeInfo, Response response) {
                        try {
                            UIUtil.stopProgressDialog(getApplicationContext());
                            if (collegeInfo.getStatus() == Constants.SUCCESS) {
                                bindData(collegeInfo.getResponse());
                                Picasso.get().load(collegeInfo.getInstitutionImg()).resize(400, 80).into(imgCollegeInfo);
                            } else if (collegeInfo.getStatus() == Constants.SWR) {
                                Toast.makeText(getApplicationContext(), "Something went wrong redirect to back", Toast.LENGTH_SHORT).show();
                            } else if (collegeInfo.getStatus() == Constants.NDF) {
                                Toast.makeText(getApplicationContext(), "NO Datafound", Toast.LENGTH_SHORT).show();
                            } else if (collegeInfo.getStatus() == Constants.CMF) {
                                Toast.makeText(getApplicationContext(), " Check all the mandatory fields", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        UIUtil.stopProgressDialog(getApplicationContext());

                        Toast.makeText(getApplicationContext(), "Server is not responding, Try after some time.", Toast.LENGTH_SHORT).show();

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
