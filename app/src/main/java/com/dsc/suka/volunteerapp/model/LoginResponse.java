package com.dsc.suka.volunteerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RespObjLogin {

    @SerializedName("email")
    public String mEmail;
    @SerializedName("password")
    public String mPassword;
    @SerializedName("status")
    public boolean statusLogin;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public List<UserLoginDetail> data = new ArrayList<>();

//    public RespObjLogin (String email, String password){
//        this.mEmail = email;
//        this.mPassword = password;
//    }

    public String getMessage() {
        return message;
    }

    public boolean getStatusLogin() {
        return statusLogin;
    }

    public class UserLoginDetail {
        @SerializedName("uid")
        public String user_id;
        @SerializedName("role")
        public String user_role;

        public String getUserId() {
            return user_id;
        }

        public String getUserRole() {
            return user_role;
        }
    }

}
