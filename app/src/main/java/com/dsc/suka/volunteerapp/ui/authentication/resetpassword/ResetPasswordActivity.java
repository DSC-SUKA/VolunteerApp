package com.dsc.suka.volunteerapp.ui.authentication.resetpassword;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dsc.suka.volunteerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ResetPasswordActivity extends AppCompatActivity {

    TextInputLayout inputEmail;
    Button btnResetPass;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        auth = FirebaseAuth.getInstance();

        inputEmail = (TextInputLayout)findViewById(R.id.inputEmail_resetPass);
        btnResetPass = (Button)findViewById(R.id.btnReset_resetPass);

        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = Objects.requireNonNull(inputEmail.getEditText()).getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), R.string.enter_registered_email, Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.e("TAG", email);
                                if (task.isSuccessful()) {
                                    Toast.makeText(ResetPasswordActivity.this, R.string.go_check_email, Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), getString(R.string.failed_reset_password), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
