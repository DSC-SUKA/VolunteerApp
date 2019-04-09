package com.dsc.suka.volunteerapp.ui.authentication.register;

import android.Manifest;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.model.UserData;
import com.dsc.suka.volunteerapp.ui.dashboard.MainActivity;
import com.dsc.suka.volunteerapp.model.ResponseImageUpload;
import com.dsc.suka.volunteerapp.utils.PermissionManager;
import com.dsc.suka.volunteerapp.utils.SessionManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignupActivity extends AppCompatActivity implements SignupView {
    private TextInputLayout inputEmail, inputPassword, inputPhoneNumb, inputName;
    private ProgressBar pbSignup;
    private CircleImageView userAvatar;
    private SignupPresenter mPresenter;
    private SessionManager mSessionManager;
    private String imagePath, imageUrl;
    private String[] permissionNeeded = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private final int REQUEST_CODE_SIGNUP_ACTIVITY = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        renderView();
        initComponent();
    }

    private void renderView() {
        inputEmail = findViewById(R.id.inputEmail_SignUp);
        inputPassword = findViewById(R.id.inputPass_SignUp);
        inputPhoneNumb = findViewById(R.id.inputPhone_SignUp);
        inputName = findViewById(R.id.inputName_SignUp);
        Button btnChoosePict = findViewById(R.id.btnChoosePict_SignUp);
        Button btnUploadImage = findViewById(R.id.btnUploadPict_Signup);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        pbSignup = findViewById(R.id.progressbar_signup);
        userAvatar = findViewById(R.id.civ_ava);

        pbSignup.setVisibility(View.INVISIBLE);

        btnChoosePict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (PermissionManager.hasPermissions(SignupActivity.this, permissionNeeded)) {
                    pickImage();
                } else {
                    Toast.makeText(SignupActivity.this, R.string.toast_need_permission, Toast.LENGTH_SHORT).show();
                    requestPermisson();
                }

            }
        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupNow();
            }
        });
    }

    private void initComponent() {
        mPresenter = new SignupPresenter(this);
        mSessionManager = new SessionManager(this);
    }

    private void requestPermisson() {
        PermissionManager.requestPermissions(this, permissionNeeded, REQUEST_CODE_SIGNUP_ACTIVITY);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_SIGNUP_ACTIVITY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.toast_permission_granted, Toast.LENGTH_SHORT).show();
                pickImage();
            } else {
                Toast.makeText(this, R.string.toast_permission_denied, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    private void uploadImage() {
        if (!imagePath.isEmpty())
            mPresenter.uploadPhoto(imagePath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            imagePath = getRealPathFromUri(imageUri);

            RequestOptions requestOptions = new RequestOptions().centerCrop();
            Glide.with(this)
                    .load(imageUri)
                    .apply(requestOptions)
                    .into(userAvatar);

        } else {
            Toast.makeText(this, R.string.toast_not_pick_image, Toast.LENGTH_SHORT).show();
        }
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_idx);
        cursor.close();
        return result;
    }

    private void checkSession() {
        if (mSessionManager.isLogin()) {
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    private boolean validateSignUp(String email, String password, String name, String phoneNumb) {
        if (email == null || email.trim().length() == 0) {
            Toast.makeText(this, R.string.email_isrequired, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() < 8) {
            Toast.makeText(this, R.string.password_isrequired, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (name == null || name.trim().length() == 0) {
            Toast.makeText(this, R.string.name_isrequired, Toast.LENGTH_SHORT).show();
        }
        String regexStr = "^[+]?[0-9]{10,13}$";
        if (phoneNumb == null || phoneNumb.trim().length() == 0) {
            Toast.makeText(this, R.string.phoneNumb_isrequired, Toast.LENGTH_SHORT).show();
        }
        if (!phoneNumb.matches(regexStr)) {
            Toast.makeText(this, R.string.enter_valid_number, Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void signupNow() {
        String email = inputEmail.getEditText().getText().toString();
        String password = inputPassword.getEditText().getText().toString();
        String name = inputName.getEditText().getText().toString();
        String phoneNumb = inputPhoneNumb.getEditText().getText().toString();
        String role = "volunteer";

        if (validateSignUp(email, password, name, phoneNumb)) {
            mPresenter.doSignUp(email, password, phoneNumb, name, imageUrl + "?alt=media&token=", role);
        }
    }

    @Override
    public void showLoading() {
        pbSignup.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbSignup.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSuccessRegister(UserData userData) {
        Toast.makeText(this, R.string.toast_register_successfull, Toast.LENGTH_SHORT).show();
        mSessionManager.createSession(userData);
        checkSession();
    }

    @Override
    public void onErrorRegister(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessUploadImage(ResponseImageUpload responseImageUpload) {
        if (responseImageUpload.getStatus()) {
            imageUrl = responseImageUpload.getData();
            Toast.makeText(this, R.string.toast_image_uploaded, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onErrorUploadImage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
