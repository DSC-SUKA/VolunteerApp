package com.dsc.suka.volunteerapp.presenter;


import android.util.Log;

import com.dsc.suka.volunteerapp.view.TunaNetraView;
import com.dsc.suka.volunteerapp.model.RequestItems;
import com.dsc.suka.volunteerapp.model.RequestModel;
import com.dsc.suka.volunteerapp.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TunaNetraPresenter {
    private TunaNetraView mView;
    private ApiInterface mApiInterface;

    public TunaNetraPresenter(TunaNetraView view, ApiInterface apiInterface){
        mView = view;
        mApiInterface = apiInterface;
    }

    public void getRequestList(){
        mView.showLoading();
        Call<RequestModel> requestModelCall = mApiInterface.getRequests();
        requestModelCall.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {
                List<RequestItems> requestItemsList = response.body().getRequestItems();
                Log.d("Retrofit Get", "Request Count: " + String.valueOf(requestItemsList.size()));
                mView.hideLoading();
                mView.showRequestList(requestItemsList);
            }

            @Override
            public void onFailure(Call<RequestModel> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}
