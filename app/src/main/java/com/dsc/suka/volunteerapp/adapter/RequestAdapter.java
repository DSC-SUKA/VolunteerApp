package com.dsc.suka.volunteerapp.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import com.dsc.suka.volunteerapp.util.DateTime;

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
        holder.bind(mRequestItemsList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mRequestItemsList != null){
            return mRequestItemsList.size();
        } else {
            return 0;
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRequesterName, tvRequesterProdi, tvRequestTime;
        public ImageView imgRequesterPhoto;
        public FloatingActionButton fabPlay;
        MediaPlayer mediaPlayer;
        boolean isPlay = false;

        public ViewHolder(View itemView) {
            super(itemView);
            tvRequesterName = itemView.findViewById(R.id.tv_requester_name);
            tvRequesterProdi = itemView.findViewById(R.id.tv_requester_prodi);
            tvRequestTime = itemView.findViewById(R.id.tv_request_time);
            imgRequesterPhoto = itemView.findViewById(R.id.img_requester_photo);
            fabPlay = itemView.findViewById(R.id.fab_play_request_item);
        }

        public void bind(final RequestItems requestItems){
            tvRequesterName.setText(requestItems.requesterName);
            tvRequesterProdi.setText(requestItems.requsterProdi);

            String fullDate = requestItems.getTime();
            StringTokenizer tokenizer = new StringTokenizer(fullDate);

            String date = tokenizer.nextToken();
            String time = tokenizer.nextToken();

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(calendar.getTime());

            if (today.equalsIgnoreCase(date)){
                String newTime = DateTime.dateTimeParser(time,"hh:mm:ss","hh:mm:ss" );
                tvRequestTime.setText(newTime);

            } else {
                String newDate = DateTime.dateTimeParser(date, "yyyy-MM-dd", "dd MMMM yyyy");
                tvRequestTime.setText(newDate);
            }

            String photoUrl = requestItems.requesterPhoto;

            RequestOptions options = new RequestOptions()
                    .centerCrop();

            Glide.with(itemView.getContext())
                    .load(photoUrl)
                    .apply(options)
                    .into(imgRequesterPhoto);

            String audioURl = "https://drive.google.com/file/d/1YNv1z1DKStr6xr9Sj9ihTa55pW3n6EoB/view";

            if (mediaPlayer == null){
                mediaPlayer = new MediaPlayer();
                mediaPlayer = MediaPlayer.create(itemView.getContext(), R.raw.cek);
            }

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    fabPlay.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.ic_play_arrow_red_24dp));
                    isPlay = false;
                }
            });

            fabPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isPlay){
                        mediaPlayer.pause();
                        fabPlay.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.ic_play_arrow_red_24dp));
                        isPlay = false;
                    } else {
                        mediaPlayer.start();
                        fabPlay.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.ic_pause_blue_24dp));
                        isPlay = true;
                    }
                }
            });
        }
    }
}
