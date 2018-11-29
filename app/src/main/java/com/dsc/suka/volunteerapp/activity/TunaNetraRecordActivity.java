package com.dsc.suka.volunteerapp.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.fragment.TunaNetraAlertDiscardDialog;

import java.io.IOException;

public class TunaNetraRecordActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String EXTRA_REQUEST_ID = "request_id_extras";
    private Button btnSend, btnDiscard;
    private ImageButton btnRecord;
    private boolean isRecording, isRecorded, isRecordPaused;
    private TextView tvRecordMessage;
    private long startHTime = 0L;
    private Handler handler = new Handler();
    long timeInMilis = 0L, timeSwapBuff = 0L, updatedTime = 0L;
    private MediaRecorder myAudioRecorder;
    private String outputFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuna_netra_record);

        setRecorder();

        isRecorded = false;
        isRecordPaused = true;
        isRecording = false;

        tvRecordMessage = findViewById(R.id.tv_record_message);

        btnSend = findViewById(R.id.btn_send_record);
        btnSend.setOnClickListener(this);
        btnSend.setEnabled(false);

        btnDiscard = findViewById(R.id.btn_discard_record);
        btnDiscard.setOnClickListener(this);
        btnDiscard.setEnabled(false);

        btnRecord = findViewById(R.id.btn_record);

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRecorded == false){
                    if (isRecording == false){
                        try {
                            myAudioRecorder.prepare();
                            myAudioRecorder.start();
                            startHTime = SystemClock.uptimeMillis();
                            handler.postDelayed(updateTimer, 0);
                        } catch (IllegalStateException ise){
                            ise.printStackTrace();
                        } catch (IOException e){
                            e.printStackTrace();
                        }

                        setStopButton();
                        isRecording = true;
                    } else {
                        myAudioRecorder.stop();
                        myAudioRecorder.release();
                        myAudioRecorder = null;

                        timeSwapBuff += timeInMilis;
                        handler.removeCallbacks(updateTimer);

                        setPlayButton();
                        isRecording = !isRecording;
                        isRecorded = true;
                        btnSend.setEnabled(true);
                        btnDiscard.setEnabled(true);
                    }
                } else if (isRecorded){
                    final MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            setPlayButton();
                            isRecordPaused = true;
                        }
                    });
                    if (isRecordPaused){
                        playAudioRecorded(mediaPlayer, outputFile);
                        setStopButton();
                        isRecordPaused = !isRecordPaused;
                    } else {
                        stopAudioRecorded(mediaPlayer, outputFile);
                        setPlayButton();
                        isRecordPaused = !isRecordPaused;
                    }
                }
            }
        });

    }

    private void setRecorder() {
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);
    }

    private void stopAudioRecorded(MediaPlayer mediaPlayer, String outputFile) {
        if (mediaPlayer != null){
            try {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void playAudioRecorded(MediaPlayer mediaPlayer, String outputFile) {
        try {
            mediaPlayer.setDataSource(outputFile);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private void setRecordButton(){
        btnRecord.setImageDrawable(getResources().getDrawable(R.mipmap.btn_record));
    }

    private void setPlayButton() {
        btnRecord.setImageDrawable(getResources().getDrawable(R.mipmap.btn_play));
    }

    private void setStopButton() {
        btnRecord.setImageDrawable(getResources().getDrawable(R.mipmap.btn_stop));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_discard_record:
                TunaNetraAlertDiscardDialog alertDialog = new TunaNetraAlertDiscardDialog();
                alertDialog.setOnOptionDialogListener(new TunaNetraAlertDiscardDialog.OnOptionDialogListener() {
                    @Override
                    public void onOptionChoosen(boolean optionsChoosen) {
                        if (optionsChoosen){
                            resetRecord();
                        }
                    }
                });
                FragmentManager fragmentManager = getSupportFragmentManager();
                alertDialog.show(fragmentManager, TunaNetraAlertDiscardDialog.class.getSimpleName());
                break;
            case R.id.btn_send_record:
                Intent intent = new Intent(this, TunaNetraSendActivity.class);
                intent.putExtra(TunaNetraSendActivity.EXTRA_REQUEST_ID_SUBMIT, getIntent().getIntExtra(EXTRA_REQUEST_ID, 0));
                startActivity(intent);
                break;
        }
    }

    private Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            timeInMilis = SystemClock.uptimeMillis() - startHTime;
            updatedTime = timeSwapBuff + timeInMilis;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            if (tvRecordMessage != null){
                tvRecordMessage.setText("" + String.format("%02d", mins) + ":" + String.format("%02d", secs));
                handler.postDelayed(this, 0);
            }
        }
    };

    private void resetRecord() {
        isRecorded = false;
        isRecordPaused = true;
        isRecording = false;

        setRecordButton();

        btnSend.setEnabled(false);
        btnDiscard.setEnabled(false);

        resetRecordMessage();

        tvRecordMessage.setText(getString(R.string.tv_record_message));
        setRecorder();
    }

    private void resetRecordMessage(){
        startHTime = 0L;
        timeInMilis = 0L;
        timeSwapBuff = 0L;
        updatedTime = 0L;
    }

}
