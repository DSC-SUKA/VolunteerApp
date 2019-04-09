package com.dsc.suka.volunteerapp.model;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("status")
    private boolean statusLogin;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private UserLoginDetail data = new UserLoginDetail();

    public String getMessage() {
        return message;
    }

    public boolean getStatusLogin() {
        return statusLogin;
    }

    public UserLoginDetail getData() {
        return data;
    }
}
