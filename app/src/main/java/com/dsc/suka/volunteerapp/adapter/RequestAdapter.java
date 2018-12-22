package com.dsc.suka.volunteerapp.adapter;

import android.content.Context;
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
import com.dsc.suka.volunteerapp.newModel.RequestModelData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {
    private List<RequestModelData> mRequestItemsList;
    private Context context;
    private RequestAdapterClickListener mListener;
    private int mCurrentPlayingPosition = -1;

    public RequestAdapter(List<RequestModelData> requestItemsList, Context context, RequestAdapterClickListener listener) {
        mRequestItemsList = requestItemsList;
        this.context = context;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tuna_netra_request_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mRequestItemsList.get(position), position, mListener);

    }

    @Override
    public int getItemCount() {
        if (mRequestItemsList != null) {
            return mRequestItemsList.size();
        } else {
            return 0;
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRequesterName, tvRequesterProdi, tvRequestTime;
        ImageView imgRequesterPhoto;
        FloatingActionButton fabPlay;
        boolean isPlaying = false;

        ViewHolder(View itemView) {
            super(itemView);
            tvRequesterName = itemView.findViewById(R.id.tv_requester_name);
            tvRequesterProdi = itemView.findViewById(R.id.tv_requester_prodi);
            tvRequestTime = itemView.findViewById(R.id.tv_request_time);
            imgRequesterPhoto = itemView.findViewById(R.id.img_requester_photo);
            fabPlay = itemView.findViewById(R.id.fab_play_request_item);
        }

        void bind(final RequestModelData requestItems, final int position, final RequestAdapterClickListener listener) {
            if (mCurrentPlayingPosition == position){
                fabPlay.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_pause_blue_24dp));
            } else {
                fabPlay.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_play_arrow_red_24dp));
                isPlaying = false;
            }
            tvRequesterName.setText(requestItems.userName);
            tvRequesterProdi.setText("");

//            String fullDate = requestItems.getTime();
//            StringTokenizer tokenizer = new StringTokenizer(fullDate);

//            String date = tokenizer.nextToken();
//            String time = tokenizer.nextToken();

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(calendar.getTime());

//            if (today.equalsIgnoreCase(date)) {
//                String newTime = DateTime.dateTimeParser(time, "hh:mm:ss", "hh:mm:ss");
//                tvRequestTime.setText(newTime);
//
//            } else {
//                String newDate = DateTime.dateTimeParser(date, "yyyy-MM-dd", "dd MMMM yyyy");
//                tvRequestTime.setText(newDate);
//            }

            String photoUrl = requestItems.avatarURL;

            RequestOptions options = new RequestOptions()
                    .centerCrop();

            Glide.with(itemView.getContext())
                    .load(photoUrl)
                    .apply(options)
                    .into(imgRequesterPhoto);

            final String audioURl = requestItems.getAudioUrl();

            fabPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int previousPosition = mCurrentPlayingPosition;

                    if (isPlaying) {
                        mCurrentPlayingPosition = -1;

                        listener.onClickListener(audioURl, getAdapterPosition(), isPlaying);
                        fabPlay.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.ic_play_arrow_red_24dp));
                        isPlaying = false;
                    } else {
                        mCurrentPlayingPosition = getAdapterPosition();

                        listener.onClickListener(audioURl, getAdapterPosition(), isPlaying);
                        fabPlay.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.ic_pause_blue_24dp));
                        isPlaying = true;
                    }

                    if (previousPosition != -1){
                        notifyItemChanged(previousPosition);
                    }
                }
            });
        }
    }

    public interface RequestAdapterClickListener{
        void onClickListener(String audioUrl, int adapterPosition, boolean isPlaying);
    }

}
