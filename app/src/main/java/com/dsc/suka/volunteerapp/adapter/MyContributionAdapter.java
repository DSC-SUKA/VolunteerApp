package com.dsc.suka.volunteerapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.model.ContributionItems;
import com.dsc.suka.volunteerapp.model.RequestItems;
import com.dsc.suka.volunteerapp.util.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class MyContributionAdapter extends RecyclerView.Adapter<MyContributionAdapter.MyContributionViewHolder> {
    List<ContributionItems> mContributionItemList;
    private Context context;

    public MyContributionAdapter(List<ContributionItems> contributionItemsList, Context context) {
        mContributionItemList = contributionItemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyContributionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_contribution_item, parent, false);
        MyContributionViewHolder myContributionViewHolder = new MyContributionViewHolder(view);
        return myContributionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyContributionViewHolder holder, int position) {
        holder.bind(mContributionItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mContributionItemList.size();
    }


    public class MyContributionViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContributionName, tvContributionProdi, tvContributionTime;
        private ImageView imgContributionPhoto;

        public MyContributionViewHolder(View itemView) {
            super(itemView);
            tvContributionName = itemView.findViewById(R.id.tv_contribution_name);
            tvContributionProdi = itemView.findViewById(R.id.tv_contribution_prodi);
            tvContributionTime = itemView.findViewById(R.id.tv_contribution_time);
            imgContributionPhoto = itemView.findViewById(R.id.img_person_contribution);
        }

        public void bind(final ContributionItems contributionItems) {
            tvContributionName.setText(contributionItems.getRequesterName());
            tvContributionProdi.setText(contributionItems.getRequsterProdi());

            String fullDate = contributionItems.getTime();
            StringTokenizer tokenizer = new StringTokenizer(fullDate);

            String date = tokenizer.nextToken();
            String time = tokenizer.nextToken();

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(calendar.getTime());

            if (today.equalsIgnoreCase(date)) {
                String newTime = DateTime.dateTimeParser(time, "hh:mm:ss", "hh:mm:ss");
                tvContributionTime.setText(newTime);

            } else {
                String newDate = DateTime.dateTimeParser(date, "yyyy-MM-dd", "dd MMMM yyyy");
                tvContributionTime.setText(newDate);
            }

            String photoUrl = contributionItems.getRequesterPhoto();

            RequestOptions options = new RequestOptions()
                    .centerCrop();

            Glide.with(itemView.getContext())
                    .load(photoUrl)
                    .apply(options)
                    .into(imgContributionPhoto);
        }
    }
}
