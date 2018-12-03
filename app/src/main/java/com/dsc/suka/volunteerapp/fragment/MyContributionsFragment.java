package com.dsc.suka.volunteerapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.adapter.MyContributionAdapter;
import com.dsc.suka.volunteerapp.model.RequestItems;
import com.dsc.suka.volunteerapp.model.RequestModel;
import com.dsc.suka.volunteerapp.service.ApiClient;
import com.dsc.suka.volunteerapp.service.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyContributionsFragment extends Fragment {
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private MyContributionAdapter mAdapter;


    public MyContributionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_contributions, container, false);

        mRecyclerView = view.findViewById(R.id.rv_my_contribution);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        refresh();

        return view;
    }

    private void refresh() {
        Call<RequestModel> requestModelCall = mApiInterface.getContribution();
        requestModelCall.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {
                List<RequestItems> requestItemsList = response.body().getRequestItems();
                Log.d("Retrofit Get", "Request Count: " + String.valueOf(requestItemsList.size()));
                mAdapter = new MyContributionAdapter(requestItemsList, getContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<RequestModel> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());

            }
        });
    }

}
