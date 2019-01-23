package com.dsc.suka.volunteerapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientService {

//    private static ApiInterfaceService service;

//    public static final String BASE_URL = "https://demo4098679.mockable.io/";

    private static final String base_url =

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String base_url){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(ApiInterfaceService.class);
        }
        return  retrofit;
    }


    public static ApiClientService getUserService() {
        return
    }
}
