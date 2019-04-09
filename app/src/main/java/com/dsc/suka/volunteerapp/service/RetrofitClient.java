package com.dsc.suka.volunteerapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

//    private static ApiInterfaceMock service;

//    public static final String BASE_URL = "https://demo4098679.mockable.io/";

//    private static final String base_url =

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String url){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
//
//            service = retrofit.create(ApiInterfaceMock.class);
        }
        return  retrofit;
    }

}
