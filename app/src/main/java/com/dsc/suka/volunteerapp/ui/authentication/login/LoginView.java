package com.dsc.suka.volunteerapp.ui.authentication.login;

import com.dsc.suka.volunteerapp.model.UserData;
import com.dsc.suka.volunteerapp.model.UserLoginDetail;

public interface LoginView {
    void showLoading();
    void hideLoading();
    void onSuccessLogin(UserLoginDetail userLoginDetail);
    void onErrorLogin(String errorMessage);
    void onResponseUserData(UserData userData);
}
