package com.dsc.suka.volunteerapp.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.fragment.TunaNetraAlertDiscardDialog;
import com.dsc.suka.volunteerapp.ui.dashboard.MainActivity;
import com.dsc.suka.volunteerapp.ui.sendresponse.TunaNetraSendActivity;
import com.dsc.suka.volunteerapp.utils.PermissionManager;

import java.io.IOException;

public class TunaNetraRecordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSend, btnDiscard;
    private ImageButton btnRecord;
    private boolean isRecording, isRecorded, isRecordPaused;
    private TextView tvRecordMessage;
    private long startHTime = 0L;
    private Handler handler = new Handler();
    long timeInMilis = 0L, timeSwapBuff = 0L, updatedTime = 0L;
    private MediaRecorder myAudioRecorder;
    private String[] permissionNeeded = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private final int REQUEST_CODE_RECORD_ACTIVITY = 111;
    private MediaPlayer mediaPlayer;
    public static String EXTRA_REQUEST_DETAIL = "extra_request";

    private String outputPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/response.3gp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuna_netra_record);

        checkPermission();

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
                if (PermissionManager.hasPermissions(getApplicationContext(), permissionNeeded)) {
                    if (!isRecorded) {
                        if (!isRecording) {
                            setRecorder();
                            try {
                                myAudioRecorder.prepare();
                                myAudioRecorder.start();
                                startHTime = SystemClock.uptimeMillis();
                                handler.postDelayed(updateTimer, 0);
                            }
                            catch (IllegalStateException | IOException ise) {
                                ise.printStackTrace();
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
                            isRecording = false;
                            isRecorded = true;
                            btnSend.setEnabled(true);
                            btnDiscard.setEnabled(true);
                        }
                    } else {
                        if (isRecordPaused) {
                            setAudioPlayer(outputPath);
                            mediaPlayer.start();
                            setStopButton();
                            isRecordPaused = false;
                        } else {
                            stopAudioRecorded(mediaPlayer, outputPath);
                            setPlayButton();
                            isRecordPaused = true;
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "You need permission to use this feature", Toast.LENGTH_SHORT).show();
                    requestPermissions();
                }
            }
        });

    }

    private void checkPermission() {
        if (!PermissionManager.hasPermissions(this, permissionNeeded)) {
            requestPermissions();
        }
    }

    private void requestPermissions() {
        PermissionManager.requestPermissions(this, permissionNeeded, REQUEST_CODE_RECORD_ACTIVITY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_RECORD_ACTIVITY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setRecorder() {
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputPath);
    }

    private void stopAudioRecorded(MediaPlayer mediaPlayer, String outputFile) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);

        }
    }

    private void setAudioPlayer(String outputFile) {
        if (mediaPlayer == null){
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer.setDataSource(outputFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                setPlayButton();
                isRecordPaused = true;
            }
        });
    }

    private void setRecordButton() {
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
        switch (v.getId()) {
            case R.id.btn_discard_record:
                showAlertDialog();
                break;
            case R.id.btn_send_record:
                Intent intent = new Intent(this, TunaNetraSendActivity.class);
                // PASSING REQUEST DATA
                intent.putExtra(TunaNetraSendActivity.EXTRA_REQUEST_DETAIL, getIntent().getParcelableExtra(EXTRA_REQUEST_DETAIL));
                // PASSING RECORDED AUDIO PATH
                intent.putExtra(TunaNetraSendActivity.EXTRA_PATH_RECORD, outputPath);
                startActivity(intent);
                break;
        }
    }

    private void showAlertDialog() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_discard_record);

        RelativeLayout dimBackground = (RelativeLayout) dialog.findViewById(R.id.dim_background_discard_record);
        dimBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button btnYes = (Button) dialog.findViewById(R.id.btn_discard_record_dialog_yes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                resetRecord();
            }
        });

        Button btnNo = (Button) dialog.findViewById(R.id.btn_discard_dialog_dialog_no);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            timeInMilis = SystemClock.uptimeMillis() - startHTime;
            updatedTime = timeSwapBuff + timeInMilis;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            if (tvRecordMessage != null) {
                tvRecordMessage.setText("" + String.format("%02d", mins) + ":" + String.format("%02d", secs));
                handler.postDelayed(this, 0);
            }
        }
    };

    private void resetRecord() {
        if (mediaPlayer != null){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }


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

    private void resetRecordMessage() {
        startHTime = 0L;
        timeInMilis = 0L;
        timeSwapBuff = 0L;
        updatedTime = 0L;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
    }
}
