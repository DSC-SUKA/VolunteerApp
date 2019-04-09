package com.dsc.suka.volunteerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignUpModel {

    @SerializedName("sign_up")
    private List<ResponseSignup> mSignUpItems;

    public SignUpModel(List<ResponseSignup> signUpItems) {
        mSignUpItems = signUpItems;
    }

    public List<ResponseSignup> getmSignUpItems() {
        return mSignUpItems;
    }

    public void setmSignUpItems(List<ResponseSignup> mSignUpItems) {
        this.mSignUpItems = mSignUpItems;
    }

}
