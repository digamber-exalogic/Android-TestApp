package com.example.exalogicsolutions.inmegh_kmct.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.exalogicsolutions.inmegh_kmct.Models.LeaveResponse.AdminLeaveApplication;
import com.example.exalogicsolutions.inmegh_kmct.R;

import java.util.ArrayList;
import java.util.List;

public class AdminLeaveAdapter extends RecyclerView.Adapter<AdminLeaveAdapter.ViewHolder> {

private List<AdminLeaveApplication> adminLeaveApplications;

private Activity activity;
public static AdminLeaveAdapter.OnItemClickListener mItemClickListener;
        boolean isSelectedAll;

public AdminLeaveAdapter(Activity activity, ArrayList<AdminLeaveApplication> adminLeaveApplications) {
        this.adminLeaveApplications = adminLeaveApplications;

        this.activity = activity;
        }


@Override
public AdminLeaveAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.leave_cardview_list, parent, false);
        AdminLeaveAdapter.ViewHolder vh = new AdminLeaveAdapter.ViewHolder(v);

        return vh;
        }

public void selectAll() {
        Log.e("onClickSelectAll", "yes");
        isSelectedAll = true;
        notifyDataSetChanged();
        }

@Override
public void onBindViewHolder(AdminLeaveAdapter.ViewHolder holder, final int position) {

        AdminLeaveApplication adminLeaveApplication = adminLeaveApplications.get(position);


        holder.tvApplid.setText("" + adminLeaveApplication.getEmpName());
        holder.tvCourse.setText("" + adminLeaveApplication.getCourseName());
        holder.tvType.setText("" + adminLeaveApplication.getLeaveType());
        holder.tvStart.setText("" + adminLeaveApplication.getStartDate());

        holder.tvEnd.setText("" + adminLeaveApplication.getEndDate());
        holder.tvMax.setText("" + adminLeaveApplication.getMaxCount());

        holder.tvAvailable.setText("" + adminLeaveApplication.getAvailable());
        holder.tvLop.setText("" + adminLeaveApplication.getNoDaysLop());
        holder.tvReason.setText("" + adminLeaveApplication.getReason());

        if (adminLeaveApplication.getApproved().equalsIgnoreCase("Rejected")) {
        holder.approvedtv.setVisibility(View.GONE);
        holder.btnReject.setVisibility(View.GONE);
        holder.btnApproved.setVisibility(View.GONE);
        holder.rejectedtv.setVisibility(View.VISIBLE);
        } else if (adminLeaveApplication.getApproved().equalsIgnoreCase("Approved")) {
        holder.approvedtv.setVisibility(View.VISIBLE);
        holder.btnReject.setVisibility(View.GONE);
        holder.btnApproved.setVisibility(View.GONE);
        holder.rejectedtv.setVisibility(View.GONE);
        } else if (adminLeaveApplication.getApproved().equalsIgnoreCase("Pending")) {
        holder.btnReject.setVisibility(View.VISIBLE);
        holder.btnApproved.setVisibility(View.VISIBLE);
        holder.approvedtv.setVisibility(View.GONE);
        holder.rejectedtv.setVisibility(View.GONE);
        }
        }

@Override
public int getItemCount() {
        return adminLeaveApplications.size();
        }


public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tvApplid, tvCourse, tvType, tvStart, tvEnd, tvMax;
    TextView tvAvailable, tvLop, tvReason;
    LinearLayout approvedtv, rejectedtv;
    ImageView btnApproved, btnReject;

    public ViewHolder(View v) {
        super(v);
        this.approvedtv = v.findViewById(R.id.lyAccept);
        this.rejectedtv = v.findViewById(R.id.lyReject);

        this.tvApplid = v.findViewById(R.id.tvApplid);
        this.tvCourse = v.findViewById(R.id.tvCourse);
        this.tvType = v.findViewById(R.id.tvType);
        this.tvStart = v.findViewById(R.id.tvStartDate);
        this.tvMax = v.findViewById(R.id.tvMax);
        this.tvEnd = v.findViewById(R.id.tvEndDate);

        this.tvReason = v.findViewById(R.id.tvReason);
        this.tvLop = v.findViewById(R.id.tvLop);
        this.tvAvailable = v.findViewById(R.id.tvLeft);
        this.btnApproved = v.findViewById(R.id.imgLeaveAccept);
        this.btnReject = v.findViewById(R.id.imgLeaveReject);


        this.btnApproved.setOnClickListener(this);
        this.btnReject.setOnClickListener(this);
        v.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgLeaveAccept:
                mItemClickListener.onApproveClick(v, getAdapterPosition());
                v.setVisibility(View.GONE);
                break;
            case R.id.imgLeaveReject:
                mItemClickListener.onRejectClick(v, getAdapterPosition());
                break;
            default:
                mItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}

public interface OnItemClickListener {

    public void onItemClick(View view, int position);

    public void onApproveClick(View view, int position);

    public void onRejectClick(View view, int position);


}

    public void SetOnItemClickListener(AdminLeaveAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
