package com.dsc.suka.volunteerapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AudioModel implements Parcelable {

    @SerializedName("audioReq")
    private String audioReq;
    @SerializedName("category")
    private String category;
    @SerializedName("audioResp")
    private String audioResp;


    public String getAudioReq() {
        return audioReq;
    }

    public void setAudioReq(String audioReq) {
        this.audioReq = audioReq;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAudioResp() {
        return audioResp;
    }

    public void setAudioResp(String audioResp) {
        this.audioResp = audioResp;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AudioModel> CREATOR = new Creator<AudioModel>() {
        @Override
        public AudioModel createFromParcel(Parcel in) {
            return new AudioModel(in);
        }

        @Override
        public AudioModel[] newArray(int size) {
            return new AudioModel[size];
        }
    };

    private AudioModel(Parcel in) {
        audioReq = in.readString();
        category = in.readString();
        audioResp = in.readString();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(audioReq);
        dest.writeString(category);
        dest.writeString(audioResp);
    }
}
