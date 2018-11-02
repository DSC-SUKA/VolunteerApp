package com.dicoding.millatip.volunteerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestModel {
    @SerializedName("requests")
    private List<RequestItems> requestItems;

    public RequestModel(List<RequestItems> requestItems){
        this.requestItems = requestItems;
    }

    public List<RequestItems> getRequestItems() {
        return requestItems;
    }

    public void setRequestItems(List<RequestItems> requestItems) {
        this.requestItems = requestItems;
    }
}
