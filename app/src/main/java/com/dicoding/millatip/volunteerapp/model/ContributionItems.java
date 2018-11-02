package com.dicoding.millatip.volunteerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ContributionItems implements Parcelable {
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

    @SerializedName("requester.type")
    public String requesterType;

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

    public String getRequesterType() {
        return requesterType;
    }

    public void setRequesterType(String requesterType) {
        this.requesterType = requesterType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.time);
        dest.writeString(this.requesterName);
        dest.writeString(this.requsterNim);
        dest.writeString(this.requsterProdi);
        dest.writeString(this.requesterType);
        dest.writeString(this.requesterPhoto);
    }

    public ContributionItems() {
    }

    protected ContributionItems(Parcel in) {
        this.id = in.readInt();
        this.time = in.readString();
        this.requesterName = in.readString();
        this.requsterNim = in.readString();
        this.requsterProdi = in.readString();
        this.requesterType = in.readString();
        this.requesterPhoto = in.readString();
    }

    public static final Parcelable.Creator<ContributionItems> CREATOR = new Parcelable.Creator<ContributionItems>() {
        @Override
        public ContributionItems createFromParcel(Parcel source) {
            return new ContributionItems(source);
        }

        @Override
        public ContributionItems[] newArray(int size) {
            return new ContributionItems[size];
        }
    };
}
