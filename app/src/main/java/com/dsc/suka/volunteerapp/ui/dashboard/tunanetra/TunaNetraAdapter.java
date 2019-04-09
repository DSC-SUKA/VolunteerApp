package com.dsc.suka.volunteerapp.ui.dashboard.tunanetra;

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
import com.dsc.suka.volunteerapp.model.ResponseRequestSingle;

import java.util.List;

public class TunaNetraAdapter extends RecyclerView.Adapter<TunaNetraAdapter.ViewHolder> {
    private List<ResponseRequestSingle> mRequestList;
    private Context mContext;
    private TunaNetraAdapterClickListener mListener;
    private int mCurrentPlayingPosition = -1;

    public void setmCurrentPlayingPosition(int mCurrentPlayingPosition) {
        this.mCurrentPlayingPosition = mCurrentPlayingPosition;
    }

    public TunaNetraAdapter(List<ResponseRequestSingle> requestList, Context context, TunaNetraAdapterClickListener listener){
        mRequestList = requestList;
        mContext = context;
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
        holder.bind(mRequestList.get(position), position, mListener);
    }

    @Override
    public int getItemCount() {
        if (mRequestList != null) {
            return mRequestList.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRequesterName;
        ImageView imgRequesterPhoto;
        FloatingActionButton fabPlay;
        boolean isPlaying = false;

        ViewHolder(View itemView) {
            super(itemView);
            tvRequesterName = itemView.findViewById(R.id.tv_requester_name);
            imgRequesterPhoto = itemView.findViewById(R.id.img_requester_photo);
            fabPlay = itemView.findViewById(R.id.fab_play_request_item);
        }

        void bind(final ResponseRequestSingle requestItems, final int position, final TunaNetraAdapterClickListener listener) {
            if (mCurrentPlayingPosition == position) {
                fabPlay.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_pause_blue_24dp));
            } else {
                fabPlay.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_play_arrow_red_24dp));
                isPlaying = false;
            }

            String nama = String.valueOf(requestItems.getUserName());
            String avatar_url = String.valueOf(requestItems.getAvatarURL());
            final String audioURl = String.valueOf(requestItems.getAudioUrl() + "?alt=media&token=");
            tvRequesterName.setText(nama);

//            String fullDate = requestItems.getTime();
//            StringTokenizer tokenizer = new StringTokenizer(fullDate);

//            String date = tokenizer.nextToken();
//            String time = tokenizer.nextToken();

//            Calendar calendar = Calendar.getInstance();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String today = sdf.format(calendar.getTime());

//            if (today.equalsIgnoreCase(date)) {
//                String newTime = DateTime.dateTimeParser(time, "hh:mm:ss", "hh:mm:ss");
//                tvRequestTime.setText(newTime);
//
//            } else {
//                String newDate = DateTime.dateTimeParser(date, "yyyy-MM-dd", "dd MMMM yyyy");
//                tvRequestTime.setText(newDate);
//            }


            RequestOptions options = new RequestOptions()
                    .centerCrop();

            Glide.with(itemView.getContext())
                    .load(avatar_url)
                    .apply(options)
                    .into(imgRequesterPhoto);



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

    public interface TunaNetraAdapterClickListener{
        void onClickListener(String audioUrl, int adapterPosition, boolean isPlaying);
    }
}
