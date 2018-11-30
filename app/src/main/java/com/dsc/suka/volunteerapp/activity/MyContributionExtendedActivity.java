package com.dsc.suka.volunteerapp.activity;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.model.ContributionItems;
import com.dsc.suka.volunteerapp.util.DateTime;

import java.util.StringTokenizer;

public class MyContributionExtendedActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener {
    private ContributionItems contributionItems;
    private TextView tvName, tvProdi, tvTime;
    public static String EXTRA_CONTRIBUTION = "contribution_extras";
    private ImageButton btnPlay, btnStop, btnRestart;
    private boolean isPaused;
    private SeekBar seekBarProgrress;
    private String audioUrl = "https://drive.google.com/file/d/1YNv1z1DKStr6xr9Sj9ihTa55pW3n6EoB/view";
    private MediaPlayer mediaPlayer;
    private int mediaFileLengtInMilis;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_contribution_extended);

        tvName = findViewById(R.id.tv_extended_contribution_name);
        tvProdi = findViewById(R.id.tv_extended_contribution_prodi);
        tvTime = findViewById(R.id.tv_extended_contribution_time);
        btnPlay = findViewById(R.id.btn_play_contribution_extended);
        btnPlay.setOnClickListener(this);

        btnStop = findViewById(R.id.btn_stop_contribution_extended);
        btnStop.setOnClickListener(this);

        btnRestart = findViewById(R.id.btn_restart_contribution_extended);
        btnRestart.setOnClickListener(this);

        isPaused = true;

        contributionItems = getIntent().getParcelableExtra(EXTRA_CONTRIBUTION);

        tvName.setText(contributionItems.getRequesterName());
        tvProdi.setText(contributionItems.getRequsterProdi());

        String fullDate = contributionItems.getTime();
        StringTokenizer tokenizer = new StringTokenizer(fullDate);

        String date = tokenizer.nextToken();
        String newDate = DateTime.dateTimeParser(date,"yyyy-MM-dd", "dd / MM / yyyy");

        tvTime.setText(newDate);

        seekBarProgrress = findViewById(R.id.seekbar_contribution);
        seekBarProgrress.setMax(99);
        seekBarProgrress.setOnTouchListener(this);

        setMediaPlayer();

    }

    private void setMediaPlayer() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.cek);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);
    }

    private void primarySeekBarProggressUpdater() {
        seekBarProgrress.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaFileLengtInMilis) * 100));
        if (mediaPlayer.isPlaying()) {
            Runnable notification = new Runnable() {
                @Override
                public void run() {
                    primarySeekBarProggressUpdater();
                }
            };
            handler.postDelayed(notification, 1000);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play_contribution_extended:
                mediaFileLengtInMilis = mediaPlayer.getDuration();

                if (isPaused) {

                    mediaPlayer.start();
                    btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_blue_24dp));
                    isPaused = false;

                } else {
                    mediaPlayer.pause();
                    btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_red_24dp));
                    isPaused = true;
                }
                primarySeekBarProggressUpdater();
                break;
            case R.id.btn_stop_contribution_extended:

                if (mediaPlayer != null){
                    if (mediaPlayer.isPlaying()){
                        btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_red_24dp));
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                        isPaused = true;
                    }
                }
                primarySeekBarProggressUpdater();
                break;
            case R.id.btn_restart_contribution_extended:
                if (mediaPlayer != null){
                    btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_blue_24dp));
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                    isPaused = false;
                }
                primarySeekBarProggressUpdater();
                break;
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        seekBarProgrress.setSecondaryProgress(percent);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_red_24dp));
        isPaused = true;

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.seekbar_contribution){
            if (mediaPlayer.isPlaying()){
                SeekBar sb = (SeekBar)v;
                int playPositionInMilis = (mediaFileLengtInMilis / 100) * sb.getProgress();
                mediaPlayer.seekTo(playPositionInMilis);
            }
        }
        return false;
    }

}
