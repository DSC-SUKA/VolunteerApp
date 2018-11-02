package com.dicoding.millatip.volunteerapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dicoding.millatip.volunteerapp.R;
import com.dicoding.millatip.volunteerapp.model.ContributionItems;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class LatestContributionAdapter extends RecyclerView.Adapter<LatestContributionAdapter.LatestContributionViewHolder> {
    private List<ContributionItems> contributionItemsList;
    private Context context;

    public LatestContributionAdapter(List<ContributionItems> contributionItems, Context context){
        contributionItemsList = contributionItems;
        this.context = context;
    }

    @NonNull
    @Override
    public LatestContributionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        LatestContributionViewHolder latestContributionViewHolder = new LatestContributionViewHolder(view);
        return latestContributionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LatestContributionViewHolder holder, int position) {
        holder.requesterName.setText(contributionItemsList.get(position).getRequesterName());
        holder.requesterProdi.setText(contributionItemsList.get(position).getRequsterProdi());

        String getType = contributionItemsList.get(position).getRequesterType();
        String type;

        if (getType.equalsIgnoreCase("blind")){
            type = context.getResources().getString(R.string.type_blind);
            holder.requesterType.setBackground(context.getResources().getDrawable(R.drawable.tv_background_blind));
        } else if (getType.equalsIgnoreCase("deaf")){
            type = context.getResources().getString(R.string.type_deaf);
            holder.requesterType.setBackground(context.getResources().getDrawable(R.drawable.tv_background_deaf));
        } else {
            type = null;
        }

        holder.requesterType.setText(type);

        String fullDate = contributionItemsList.get(position).getTime();
        StringTokenizer tokenizer = new StringTokenizer(fullDate);

        String date = tokenizer.nextToken();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate = null;
        try {
            tempDate = sdf.parse(date);
        } catch (ParseException e){
            e.printStackTrace();
        }

        SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
        String newDate = newFormat.format(tempDate);

        holder.requestTime.setText(newDate);
    }

    @Override
    public int getItemCount() {
        if (contributionItemsList != null){
            return contributionItemsList.size();
        } else {
            return 0;
        }
    }

    public class LatestContributionViewHolder extends RecyclerView.ViewHolder {
        private TextView requesterName, requesterProdi, requesterType, requestTime;

        public LatestContributionViewHolder(View itemView) {
            super(itemView);
            requesterName = itemView.findViewById(R.id.tv_latest_contribution_name);
            requesterProdi = itemView.findViewById(R.id.tv_latest_contribution_prodi);
            requesterType = itemView.findViewById(R.id.tv_latest_contribution_type);
            requestTime = itemView.findViewById(R.id.tv_latest_contribution_time);
        }
    }
}
