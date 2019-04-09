//package com.dsc.suka.volunteerapp.presenter;
//
//import android.support.annotation.NonNull;
//import android.util.Log;
//
//import com.dsc.suka.volunteerapp.model.ContributionItems;
//import com.dsc.suka.volunteerapp.model.ContributionModel;
//import com.dsc.suka.volunteerapp.network.ApiInterfaceMock;
//import com.dsc.suka.volunteerapp.view.ContributionView;
//
//import java.util.List;
//import java.util.Objects;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class ContributionPresenter {
//    private ContributionView mView;
//    private ApiInterfaceMock mApiInterfaceMock;
//
//    public ContributionPresenter(ContributionView contributionView, ApiInterfaceMock apiInterface) {
//        this.mView = contributionView;
//        this.mApiInterfaceMock = apiInterface;
//    }
//
//    public void getLatestContributionList(){
//        mView.showLoading();
//        Call<ContributionModel> contributionModelCall = mApiInterfaceMock.getLatestContribution();
//        contributionModelCall.enqueue(new Callback<ContributionModel>() {
//            @Override
//            public void onResponse(@NonNull Call<ContributionModel> call, @NonNull Response<ContributionModel> response) {
//                List<ContributionItems> contributionItemsList = Objects.requireNonNull(response.body()).getmContributionItems();
//                Log.d("Retrofit Get", "Request Count: " + response.toString());
//                mView.hideLoading();
//                mView.showContributionList(contributionItemsList);
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ContributionModel> call, @NonNull Throwable t) {
//                Log.e("Retrofit Get", t.toString());
//            }
//        });
//    }
//
//    public void getMyContributionList(){
//        mView.showLoading();
//        Call<ContributionModel> contributionModelCall = mApiInterfaceMock.getMyContribution();
//        contributionModelCall.enqueue(new Callback<ContributionModel>() {
//            @Override
//            public void onResponse(@NonNull Call<ContributionModel> call, @NonNull Response<ContributionModel> response) {
//                List<ContributionItems> contributionItemsList = Objects.requireNonNull(response.body()).getmContributionItems();
//                Log.d("Retrofit Get", "Request Count: " + String.valueOf(contributionItemsList.size()));
//                mView.hideLoading();
//                mView.showContributionList(contributionItemsList);
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ContributionModel> call, @NonNull Throwable t) {
//                Log.e("Retrofit Get", t.toString());
//            }
//        });
//    }
//}
