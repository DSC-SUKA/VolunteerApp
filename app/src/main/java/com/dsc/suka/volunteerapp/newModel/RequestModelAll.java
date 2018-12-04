package com.dsc.suka.volunteerapp.newModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestModelAll {

    @SerializedName("status")
    public boolean status;

    @SerializedName("data")
    public List<RequestModelData> data;

    @SerializedName("message")
    public String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<RequestModelData> getData() {
        return data;
    }

    public void setData(List<RequestModelData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
