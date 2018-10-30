package com.dicoding.millatip.volunteerapp.rest;

import com.dicoding.millatip.volunteerapp.model.RequestModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("request_contribution")
    Call<RequestModel> getRequests();
}
