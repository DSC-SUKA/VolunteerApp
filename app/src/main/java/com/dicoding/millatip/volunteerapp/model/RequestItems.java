package com.dicoding.millatip.volunteerapp.model;

import com.google.gson.annotations.SerializedName;

public class RequestItems {
    @SerializedName("request_id")
    public int id;

    @SerializedName("request_time")
    public String time;

    @SerializedName("requester.name")
    public String requesterName;

    @SerializedName("requester.nim")
    public String requsterNim;

    @SerializedName("requester.prodi")
    public String requsterProdi;

    @SerializedName("requester.photo")
    public String requesterPhoto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getRequsterNim() {
        return requsterNim;
    }

    public void setRequsterNim(String requsterNim) {
        this.requsterNim = requsterNim;
    }

    public String getRequsterProdi() {
        return requsterProdi;
    }

    public void setRequsterProdi(String requsterProdi) {
        this.requsterProdi = requsterProdi;
    }

    public String getRequesterPhoto() {
        return requesterPhoto;
    }

    public void setRequesterPhoto(String requesterPhoto) {
        this.requesterPhoto = requesterPhoto;
    }
}
