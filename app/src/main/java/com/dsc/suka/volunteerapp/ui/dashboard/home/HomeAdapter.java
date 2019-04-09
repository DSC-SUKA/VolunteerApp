package com.dsc.suka.volunteerapp.ui.dashboard.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.model.ResponseAudioSingle;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<ResponseAudioSingle> mResponseAudioList;
    private Context mContext;

    public HomeAdapter(List<ResponseAudioSingle> responseAudioList, Context context){
        mResponseAudioList = responseAudioList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(mResponseAudioList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mResponseAudioList != null){
            return mResponseAudioList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView requesterName, requesterProdi, requesterType, requestTime;

        public ViewHolder(View itemView) {
            super(itemView);
            requesterName = itemView.findViewById(R.id.tv_latest_contribution_name);
            requesterProdi = itemView.findViewById(R.id.tv_latest_contribution_prodi);
//            requesterType = itemView.findViewById(R.id.tv_latest_contribution_type);
//            requestTime = itemView.findViewById(R.id.tv_latest_contribution_time);
        }

        void bindItem(ResponseAudioSingle audioSingle){
            requesterName.setText(audioSingle.getUser_name());
        }
    }
}
