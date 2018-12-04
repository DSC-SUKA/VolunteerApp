package com.dsc.suka.volunteerapp.presenter;


import android.util.Log;

import com.dsc.suka.volunteerapp.newModel.RequestModelAll;
import com.dsc.suka.volunteerapp.newModel.RequestModelData;
import com.dsc.suka.volunteerapp.service.ApiInterfaceService;
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
    private ApiInterfaceService mApiInterface;

    public TunaNetraPresenter(TunaNetraView view, ApiInterfaceService apiInterface){
        mView = view;
        mApiInterface = apiInterface;
    }

    public void getRequestList(){
        mView.showLoading();
        Call<RequestModelAll> requestModelCall = mApiInterface.getRequests();
        requestModelCall.enqueue(new Callback<RequestModelAll>() {
            @Override
            public void onResponse(Call<RequestModelAll> call, Response<RequestModelAll> response) {
                Log.d("Retrofit Get", "Request Count: " + response.toString());
                List<RequestModelData> requestItemsList = response.body().getData();
                mView.hideLoading();
                mView.showRequestList(requestItemsList);
            }

            @Override
            public void onFailure(Call<RequestModelAll> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}
