package com.dsc.suka.volunteerapp.model;

import com.google.gson.annotations.SerializedName;

public class UserLoginDetail {
    @SerializedName("uid")
    private String user_id;
    @SerializedName("role")
    private String user_role;

    public String getUserId() {
        return user_id;
    }

    public String getUserRole() {
        return user_role;
    }

}
