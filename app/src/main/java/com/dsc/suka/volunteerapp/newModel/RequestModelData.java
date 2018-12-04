package com.dsc.suka.volunteerapp.newModel;

import com.google.gson.annotations.SerializedName;

public class RequestModelData {

    @SerializedName("user_avatar_url")
    public String avatarURL;

    @SerializedName("user_name")
    public String userName;

    @SerializedName("audio_url")
    public String audioUrl;

    @SerializedName("user_id")
    public String userId;

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
