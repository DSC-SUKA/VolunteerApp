package com.dsc.suka.volunteerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RequestResponse {

    @SerializedName("status")
    private boolean status;
    @SerializedName("data")
    public List<RequestedBookDetail> data = new ArrayList<>();

    public boolean getStatus() {
        return status;
    }

    public List<RequestedBookDetail> getData() {
        return data;
    }




}
