package com.dsc.suka.volunteerapp.service;

import com.dsc.suka.volunteerapp.model.ContributionModel;
import com.dsc.suka.volunteerapp.model.RequestModel;
import com.dsc.suka.volunteerapp.newModel.RequestModelAll;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaceService {

    @GET("request")
    Call<RequestModelAll> getRequests();

    @GET("my_contribution")
    Call<RequestModel> getContribution();

    @GET("latest_contribution")
    Call<ContributionModel> getLatestContribution();

}
