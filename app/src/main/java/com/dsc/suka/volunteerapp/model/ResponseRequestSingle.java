package com.dsc.suka.volunteerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ResponseRequestSingle implements Parcelable {

    @SerializedName("docid")
    private String docid;
    @SerializedName("user_avatar_url")
    private String avatarURL;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("audio_url")
    private String audioUrl;
    @SerializedName("user_id")
    private String userId;

    public String getDocid() {
        return docid;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public String getUserName() {
        return userName;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public String getUserId() {
        return userId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.docid);
        dest.writeString(this.avatarURL);
        dest.writeString(this.userName);
        dest.writeString(this.audioUrl);
        dest.writeString(this.userId);
    }

    public ResponseRequestSingle() {
    }

    protected ResponseRequestSingle(Parcel in) {
        this.docid = in.readString();
        this.avatarURL = in.readString();
        this.userName = in.readString();
        this.audioUrl = in.readString();
        this.userId = in.readString();
    }

    public static final Parcelable.Creator<ResponseRequestSingle> CREATOR = new Parcelable.Creator<ResponseRequestSingle>() {
        @Override
        public ResponseRequestSingle createFromParcel(Parcel source) {
            return new ResponseRequestSingle(source);
        }

        @Override
        public ResponseRequestSingle[] newArray(int size) {
            return new ResponseRequestSingle[size];
        }
    };
}
