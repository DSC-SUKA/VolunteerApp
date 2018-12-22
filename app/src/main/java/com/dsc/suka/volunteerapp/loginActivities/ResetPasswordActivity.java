package com.dsc.suka.volunteerapp.loginActivities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dsc.suka.volunteerapp.R;

public class ResetPasswordActivity extends AppCompatActivity {

    TextInputLayout inputEmail;
    Button btnResetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        inputEmail = (TextInputLayout)findViewById(R.id.inputEmail_resetPass);
        btnResetPass = (Button)findViewById(R.id.btnResetPass_login);

        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "reset password successful", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
