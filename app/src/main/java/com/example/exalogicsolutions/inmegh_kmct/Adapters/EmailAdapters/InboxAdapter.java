package com.example.exalogicsolutions.inmegh_kmct.Adapters.EmailAdapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuEmailInboxShowActivity;
import com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse.ReceivedMail;
import com.example.exalogicsolutions.inmegh_kmct.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.MyViewHolder> implements View.OnClickListener {
   /* @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }*/
    private static final String TAG = "InboxAdapter";

    private ArrayList<ReceivedMail> mInboxArrayList;
    private FragmentActivity activity;
    private ClickListener clickListener;

    public InboxAdapter(FragmentActivity activity, ArrayList<ReceivedMail> inboxArrayList) {
        this.activity = activity;
        this.mInboxArrayList = inboxArrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.email_cardview_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        ReceivedMail item = mInboxArrayList.get(position);
        Glide.with(activity)
                .asBitmap().load(item.getProfile())
                .into(holder.imgEmailProfilePic);

        holder.txProfileName.setText(item.getSender());
        holder.txSubject.setText(item.getSubject());
        holder.txTime.setText(item.getTime());

    }

    public void setClickListener (ClickListener clickListener) {

        this.clickListener = clickListener;
    }

    public  void  removeItem(int position) {

        mInboxArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(ReceivedMail item, int position) {

        mInboxArrayList.add(position, item);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return mInboxArrayList.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public CircleImageView imgEmailProfilePic;
        public ImageView imgAttachment;
        public TextView txProfileName, txSubject, txTime;
        public FrameLayout parentLayout;
        public RelativeLayout viewBackground;
        public RelativeLayout viewForeground;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            imgEmailProfilePic = itemView.findViewById(R.id.imgEmailProfilePic);
            imgAttachment = itemView.findViewById(R.id.imgAttachment);
            txProfileName = itemView.findViewById(R.id.txProfileName);
            txSubject = itemView.findViewById(R.id.txSubject);
            txTime = itemView.findViewById(R.id.txTime);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
        }

        @Override
        public void onClick(View view) {
            /*activity.startActivity(new Intent(activity, AdminMenuEmailInboxShowActivity.class));*/
            if (clickListener != null) {

                clickListener.itemClicked(view, getAdapterPosition());
            }
        }
    }

    public interface ClickListener {

        public void itemClicked(View view, int position);
    }

}
