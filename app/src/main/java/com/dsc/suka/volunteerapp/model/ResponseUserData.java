package com.dsc.suka.volunteerapp.model;

import com.google.gson.annotations.SerializedName;

public class ResponseUserData {
    @SerializedName("status")
    private boolean status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private UserData data;

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public UserData getData() {
        return data;
    }
}
