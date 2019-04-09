package com.dsc.suka.volunteerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ResponseAudioSingle implements Parcelable {
    @SerializedName("docid")
    private String docid;
    @SerializedName("user_avatar_url")
    private String user_avatar_url;
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("category")
    private String category;
    @SerializedName("user_dif_id")
    private String user_dif_id;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("user_dif_name")
    private String user_dif_name;
    @SerializedName("audio_url")
    private String audio_url;
    @SerializedName("audio_request_id")
    private String audio_request_id;

    public String getDocid() {
        return docid;
    }

    public String getUser_avatar_url() {
        return user_avatar_url;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getCategory() {
        return category;
    }

    public String getUser_dif_id() {
        return user_dif_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_dif_name() {
        return user_dif_name;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public String getAudio_request_id() {
        return audio_request_id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.docid);
        dest.writeString(this.user_avatar_url);
        dest.writeString(this.user_name);
        dest.writeString(this.category);
        dest.writeString(this.user_dif_id);
        dest.writeString(this.user_id);
        dest.writeString(this.user_dif_name);
        dest.writeString(this.audio_url);
        dest.writeString(this.audio_request_id);
    }

    public ResponseAudioSingle() {
    }

    protected ResponseAudioSingle(Parcel in) {
        this.docid = in.readString();
        this.user_avatar_url = in.readString();
        this.user_name = in.readString();
        this.category = in.readString();
        this.user_dif_id = in.readString();
        this.user_id = in.readString();
        this.user_dif_name = in.readString();
        this.audio_url = in.readString();
        this.audio_request_id = in.readString();
    }

    public static final Parcelable.Creator<ResponseAudioSingle> CREATOR = new Parcelable.Creator<ResponseAudioSingle>() {
        @Override
        public ResponseAudioSingle createFromParcel(Parcel source) {
            return new ResponseAudioSingle(source);
        }

        @Override
        public ResponseAudioSingle[] newArray(int size) {
            return new ResponseAudioSingle[size];
        }
    };
}
