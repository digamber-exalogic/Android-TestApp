package com.example.exalogicsolutions.inmegh_kmct.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.Adminevents;
import com.example.exalogicsolutions.inmegh_kmct.R;

import java.util.ArrayList;

public class AdminEventViewAdapter extends RecyclerView.Adapter<AdminEventViewAdapter.ViewHolder> {
    private ArrayList<Adminevents> arrayList;
    private Activity activity;


    public AdminEventViewAdapter(Activity activity, ArrayList<Adminevents> arrayList) {
        this.arrayList = arrayList;
        this.activity = activity;
    }


    @Override
    public AdminEventViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_cardview_list, parent, false);
        AdminEventViewAdapter.ViewHolder vh = new AdminEventViewAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(AdminEventViewAdapter.ViewHolder holder, final int position) {

        Adminevents adminevents = arrayList.get(position);


        holder.tvName.setText("" + adminevents.getName());
        holder.tvStartDate.setText("" + adminevents.getStartDate());
        if (adminevents.getEndDate() == null) {
            holder.tvEndDate.setText("Single Day");

        } else {
            holder.tvEndDate.setText("" + adminevents.getEndDate());
        }
        if (adminevents.getCourse() == null) {
            holder.tvCourseName.setText("Common For All");
        } else {
            holder.tvCourseName.setText("" + adminevents.getCourse());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvStartDate, tvEndDate, tvCourseName;
        ImageView imgedit, imgdelete, imgview;

        public ViewHolder(View v) {
            super(v);


            this.tvName = (TextView) v.findViewById(R.id.txEventName);
            this.tvStartDate = (TextView) v.findViewById(R.id.txStartDate);

            this.tvEndDate = (TextView) v.findViewById(R.id.txEndDate);
            this.tvCourseName = (TextView) v.findViewById(R.id.txCourse);

        }


        public interface OnItemClickListener {

            public void onDeleteClick(View view, int position);

            public void onEditClick(View view, int position);

            public void onViewClick(View view, int position);

            public void onItemClick(View view, int position);


        }

    }

}
