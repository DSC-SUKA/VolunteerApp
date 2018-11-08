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
import com.dsc.suka.volunteerapp.model.RequestItems;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class MyContributionAdapter extends RecyclerView.Adapter<MyContributionAdapter.MyContributionViewHolder>{
    List<RequestItems> mRequestItemList;
    private Context context;

    public MyContributionAdapter(List<RequestItems> requestItemsList, Context context){
        mRequestItemList = requestItemsList;
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
        holder.tvContributionName.setText(mRequestItemList.get(position).getRequesterName());
        holder.tvContributionProdi.setText(mRequestItemList.get(position).getRequsterProdi());

        String fullDate = mRequestItemList.get(position).getTime();
        StringTokenizer tokenizer = new StringTokenizer(fullDate);

        String date = tokenizer.nextToken();
        String time = tokenizer.nextToken();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(calendar.getTime());

        if (today.equalsIgnoreCase(date)){
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
            Date format = null;

            try {
                format = timeFormat.parse(time);
            } catch (ParseException e){
                e.printStackTrace();
            }

            SimpleDateFormat newTimeFormat = new SimpleDateFormat("hh:mm a");
            String newTime = newTimeFormat.format(format);

            holder.tvContributionTime.setText(newTime);
        } else {
            Date format = null;

            try {
                format = sdf.parse(date);
            } catch (ParseException e){
                e.printStackTrace();
            }

            SimpleDateFormat newFormat = new SimpleDateFormat("dd MMMM yyyy");
            String newDate = newFormat.format(format);

            holder.tvContributionTime.setText(newDate);
        }

        String photoUrl = mRequestItemList.get(position).getRequesterPhoto();

        RequestOptions options = new RequestOptions()
                .centerCrop();

        Glide.with(context)
                .load(photoUrl)
                .apply(options)
                .into(holder.imgContributionPhoto);
    }

    @Override
    public int getItemCount() {
        return mRequestItemList.size();
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
    }
}
