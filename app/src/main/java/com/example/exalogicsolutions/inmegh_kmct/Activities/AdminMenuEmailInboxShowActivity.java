package com.example.exalogicsolutions.inmegh_kmct.Activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.exalogicsolutions.inmegh_kmct.Api.RetrofitAPI;
import com.example.exalogicsolutions.inmegh_kmct.Fragments.InboxFragment;
import com.example.exalogicsolutions.inmegh_kmct.Fragments.SentboxFragment;
import com.example.exalogicsolutions.inmegh_kmct.Fragments.TrashFragment;
import com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse.EmailReadResponse;
import com.example.exalogicsolutions.inmegh_kmct.R;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.UIUtil;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AdminMenuEmailInboxShowActivity extends AppCompatActivity {

    private CircleImageView imgEmailProfilePic;
    private TextView txSubjectName, txEmailBoxType, txProfileName,
            txSubject, txViewDetails, txHideView, txFromEmailId,
            txToEmailId, txDateValue, txTimeValue, txMessageBody, txTime, txSmallBody;
    private LinearLayout llHideDetails, llsuperDetailLayout;
    private ImageView imgEmailAttachment;
    private boolean status = true, clicked = false;
    private String id, name;
    /*private boolean doubleBackToExitPressedOnce = false;*/

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_email_inbox_show);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //XML Objects initialization
        imgEmailProfilePic = findViewById(R.id.imgEmailProfilePic);
        txSubjectName = findViewById(R.id.txSubjectName);
        txEmailBoxType = findViewById(R.id.txEmailBoxType);
        txProfileName = findViewById(R.id.txProfileName);
        txSubject = findViewById(R.id.txSubject);
        txViewDetails = findViewById(R.id.txViewDetails);
        txHideView = findViewById(R.id.txHideView);
        txFromEmailId = findViewById(R.id.txFromEmailId);
        txToEmailId = findViewById(R.id.txToEmailId);
        txDateValue = findViewById(R.id.txDateValue);
        txTimeValue = findViewById(R.id.txTimeValue);
        txMessageBody = findViewById(R.id.txMessageBody);
        llHideDetails = findViewById(R.id.llHideDetails);
        imgEmailAttachment = findViewById(R.id.imgEmailAttachment);
        txTime = findViewById(R.id.txTime);
        llsuperDetailLayout = findViewById(R.id.llsuperDetailLayout);
        txSmallBody = findViewById(R.id.txSmallBody);

        try {

            Intent intent = getIntent();
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            Log.e("name", "00000" + name);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (name.equalsIgnoreCase("inbox")) {
            txEmailBoxType.setText("Inbox");
        } else if (name.equalsIgnoreCase("sent")) {
            txEmailBoxType.setText("Sent");
        } else {
            txEmailBoxType.setText("Trash");
        }

        //Creating methods for view details
        txViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txViewDetails.setVisibility(View.GONE);
                txHideView.setVisibility(View.VISIBLE);
                llHideDetails.setVisibility(View.VISIBLE);
                txTime.setVisibility(View.GONE);
                txMessageBody.setVisibility(View.VISIBLE);
                txSmallBody.setVisibility(View.GONE);

                clicked = true;

            }
        });

        txHideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txViewDetails.setVisibility(View.VISIBLE);
                txHideView.setVisibility(View.GONE);
                llHideDetails.setVisibility(View.GONE);
                txTime.setVisibility(View.VISIBLE);
                txMessageBody.setVisibility(View.VISIBLE);
                txSmallBody.setVisibility(View.GONE);

                clicked = false;
            }
        });

        /*llsuperDetailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status) {

                    status = false;

                    txViewDetails.setVisibility(View.GONE);
                    txHideView.setVisibility(View.GONE);
                    llHideDetails.setVisibility(View.GONE);
                    imgEmailAttachment.setVisibility(View.GONE);
                    txSubject.setVisibility(View.GONE);
                    txSmallBody.setVisibility(View.VISIBLE);
                    txMessageBody.setVisibility(View.GONE);

                    Log.e("Status1 :", "---------"+status);


                } else if (!status) {

                    status = true;
                    txViewDetails.setVisibility(View.VISIBLE);
                    txHideView.setVisibility(View.GONE);
                    llHideDetails.setVisibility(View.GONE);
                    imgEmailAttachment.setVisibility(View.VISIBLE);
                    txSubject.setVisibility(View.VISIBLE);
                    txSmallBody.setVisibility(View.VISIBLE);
                    txMessageBody.setVisibility(View.VISIBLE);
                    Log.e("Status2 :", "---------"+status);
                }
            }
        });*/
        getMaildata();

    }

    private void inboxdeleteMessage() {
        try {
            if (UIUtil.isInternetAvailable(this)) {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);

                RetrofitAPI.getInstance(this).getApi().deleteMail(params, new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject object, Response response) {
                        try {
                            Log.e("jsonObject", "jsonObject --- " + object.toString());
                            getMaildata();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            } else {

                Toast.makeText(this, "Please Connect to Internet", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void trashdeleteMessage() {
        try {
            if (UIUtil.isInternetAvailable(this)) {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);

                RetrofitAPI.getInstance(this).getApi().trashdeleteMail(params, new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject object, Response response) {
                        try {
                            Log.e("jsonObject", "jsonObject --- " + object.toString());
                            getMaildata();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            } else {

                Toast.makeText(this, "Please Connect to Internet", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sentdeleteMessage() {
        try {
            if (UIUtil.isInternetAvailable(this)) {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);

                RetrofitAPI.getInstance(this).getApi().sentdeleteMail(params, new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject object, Response response) {
                        try {
                            Log.e("jsonObject", "jsonObject --- " + object.toString());
                            getMaildata();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            } else {

                Toast.makeText(this, "Please Connect to Internet", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getMaildata() {
        try {
            if (UIUtil.isInternetAvailable(this)) {
                UIUtil.startProgressDialog(this, "Please Wait.. Getting Details");
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);

                RetrofitAPI.getInstance(this).getApi().getReadMailDetails(params, new Callback<EmailReadResponse>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void success(EmailReadResponse emailReadResponse, Response response) {
                        try {
                            UIUtil.stopProgressDialog(getApplicationContext());
                            if (emailReadResponse == null)
                                return;
                            if (emailReadResponse.getStatus() == Constants.SUCCESS) {
                                txSubjectName.setText(emailReadResponse.getSubject());
                                txProfileName.setText(emailReadResponse.getSender());
                                txFromEmailId.setText(emailReadResponse.getSender_id());
                                txToEmailId.setText(emailReadResponse.getReceiver_ids());
                                Glide.with(getApplicationContext()).asBitmap().load(emailReadResponse.getProfile()).into(imgEmailProfilePic);
                                txMessageBody.setText(emailReadResponse.getBody());
                                Glide.with(getApplicationContext()).asBitmap().load(emailReadResponse.getAttachment()).into(imgEmailAttachment);
                                txDateValue.setText(emailReadResponse.getSent_date());
                                txTimeValue.setText(emailReadResponse.getSent_time());
                                txTime.setText(emailReadResponse.getTime());
                            } else if (emailReadResponse.getStatus() == Constants.SWR) {
                                Toast.makeText(getApplicationContext(), "Something went wrong redirect to back", Toast.LENGTH_SHORT).show();
                            } else if (emailReadResponse.getStatus() == Constants.NDF) {
                                Toast.makeText(getApplicationContext(), "NO Datafound", Toast.LENGTH_SHORT).show();
                            } else if (emailReadResponse.getStatus() == Constants.CMF) {
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

    private void onDeleteclickTrash() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        // set title
        alertDialogBuilder.setTitle("Confirmation");
        alertDialogBuilder
                .setMessage("Do You Want to Delete trash item")
                .setCancelable(false)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        trashdeleteMessage();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MailTabbedActivity.class));
                        /*Intent intent = new Intent(getApplicationContext(),MailTabbedActivity.class);
                        intent.putExtra("name","inbox");
                        startActivity(intent);*/
                        overridePendingTransition(R.anim.left_to_center, R.anim.center_to_right);

                    }
                })
                .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }


    private void onDeleteclickSent() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        // set title
        alertDialogBuilder.setTitle("Confirmation");
        alertDialogBuilder
                .setMessage("Do You Want to Delete sent item")
                .setCancelable(false)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        sentdeleteMessage();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MailTabbedActivity.class));
                        /*Intent intent = new Intent(getApplicationContext(),MailTabbedActivity.class);
                        intent.putExtra("name","sent");
                        startActivity(intent);*/
                        overridePendingTransition(R.anim.left_to_center, R.anim.center_to_right);

                    }
                })
                .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private void onDeleteclickInbox() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        // set title
        alertDialogBuilder.setTitle("Confirmation");
        alertDialogBuilder
                .setMessage("Do You Want to Delete inbox item")
                .setCancelable(false)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        inboxdeleteMessage();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MailTabbedActivity.class));
                        /*Intent intent = new Intent(getApplicationContext(),MailTabbedActivity.class);
                        intent.putExtra("name","inbox");
                        startActivity(intent);*/
                        overridePendingTransition(R.anim.left_to_center, R.anim.center_to_right);
                    }
                })
                .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu_button, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuEmailDelete:
                if (name.equalsIgnoreCase("trash")) {
                    onDeleteclickTrash();
                } else if (name.equalsIgnoreCase("sent")) {
                    onDeleteclickSent();
                } else if (name.equalsIgnoreCase("inbox")) {
                    onDeleteclickInbox();
                }
                return true;

            default:
                onBackPressed();
                return true;
        }

    }

}
