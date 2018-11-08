package com.dsc.suka.volunteerapp.rest;

import com.dsc.suka.volunteerapp.model.ContributionModel;
import com.dsc.suka.volunteerapp.model.RequestModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("request_contribution")
    Call<RequestModel> getRequests();

    @GET("my_contribution")
    Call<RequestModel> getContribution();

    @GET("latest_contribution")
    Call<ContributionModel> getLatestContribution();

}
