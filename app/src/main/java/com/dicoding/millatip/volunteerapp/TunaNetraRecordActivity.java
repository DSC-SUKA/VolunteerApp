package com.dicoding.millatip.volunteerapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dicoding.millatip.volunteerapp.fragment.TunaNetraAlertDiscardDialog;

public class TunaNetraRecordActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String EXTRA_REQUEST_ID = "request_id_extras";
    private Button btnSend, btnDiscard;
    private ImageButton btnRecord;
    private boolean isRecording, isRecorded, isRecordPaused;
    private TextView tvRecordMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuna_netra_record);

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

        btnRecord.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (isRecorded == false){
                    btnRecord.setImageDrawable(getResources().getDrawable(R.mipmap.btn_stop));
                    tvRecordMessage.setText("00:01");
                    isRecording = true;
                    return true;
                }
                return false;
            }
        });

        btnRecord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isRecording){
                    switch (event.getAction()){
                        case MotionEvent.ACTION_UP:
                            setPlayButton();
                            tvRecordMessage.setText("00:30");
                            isRecorded = true;
                            btnSend.setEnabled(true);
                            btnDiscard.setEnabled(true);
                            isRecording = !isRecording;
                            return true;
                    }
                } else if (isRecorded){
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            if (isRecordPaused){
                                setStopButton();
                                isRecordPaused = !isRecordPaused;
                            } else {
                                setPlayButton();
                                isRecordPaused = !isRecordPaused;
                            }
                            return true;
                    }
                }
                return false;
            }


        });


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

    private void resetRecord() {
        isRecorded = false;
        isRecordPaused = true;
        isRecording = false;
        setRecordButton();
        btnSend.setEnabled(false);
        btnDiscard.setEnabled(false);
    }

}
