//package com.dsc.suka.volunteerapp.presenter;
//
//import android.support.annotation.NonNull;
//import android.util.Log;
//
//import com.dsc.suka.volunteerapp.model.ResponseAudioAll;
//import com.dsc.suka.volunteerapp.service.ApiInterfaceMock;
//import com.dsc.suka.volunteerapp.view.AudioResponsesView;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class AudioResponsesPresenter {
//
//
//    private AudioResponsesView mView;
//    private ApiInterfaceMock apiInterfaceService;
//
//    public AudioResponsesPresenter(AudioResponsesView audioResponsesView, ApiInterfaceMock apiInterfaceService) {
//        this.mView = audioResponsesView;
//        this.apiInterfaceService = apiInterfaceService;
//    }
//
//    public void getAudioResponsesList(){
//        mView.showLoading();
//        Call<ResponseAudioAll> call = apiInterfaceService.audioResponses();
//        call.enqueue(new Callback<ResponseAudioAll>() {
//            @Override
//            public void onResponse(@NonNull Call<ResponseAudioAll> call, @NonNull Response<ResponseAudioAll> response) {
//                List<ResponseAudioAll> audioResponsesData = response.body().getData();
//                Log.d("Retrofit Get", "Request Count: " + response.toString());
//                mView.hideLoading();
//                mView.showAudioResponses(audioResponsesData);
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ResponseAudioAll> call, @NonNull Throwable t) {
//                Log.e("Retrofit Get", t.toString());
//            }
//        });
//    }
//
////    public void getMyContributionList(){
////        mView.showLoading();
////        Call<ContributionModel> contributionModelCall = mApiInterface.getMyContribution();
////        contributionModelCall.enqueue(new Callback<ContributionModel>() {
////            @Override
////            public void onResponse(@NonNull Call<ContributionModel> call, @NonNull Response<ContributionModel> response) {
////                List<ContributionItems> contributionItemsList = Objects.requireNonNull(response.body()).getmContributionItems();
////                Log.d("Retrofit Get", "Request Count: " + String.valueOf(contributionItemsList.size()));
////                mView.hideLoading();
////                mView.showContributionList(contributionItemsList);
////            }
////
////            @Override
////            public void onFailure(@NonNull Call<ContributionModel> call, @NonNull Throwable t) {
////                Log.e("Retrofit Get", t.toString());
////            }
////        });
////    }
//}
