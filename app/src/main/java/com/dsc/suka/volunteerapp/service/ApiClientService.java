package com.dsc.suka.volunteerapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientService {

    public static final String BASE_URL = "https://demo4098679.mockable.io/";

    //tesya
    public static final String base_url = "https://suka-care-dev.appspot.com/";

    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
