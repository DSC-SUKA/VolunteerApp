package com.dsc.suka.volunteerapp.ui.dashboard.home;

import android.util.Log;

import com.dsc.suka.volunteerapp.model.ResponseAudioAll;
import com.dsc.suka.volunteerapp.service.ApiInterface;
import com.dsc.suka.volunteerapp.service.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {
    private ApiInterface mApiInterface;
    private HomeView mView;

    public HomePresenter(HomeView mView){
        mApiInterface = ApiUtils.getUserService();
        this.mView = mView;
    }

    void getAllResponses(){
        mView.showLoading();
        Call<ResponseAudioAll> call = mApiInterface.getAllResponses();
        call.enqueue(new Callback<ResponseAudioAll>() {
            @Override
            public void onResponse(Call<ResponseAudioAll> call, Response<ResponseAudioAll> response) {
                mView.hideLoading();
                if (response.body() != null){
                    Log.d("HomePresenter", "Response Count = " + response.body().getData().size());
                    mView.showData(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ResponseAudioAll> call, Throwable t) {
                mView.hideLoading();
                Log.d("HomePresenter", t.getLocalizedMessage());
            }
        });
    }
}
