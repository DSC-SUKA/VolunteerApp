package com.dsc.suka.volunteerapp.ui.dashboard.mycontribution;

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
import com.dsc.suka.volunteerapp.model.ResponseAudioSingle;
import com.dsc.suka.volunteerapp.utils.DateTime;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

public class MyContributionAdapter extends RecyclerView.Adapter<MyContributionAdapter.ViewHolder> {
    private List<ResponseAudioSingle> mContributionList;
    private Context mContext;

    public MyContributionAdapter(List<ResponseAudioSingle> contributionList, Context context) {
        this.mContributionList = contributionList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_contribution_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mContributionList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mContributionList != null){
            return mContributionList.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContributionName, tvContributionProdi, tvContributionTime;
        private ImageView imgContributionPhoto;

        ViewHolder(View itemView) {
            super(itemView);
            tvContributionName = itemView.findViewById(R.id.tv_contribution_name);
            tvContributionProdi = itemView.findViewById(R.id.tv_contribution_prodi);
            tvContributionTime = itemView.findViewById(R.id.tv_contribution_time);
            imgContributionPhoto = itemView.findViewById(R.id.img_person_contribution);
        }

        void bind(final ResponseAudioSingle contributionItems) {
            tvContributionName.setText(contributionItems.getUser_dif_name());
            tvContributionProdi.setText(contributionItems.getCategory());

//            String fullDate = contributionItems.getTime();
//            StringTokenizer tokenizer = new StringTokenizer(fullDate);
//
//            String date = tokenizer.nextToken();
//            String time = tokenizer.nextToken();
//
//            Calendar calendar = Calendar.getInstance();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String today = sdf.format(calendar.getTime());
//
//            if (today.equalsIgnoreCase(date)) {
//                String newTime = DateTime.dateTimeParser(time, "hh:mm:ss", "hh:mm:ss");
//                tvContributionTime.setText(newTime);
//
//            } else {
//                String newDate = DateTime.dateTimeParser(date, "yyyy-MM-dd", "dd MMMM yyyy");
//                tvContributionTime.setText(newDate);
//            }

//            String photoUrl = contributionItems;
//
//            RequestOptions options = new RequestOptions()
//                    .centerCrop();
//
//            Glide.with(itemView.getContext())
//                    .load(photoUrl)
//                    .apply(options)
//                    .into(imgContributionPhoto);
        }
    }
}
