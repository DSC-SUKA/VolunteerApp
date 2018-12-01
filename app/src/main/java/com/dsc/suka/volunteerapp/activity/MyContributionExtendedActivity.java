package com.dsc.suka.volunteerapp.activity;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.model.ContributionItems;
import com.dsc.suka.volunteerapp.util.DateTime;
import com.dsc.suka.volunteerapp.util.MediaUtil;

import java.io.IOException;
import java.util.StringTokenizer;

public class MyContributionExtendedActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {
    private ContributionItems contributionItems;
    private TextView tvName, tvProdi, tvTime, tvAudioStart, tvAudioEnd;
    public static String EXTRA_CONTRIBUTION = "contribution_extras";
    private ImageButton btnPlay, btnStop, btnRestart;
    private boolean isPlay = false, isStopClicked = false;
    private SeekBar seekBarProgrress;
    private String audioUrl = "https://drive.google.com/file/d/1YNv1z1DKStr6xr9Sj9ihTa55pW3n6EoB/view";
    private MediaPlayer mediaPlayer;
    private int mediaFileLengtInMilis;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_contribution_extended);

        tvName = findViewById(R.id.tv_extended_contribution_name);
        tvProdi = findViewById(R.id.tv_extended_contribution_prodi);
        tvTime = findViewById(R.id.tv_extended_contribution_time);
        tvAudioStart = findViewById(R.id.tv_audio_start);
        tvAudioEnd = findViewById(R.id.tv_audio_end);
        btnPlay = findViewById(R.id.btn_play_contribution_extended);
        btnPlay.setOnClickListener(this);

        btnStop = findViewById(R.id.btn_stop_contribution_extended);
        btnStop.setOnClickListener(this);

        btnRestart = findViewById(R.id.btn_restart_contribution_extended);
        btnRestart.setOnClickListener(this);

        contributionItems = getIntent().getParcelableExtra(EXTRA_CONTRIBUTION);

        tvName.setText(contributionItems.getRequesterName());
        tvProdi.setText(contributionItems.getRequsterProdi());

        String fullDate = contributionItems.getTime();
        StringTokenizer tokenizer = new StringTokenizer(fullDate);

        String date = tokenizer.nextToken();
        String newDate = DateTime.dateTimeParser(date, "yyyy-MM-dd", "dd / MM / yyyy");

        tvTime.setText(newDate);

        seekBarProgrress = findViewById(R.id.seekbar_contribution);
        seekBarProgrress.setPadding(0, 0, 0, 0);
        seekBarProgrress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        setMediaPlayer(audioUrl);

    }

    private void clearMediaPlayer() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
    }

    private void setMediaPlayer(String audioUrl) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.cek);
            try {
                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        tvAudioStart.setText(MediaUtil.convertDuration((long) 0));
        tvAudioEnd.setText(MediaUtil.convertDuration((long) mediaPlayer.getDuration()));
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play_contribution_extended:
                if (isPlay) {
                    mediaPlayer.pause();
                    setPlayButton(btnPlay);
                    isPlay = false;
                } else {
                    mediaPlayer.start();
                    playMedia();
                    setPauseButton(btnPlay);
                    isPlay = true;
                    seekBarUpdater();
                }
                break;

            case R.id.btn_stop_contribution_extended:

                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_red_24dp));
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                        isPlay = false;
                        seekBarUpdater();
                    }
                }
                break;

            case R.id.btn_restart_contribution_extended:
                if (mediaPlayer != null) {
                    btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_blue_24dp));
                    mediaPlayer.seekTo(0);
                    playMedia();
                    isPlay = true;
                    seekBarUpdater();
                }
                break;
        }
    }

    private void seekBarUpdater() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                seekBarProgrress.setMax(mediaPlayer.getDuration() / 1000);
                int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                seekBarProgrress.setProgress(mCurrentPosition);
                tvAudioStart.setText(MediaUtil.convertDuration((long) mediaPlayer.getCurrentPosition()));
                mHandler.postDelayed(this, 1000);
            }
        });
    }

    private void playMedia() {
        mediaPlayer.start();

    }

    private void setPlayButton(ImageButton btnPlay) {
        btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_red_24dp));
    }

    private void setPauseButton(ImageButton btnPlay) {
        btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_blue_24dp));
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_red_24dp));
        mediaPlayer.seekTo(0);
        isPlay = false;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        clearMediaPlayer();
    }
}
