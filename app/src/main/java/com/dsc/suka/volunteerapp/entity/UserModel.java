package com.dsc.suka.volunteerapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UsersModel implements Parcelable {

    @SerializedName("userId")
    private int uid;
    @SerializedName("emailAddress")
    private int emailAdrs;
    @SerializedName("phoneNumb")
    private int phoneNumb;
    @SerializedName("name")
    private int name;
    @SerializedName("imageUrl")
    private int imageUrl;

    public int getEmailAdrs() {
        return emailAdrs;
    }

    public void setEmailAdrs(int emailAdrs) {
        this.emailAdrs = emailAdrs;
    }

    public int getPhoneNumb() {
        return phoneNumb;
    }

    public void setPhoneNumb(int phoneNumb) {
        this.phoneNumb = phoneNumb;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    private UsersModel(Parcel in) {
        uid = in.readInt();
    }

    public static final Creator<UsersModel> CREATOR = new Creator<UsersModel>() {
        @Override
        public UsersModel createFromParcel(Parcel in) {
            return new UsersModel(in);
        }

        @Override
        public UsersModel[] newArray(int size) {
            return new UsersModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
    }
}
