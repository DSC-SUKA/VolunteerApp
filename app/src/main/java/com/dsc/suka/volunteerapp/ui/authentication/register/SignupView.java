package com.dsc.suka.volunteerapp.ui.authentication.register;

import com.dsc.suka.volunteerapp.model.ResponseImageUpload;
import com.dsc.suka.volunteerapp.model.UserData;

public interface SignupView {
    void showLoading();
    void hideLoading();
    void onSuccessRegister(UserData userData);
    void onErrorRegister(String errorMessage);
    void onSuccessUploadImage(ResponseImageUpload responseImageUpload);
    void onErrorUploadImage(String message);
}
