package com.dsc.suka.volunteerapp.ui.authentication.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.model.UserData;
import com.dsc.suka.volunteerapp.model.UserLoginDetail;
import com.dsc.suka.volunteerapp.ui.dashboard.MainActivity;
import com.dsc.suka.volunteerapp.ui.authentication.register.SignupActivity;
import com.dsc.suka.volunteerapp.utils.SessionManager;

public class LoginActivity extends AppCompatActivity implements LoginView{
    private TextInputLayout inputEmail, inputPass;
    private ProgressBar pbLogin;
    private LoginPresenter mPresenter;
    private SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        renderView();
        initComponent();
        checkSession();
    }

    private void renderView() {
        ImageView logo = findViewById(R.id.img_logo);
        pbLogin = findViewById(R.id.progressbar_login);
        inputEmail = findViewById(R.id.inputEmail_login);
        inputPass = findViewById(R.id.inputPass_login);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnResetPassword = findViewById(R.id.btn_forgotPassword_login);
        Button btnRegister = findViewById(R.id.btn_register_login);

        Glide.with(this)
                .load(R.drawable.logo)
                .into(logo);

        pbLogin.setVisibility(View.INVISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginNow();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initComponent() {
        mPresenter = new LoginPresenter(this);
        mSessionManager = new SessionManager(this);
    }

    private void checkSession() {
        if (mSessionManager.isLogin()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    private void loginNow() {
        String email = inputEmail.getEditText().getText().toString();
        String password = inputPass.getEditText().getText().toString();
        if (validateLogin(email, password)){
            mPresenter.doLogin(email, password);
        }
    }

    private boolean validateLogin(String email, String password) {
        if (email == null || email.trim().length() == 0) {
            Toast.makeText(this, R.string.email_isrequired, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, R.string.password_isrequired, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void showLoading() {
        pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLogin.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSuccessLogin(UserLoginDetail userLoginDetail) {
        if (userLoginDetail.getUserRole().equals("volunteer")){
            mPresenter.getUserData(userLoginDetail.getUserId());
        } else {
            Toast.makeText(this, "This App is for Volunteer", Toast.LENGTH_SHORT).show();
            this.hideLoading();
        }
    }

    @Override
    public void onErrorLogin(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponseUserData(UserData userData) {
        Toast.makeText(this, "Login Successfull!", Toast.LENGTH_SHORT).show();
        mSessionManager.createSession(userData);
        checkSession();
    }
}
