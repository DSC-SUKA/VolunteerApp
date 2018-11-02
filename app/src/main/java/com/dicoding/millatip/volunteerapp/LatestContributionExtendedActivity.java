package com.dicoding.millatip.volunteerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dicoding.millatip.volunteerapp.model.ContributionItems;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class LatestContributionExtendedActivity extends AppCompatActivity implements View.OnClickListener{
    private ContributionItems contributionItems;
    private TextView tvName, tvProdi, tvTime;
    public static String EXTRA_CONTRIBUTION = "contribution_extras";
    private ImageButton btnPlay;
    private boolean isPaused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_contribution_extended);

        tvName = findViewById(R.id.tv_extended_contribution_name);
        tvProdi = findViewById(R.id.tv_extended_contribution_prodi);
        tvTime = findViewById(R.id.tv_extended_contribution_time);
        btnPlay = findViewById(R.id.btn_pause);
        btnPlay.setOnClickListener(this);

        isPaused = true;

        contributionItems = getIntent().getParcelableExtra(EXTRA_CONTRIBUTION);

        tvName.setText(contributionItems.getRequesterName());
        tvProdi.setText(contributionItems.getRequsterProdi());

        String fullDate = contributionItems.getTime();
        StringTokenizer tokenizer = new StringTokenizer(fullDate);

        String date = tokenizer.nextToken();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate = null;
        try {
            tempDate = sdf.parse(date);
        } catch (ParseException e){
            e.printStackTrace();
        }

        SimpleDateFormat newFormat = new SimpleDateFormat("dd / MM / yyyy");
        String newDate = newFormat.format(tempDate);

        tvTime.setText(newDate);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pause:
                if (isPaused){
                    btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_blue_24dp));
                    isPaused = !isPaused;
                } else {
                    btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_red_24dp));
                    isPaused = !isPaused;
                }
                break;
        }
    }
}
