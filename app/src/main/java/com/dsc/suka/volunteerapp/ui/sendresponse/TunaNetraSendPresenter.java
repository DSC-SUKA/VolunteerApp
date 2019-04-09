package com.dsc.suka.volunteerapp.ui.sendresponse;

import android.util.Log;

import com.dsc.suka.volunteerapp.model.RespUploadResponse;
import com.dsc.suka.volunteerapp.model.ResponseRequestSingle;
import com.dsc.suka.volunteerapp.model.UserData;
import com.dsc.suka.volunteerapp.service.ApiInterface;
import com.dsc.suka.volunteerapp.service.ApiUtils;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TunaNetraSendPresenter {
    private ApiInterface mApiInterface;
    private TunaNetraSendView mView;

    public TunaNetraSendPresenter(TunaNetraSendView view){
        mApiInterface = ApiUtils.getUserService();
        mView = view;
    }

    void sendResponse(String audioPath, String category, UserData userData, ResponseRequestSingle requestData){
        mView.showLoading();
        File audio = new File(audioPath);
        RequestBody requestBody = RequestBody.create(MultipartBody.FORM, audio);
        MultipartBody.Part audioBody = MultipartBody.Part.createFormData("audio", audio.getName(), requestBody);

        RequestBody reqId = RequestBody.create(MultipartBody.FORM, requestData.getDocid());
        RequestBody cat = RequestBody.create(MultipartBody.FORM, category);
        RequestBody userId = RequestBody.create(MultipartBody.FORM, userData.getUserId());
        RequestBody userImgUrl = RequestBody.create(MultipartBody.FORM, userData.getUserPhotoUrl());
        RequestBody name = RequestBody.create(MultipartBody.FORM, userData.getUserName());
        RequestBody difId = RequestBody.create(MultipartBody.FORM, requestData.getUserId());
        RequestBody difName = RequestBody.create(MultipartBody.FORM, requestData.getUserName());

        Call<RespUploadResponse> call = mApiInterface.uploadResponse(audioBody, reqId, cat, userId, userImgUrl, name, difId, difName);
        call.enqueue(new Callback<RespUploadResponse>() {
            @Override
            public void onResponse(Call<RespUploadResponse> call, Response<RespUploadResponse> response) {
                mView.hideLoading();
                if (response.body() != null){
                    Log.d(TunaNetraSendPresenter.class.getSimpleName(), "Sent, Url  = " + response.body().getMessage());
                    mView.onSuccessSendAudio("Response Sent");
                }
            }

            @Override
            public void onFailure(Call<RespUploadResponse> call, Throwable t) {
                mView.hideLoading();
                Log.d(TunaNetraSendPresenter.class.getSimpleName(), t.getLocalizedMessage());
                mView.onErrorSendAudio("Request not sent, try again...");
            }
        });
    }

}
