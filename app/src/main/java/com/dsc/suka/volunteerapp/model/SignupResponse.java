package com.dsc.suka.volunteerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SignUpItems implements Parcelable {

    @SerializedName("status")
    private boolean status;
    @SerializedName("message")
    private String message;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("phone_number")
    private String phone_number;
    @SerializedName("name")
    private String name;
    @SerializedName("image_url")
    private String image_url;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    private SignUpItems(Parcel in) {
        this.email = in.readString();
        this.password = in.readString();
        this.name = in.readString();
        this.phone_number = in.readString();
        this.image_url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.phone_number);
        dest.writeString(this.name);
        dest.writeString(this.image_url);
    }

    public static final Creator<SignUpItems> CREATOR = new Creator<SignUpItems>() {
        @Override
        public SignUpItems createFromParcel(Parcel in) {
            return new SignUpItems(in);
        }

        @Override
        public SignUpItems[] newArray(int size) {
            return new SignUpItems[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
