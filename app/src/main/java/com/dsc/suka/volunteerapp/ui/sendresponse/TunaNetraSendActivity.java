package com.dsc.suka.volunteerapp.ui.sendresponse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.model.ResponseRequestSingle;
import com.dsc.suka.volunteerapp.ui.dashboard.MainActivity;
import com.dsc.suka.volunteerapp.utils.SessionManager;

public class TunaNetraSendActivity extends AppCompatActivity implements TunaNetraSendView, View.OnClickListener, AdapterView.OnItemSelectedListener {
    private EditText edtTitle, edtCategory;
//    private Spinner spinnerCategory;
    private Button btnSubmit;
    private String responseTitle, responseCategory;
    private ResponseRequestSingle requestDetail;
    private String outputPath;
    private SessionManager mSessionManager;
    private TunaNetraSendPresenter mPresenter;

    public static String EXTRA_REQUEST_DETAIL = "request_detail_extras";
    public static String EXTRA_PATH_RECORD = "path_record_extras";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuna_netra_input_send);

        initView();
        initComponent();
    }

    private void initView() {
        edtTitle = findViewById(R.id.edt_title);

        edtCategory = findViewById(R.id.edt_category);
        edtCategory.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null) {
                    responseCategory = editable.toString().trim();
                    btnSubmit.setEnabled(true);
                }
            }
        });

//        spinnerCategory = findViewById(R.id.spinner_category);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_category, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCategory.setAdapter(adapter);

        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
        btnSubmit.setEnabled(false);
    }

    private void initComponent() {
        mSessionManager = new SessionManager(this);
        mPresenter = new TunaNetraSendPresenter(this);
        outputPath = getIntent().getStringExtra(EXTRA_PATH_RECORD);
        requestDetail = getIntent().getParcelableExtra(EXTRA_REQUEST_DETAIL);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_submit) {
            sendResponse();
        }
    }

    private void sendResponse() {
        mPresenter.sendResponse(outputPath, responseCategory, mSessionManager.getUserData(), requestDetail);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        spinnerSelected = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccessSendAudio(String message) {
        Toast.makeText(TunaNetraSendActivity.this, message, Toast.LENGTH_SHORT).show();

        // BACK TO DASHBOARD
        Intent intent = new Intent(TunaNetraSendActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onErrorSendAudio(String error) {
        Toast.makeText(TunaNetraSendActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}
