package com.dsc.suka.volunteerapp.presenter;

import android.util.Log;

import com.dsc.suka.volunteerapp.model.ContributionItems;
import com.dsc.suka.volunteerapp.model.ContributionModel;
import com.dsc.suka.volunteerapp.network.ApiInterface;
import com.dsc.suka.volunteerapp.view.ContributionView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContributionPresenter {
    private ContributionView mView;
    private ApiInterface mApiInterface;

    public ContributionPresenter(ContributionView contributionView, ApiInterface apiInterface) {
        this.mView = contributionView;
        this.mApiInterface = apiInterface;
    }

    public void getLatestContributionList(){
        mView.showLoading();
        Call<ContributionModel> contributionModelCall = mApiInterface.getLatestContribution();
        contributionModelCall.enqueue(new Callback<ContributionModel>() {
            @Override
            public void onResponse(Call<ContributionModel> call, Response<ContributionModel> response) {
                List<ContributionItems> contributionItemsList = response.body().getmContributionItems();
                Log.d("Retrofit Get", "Request Count: " + String.valueOf(contributionItemsList.size()));
                mView.hideLoading();
                mView.showContributionList(contributionItemsList);
            }

            @Override
            public void onFailure(Call<ContributionModel> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void getMyContributionList(){
        mView.showLoading();
        Call<ContributionModel> contributionModelCall = mApiInterface.getMyContribution();
        contributionModelCall.enqueue(new Callback<ContributionModel>() {
            @Override
            public void onResponse(Call<ContributionModel> call, Response<ContributionModel> response) {
                List<ContributionItems> contributionItemsList = response.body().getmContributionItems();
                Log.d("Retrofit Get", "Request Count: " + String.valueOf(contributionItemsList.size()));
                mView.hideLoading();
                mView.showContributionList(contributionItemsList);
            }

            @Override
            public void onFailure(Call<ContributionModel> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
