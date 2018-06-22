package com.example.exalogicsolutions.inmegh_kmct.Adapters.EmailAdapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse.UserEmail;
import com.example.exalogicsolutions.inmegh_kmct.R;

import java.util.ArrayList;
import java.util.List;


public class ComposeAdapter  extends RecyclerView.Adapter<ComposeAdapter.ViewHolder> {

    private List<UserEmail> arrayList;
    private Activity activity;
    public static ComposeAdapter.OnItemClickListener mItemClickListener;
    boolean isSelectedAll;
    private String className = "";

    public ComposeAdapter(Activity activity, ArrayList<UserEmail> arrayList) {
        this.arrayList = arrayList;
        this.activity = activity;
    }


    @Override
    public ComposeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.email_compose_sender_cardview_list, parent, false);
        ComposeAdapter.ViewHolder vh = new ComposeAdapter.ViewHolder(v);

        return vh;
    }
    public void selectAll(){
        Log.e("onClickSelectAll","yes");
        isSelectedAll=true;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(ComposeAdapter.ViewHolder holder, final int position) {

        UserEmail userEmail = arrayList.get(position);

        holder.txEmailToName.setText("" + userEmail.getName());
        if (!isSelectedAll) holder.mCheckBoxEmail.setChecked(false);
        else holder.mCheckBoxEmail.setChecked(true);
        if (userEmail.getCheck_box() == 1) {
            holder.mCheckBoxEmail.setChecked(true);
        } else if (userEmail.getCheck_box() == 0) {
            holder.mCheckBoxEmail.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txEmailToName;
        CheckBox mCheckBoxEmail;

        ViewHolder(View v) {
            super(v);

            this.txEmailToName = (TextView) v.findViewById(R.id.txEmailToName);
            this.mCheckBoxEmail = (CheckBox) v.findViewById(R.id.chbEmail);

            this.mCheckBoxEmail.setOnClickListener(this);

            v.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.chbEmail :
                    mItemClickListener.onCheckClick(v, getAdapterPosition());
                    break;
                default:
                    mItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {

        public void onItemClick(View view, int position);

        public void onCheckClick(View view, int position);

    }

    public void SetOnItemClickListener(ComposeAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}



