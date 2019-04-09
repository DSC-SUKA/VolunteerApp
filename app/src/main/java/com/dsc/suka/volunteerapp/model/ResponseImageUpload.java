package com.dsc.suka.volunteerapp.model;

import com.google.gson.annotations.SerializedName;

public class ResponseImageUpload {
    @SerializedName("status")
    public boolean status;
    @SerializedName("data")
    public String data;
    @SerializedName("message")
    public String message;

    public boolean getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}