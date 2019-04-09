package com.dsc.suka.volunteerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseAudioAll {
    @SerializedName("status")
    private boolean status;
    @SerializedName("message")
    private  String message;
    @SerializedName("data")
    private  List<ResponseAudioSingle> data = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public boolean getStatus() {
        return status;
    }

    public List<ResponseAudioSingle> getData() {
        return data;
    }
}
