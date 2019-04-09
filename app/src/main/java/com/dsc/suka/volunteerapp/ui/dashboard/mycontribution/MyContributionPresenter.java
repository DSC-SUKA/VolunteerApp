package com.dsc.suka.volunteerapp.ui.dashboard.mycontribution;


import android.util.Log;

import com.dsc.suka.volunteerapp.model.ResponseAudioAll;
import com.dsc.suka.volunteerapp.service.ApiInterface;
import com.dsc.suka.volunteerapp.service.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyContributionPresenter {
    private ApiInterface mApiInterface;
    private MyContributionView mView;

    public MyContributionPresenter(MyContributionView view){
        mView = view;
        mApiInterface = ApiUtils.getUserService();
    }

    void getUserContribution(String userId){
        mView.showLoading();
        Call<ResponseAudioAll> call = mApiInterface.getUserContribution(userId);
        call.enqueue(new Callback<ResponseAudioAll>() {
            @Override
            public void onResponse(Call<ResponseAudioAll> call, Response<ResponseAudioAll> response) {
                mView.hideLoading();
                if (response.body() != null){
                    Log.d("MyContributionPresenter", "Response Count =" + response.body().getData().size());
                    mView.showContributionList(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ResponseAudioAll> call, Throwable t) {
                mView.hideLoading();
                Log.d("MyContributionPresenter", t.getLocalizedMessage());
            }
        });
    }
}
