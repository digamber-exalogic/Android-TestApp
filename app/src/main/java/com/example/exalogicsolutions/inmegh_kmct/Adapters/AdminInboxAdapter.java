package com.example.exalogicsolutions.inmegh_kmct.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse.ReceivedMail;
import com.example.exalogicsolutions.inmegh_kmct.R;

import java.util.ArrayList;
import java.util.List;

public class AdminInboxAdapter extends RecyclerView.Adapter<AdminInboxAdapter.ViewHolder> {
    private List<ReceivedMail> arrayList;
    private Activity activity;
    String letter;
    Context context;

    public AdminInboxAdapter(Activity activity, ArrayList<ReceivedMail> arrayList) {

        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AdminInboxAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.email_cardview_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminInboxAdapter.ViewHolder holder, int position) {

        ReceivedMail receivedMail = arrayList.get(position);
        holder.txTime.setText("" + receivedMail.getTime());
        holder.txSubject.setText("" + receivedMail.getSubject());
        holder.txProfileName.setText("" + receivedMail.getProfile());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgEmailProfilePic, imgAttachment;
        private TextView txProfileName, txTime, txSubject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgEmailProfilePic = itemView.findViewById(R.id.imgEmailProfilePic);
            imgAttachment = itemView.findViewById(R.id.imgAttachment);
            txProfileName = itemView.findViewById(R.id.txProfileName);
            txTime = itemView.findViewById(R.id.txTime);
            txSubject = itemView.findViewById(R.id.txSubject);
        }
    }
}
