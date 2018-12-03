package com.dsc.suka.volunteerapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserModel implements Parcelable {

    @SerializedName("userId")
    private String userId;
    @SerializedName("emailAddress")
    private String emailAdrs;
    @SerializedName("phoneNumb")
    private String phoneNumb;
    @SerializedName("name")
    private String name;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("userAvatar")
    private String userAva;

    public String getEmailAdrs() {
        return emailAdrs;
    }

    public void setEmailAdrs(String emailAdrs) {
        this.emailAdrs = emailAdrs;
    }

    public String getPhoneNumb() {
        return phoneNumb;
    }

    public void setPhoneNumb(String phoneNumb) {
        this.phoneNumb = phoneNumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return userId;
    }

    public void setUid(String userId) {
        this.userId = userId;
    }

    public void setUserAva(String userAva) {
        this.userAva = userAva;
    }

    public String getUserAva() {
        return userAva;
    }

    private UserModel(Parcel in) {
        userId = in.readString();
        phoneNumb = in.readString();
        emailAdrs = in.readString();
        name = in.readString();
        imageUrl = in.readString();
        userAva = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(phoneNumb);
        dest.writeString(emailAdrs);
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeString(userAva);
    }
}
