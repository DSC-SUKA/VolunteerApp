package com.dsc.suka.volunteerapp.loginActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dsc.suka.volunteerapp.R;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout inputEmail, inputPass;
    Button btnLogin, btnResetPassword, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (TextInputLayout)findViewById(R.id.inputEmail_login);
        inputPass = (TextInputLayout)findViewById(R.id.inputText_email);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnResetPassword = (Button)findViewById(R.id.btnResetPass_login);
        btnRegister = (Button)findViewById(R.id.btnRegister_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Objects.requireNonNull(inputEmail.getEditText()).getText().toString();
                Toast.makeText(getApplicationContext(), email, Toast.LENGTH_SHORT).show();
            }
        });

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });




    }
}
