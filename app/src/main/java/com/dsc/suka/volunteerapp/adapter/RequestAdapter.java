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

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder>{
    List<RequestItems> mRequestItemsList;
    private Context context;

    public RequestAdapter(List<RequestItems> requestItemsList, Context context){
        mRequestItemsList = requestItemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tuna_netra_request_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvRequesterName.setText(mRequestItemsList.get(position).getRequesterName());
        holder.tvRequesterProdi.setText(mRequestItemsList.get(position).requsterProdi);

        String fullDate = mRequestItemsList.get(position).getTime();
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

            holder.tvRequestTime.setText(newTime);

        } else {
            Date format = null;

            try {
                format = sdf.parse(date);
            } catch (ParseException e){
                e.printStackTrace();
            }

            SimpleDateFormat newFormat = new SimpleDateFormat("dd MMMM yyyy");
            String newDate = newFormat.format(format);

            holder.tvRequestTime.setText(newDate);
        }

        String photoUrl = mRequestItemsList.get(position).getRequesterPhoto();

        RequestOptions options = new RequestOptions()
                .centerCrop();

        Glide.with(context)
                .load(photoUrl)
                .apply(options)
                .into(holder.imgRequesterPhoto);
    }

    @Override
    public int getItemCount() {
        return mRequestItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRequesterName, tvRequesterProdi, tvRequestTime;
        public ImageView imgRequesterPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            tvRequesterName = itemView.findViewById(R.id.tv_requester_name);
            tvRequesterProdi = itemView.findViewById(R.id.tv_requester_prodi);
            tvRequestTime = itemView.findViewById(R.id.tv_request_time);
            imgRequesterPhoto = itemView.findViewById(R.id.img_requester_photo);
        }
    }
}
