package com.dsc.suka.volunteerapp.model;

import com.google.gson.annotations.SerializedName;

public class ResponseSignup {
    @SerializedName("status")
    private boolean status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private UserData userData;

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public UserData getUserData() {
        return userData;
    }
}
