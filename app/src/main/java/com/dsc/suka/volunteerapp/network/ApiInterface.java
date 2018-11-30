package com.dsc.suka.volunteerapp.network;

import com.dsc.suka.volunteerapp.model.ContributionModel;
import com.dsc.suka.volunteerapp.model.RequestModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("request_contribution")
    Call<RequestModel> getRequests();

    @GET("latest_contribution")
    Call<ContributionModel> getMyContribution();

    @GET("latest_contribution")
    Call<ContributionModel> getLatestContribution();

}
