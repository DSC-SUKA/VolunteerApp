package com.dsc.suka.volunteerapp.loginActivities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dsc.suka.volunteerapp.R;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout inputEmail, inputPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputEmail = (TextInputLayout)findViewById(R.id.inputEmail_SignUp);
        inputPassword = (TextInputLayout)findViewById(R.id.inputPass_SignUp);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
