package com.dsc.suka.volunteerapp.model;

import com.google.gson.annotations.SerializedName;

public class RequestedBookDetail {

        @SerializedName("docid")
        public String docid;
        @SerializedName("user_id")
        public String user_id;
        @SerializedName("user_avatar_url")
        public String user_avatar_url;
        @SerializedName("user_name")
        public String user_name;
        @SerializedName("audio_url")
        public String audio_url;

        public String getDocId() {
            return this.docid;
        }

        public String getUserId() {
            return user_id;
        }

        public String getUserAvatar() {
            return user_avatar_url;
        }

        public String getUserName() {
            return user_name;
        }

        public String getAudio() {
            return audio_url;
        }
}
