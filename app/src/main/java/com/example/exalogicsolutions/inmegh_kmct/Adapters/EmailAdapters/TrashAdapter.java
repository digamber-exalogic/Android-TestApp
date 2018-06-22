package com.example.exalogicsolutions.inmegh_kmct.Adapters.EmailAdapters;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse.DeletedEmail;
import com.example.exalogicsolutions.inmegh_kmct.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrashAdapter extends RecyclerView.Adapter<TrashAdapter.MyViewHolder>{
    private static final String TAG = "InboxAdapter";

    private ArrayList<DeletedEmail> mTrashArrayList;
    private FragmentActivity activity;
    private ClickListener clickListener;

    public TrashAdapter(FragmentActivity activity, ArrayList<DeletedEmail> trashArrayList) {
        this.activity = activity;
        this.mTrashArrayList = trashArrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.email_cardview_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DeletedEmail item = mTrashArrayList.get(position);
        Glide.with(activity)
                .asBitmap().load(item.getProfile())
                .into(holder.imgEmailProfilePic);

        holder.txProfileName.setText(item.getSender());
        holder.txSubject.setText(item.getSubject());
        holder.txTime.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return mTrashArrayList.size();
    }

    public  void  removeItem(int position) {

        mTrashArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public void setClickListener (ClickListener clickListener) {

        this.clickListener = clickListener;
    }

    public void restoreItem(DeletedEmail item, int position) {

        mTrashArrayList.add(position, item);
        notifyItemInserted(position);
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
            if (clickListener != null) {

                clickListener.itemClicked(view, getAdapterPosition());
            }
        }
    }

    public interface ClickListener {

        public void itemClicked(View view, int position);
    }
}
