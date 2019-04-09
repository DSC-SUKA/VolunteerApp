package com.dsc.suka.volunteerapp.ui.dashboard.tunanetra;


import android.support.annotation.NonNull;
import android.util.Log;

import com.dsc.suka.volunteerapp.model.ResponseRequestAll;
import com.dsc.suka.volunteerapp.model.ResponseRequestSingle;
import com.dsc.suka.volunteerapp.service.ApiInterface;
import com.dsc.suka.volunteerapp.service.ApiUtils;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TunaNetraPresenter {
    private TunaNetraView mView;
    private ApiInterface mApiInterface;

    public TunaNetraPresenter(TunaNetraView view){
        mView = view;
        mApiInterface = ApiUtils.getUserService();
    }

    public void getAllRequest(){
        mView.showLoading();
        Call<ResponseRequestAll> requestModelCall = mApiInterface.getAllRequests();
        requestModelCall.enqueue(new Callback<ResponseRequestAll>() {
            @Override
            public void onResponse(@NonNull Call<ResponseRequestAll> call, @NonNull Response<ResponseRequestAll> response) {
                mView.hideLoading();
                if (response.body() != null){
                    Log.d("TunaNetraPresenter", "Request Count = " + response.body().getData().size());
                    mView.showRequestList(response.body().getData());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseRequestAll> call, @NonNull Throwable t) {
                mView.hideLoading();
                Log.e("TunaNetraPresenter", t.getLocalizedMessage());
            }
        });
    }

}
