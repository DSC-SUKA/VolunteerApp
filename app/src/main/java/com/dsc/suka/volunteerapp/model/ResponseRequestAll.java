package com.dsc.suka.volunteerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseRequestAll {
    @SerializedName("status")
    private boolean status;
    @SerializedName("data")
    private List<ResponseRequestSingle> data;
    @SerializedName("message")
    private String message;

    public boolean getStatus() {
        return status;
    }

    public List<ResponseRequestSingle> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
