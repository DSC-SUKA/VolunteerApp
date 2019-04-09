package com.dsc.suka.volunteerapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dsc.suka.volunteerapp.model.UserData;

public class SessionManager {
    private SharedPreferences mSharedPrefs;
    private SharedPreferences.Editor mEditor;

    private static final String IS_LOGIN = "is_login";
    private static final String SESSION_NAME = "vol_app_session";
    private static final String KEY_UID = "key_uid";
    private static final String KEY_EMAIL = "key_email";
    private static final String KEY_EMAIL_VERIFIED = "key_email_verified";
    private static final String KEY_PHONE_NUMBER = "key_phone_number";
    private static final String KEY_PHOTO_URL = "key_photo_url";
    private static final String KEY_DISPLAY_NAME= "key_display_name";

    public SessionManager(Context context){
        mSharedPrefs = context.getSharedPreferences(SESSION_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPrefs.edit();
    }

    public void createSession(UserData userData){
        mEditor.putBoolean(IS_LOGIN, true);
        mEditor.putString(KEY_UID, userData.getUserId());
        mEditor.putString(KEY_EMAIL, userData.getUserEmail());
        mEditor.putBoolean(KEY_EMAIL_VERIFIED, userData.getUserEmailVerified());
        mEditor.putString(KEY_PHONE_NUMBER, userData.getUserPhone());
        mEditor.putString(KEY_PHOTO_URL, userData.getUserPhotoUrl());
        mEditor.putString(KEY_DISPLAY_NAME, userData.getUserName());
        mEditor.commit();
    }

    public boolean isLogin(){
        return mSharedPrefs.getBoolean(IS_LOGIN, false);
    }

    public void deleteSession(){
        mEditor.clear();
        mEditor.commit();
    }

    public UserData getUserData(){
        return new UserData(
                mSharedPrefs.getString(KEY_UID, null),
                mSharedPrefs.getString(KEY_EMAIL, null),
                mSharedPrefs.getBoolean(KEY_EMAIL_VERIFIED, false),
                mSharedPrefs.getString(KEY_PHONE_NUMBER, null),
                mSharedPrefs.getString(KEY_PHOTO_URL, null),
                mSharedPrefs.getString(KEY_DISPLAY_NAME, null)
        );
    }
}
