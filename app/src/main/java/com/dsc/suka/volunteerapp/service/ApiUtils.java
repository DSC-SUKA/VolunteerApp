package com.dsc.suka.volunteerapp.service;

public class ApiUtils {

    public static final String BASE_URL = "https://suka-care-dev.appspot.com/";

    public static ApiInterface getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
