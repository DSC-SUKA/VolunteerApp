package com.dsc.suka.volunteerapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.activity.MyContributionExtendedActivity;
import com.dsc.suka.volunteerapp.adapter.MyContributionAdapter;
import com.dsc.suka.volunteerapp.adapter.RequestAdapter;
import com.dsc.suka.volunteerapp.model.ContributionItems;
import com.dsc.suka.volunteerapp.model.ContributionModel;
import com.dsc.suka.volunteerapp.model.RequestItems;
import com.dsc.suka.volunteerapp.model.RequestModel;
import com.dsc.suka.volunteerapp.rest.ApiClient;
import com.dsc.suka.volunteerapp.rest.ApiInterface;
import com.dsc.suka.volunteerapp.util.ItemClickSupport;

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
    private List<ContributionItems> mContributionItemsList;

    public List<ContributionItems> getmContributionItemsList() {
        return mContributionItemsList;
    }

    public void setmContributionItemsList(List<ContributionItems> mContributionItemsList) {
        this.mContributionItemsList = mContributionItemsList;
    }

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

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getContext(), MyContributionExtendedActivity.class);
                intent.putExtra(MyContributionExtendedActivity.EXTRA_CONTRIBUTION, getmContributionItemsList().get(position));
                startActivity(intent);
            }
        });

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        refresh();

        return view;
    }

    private void refresh() {
        Call<ContributionModel> requestModelCall = mApiInterface.getMyContribution();
        requestModelCall.enqueue(new Callback<ContributionModel>() {
            @Override
            public void onResponse(Call<ContributionModel> call, Response<ContributionModel> response) {
                List<ContributionItems> contributionItemsList = response.body().getmContributionItems();
                Log.d("Retrofit Get", "Request Count: " + String.valueOf(contributionItemsList.size()));
                setmContributionItemsList(contributionItemsList);
                mAdapter = new MyContributionAdapter(contributionItemsList, getContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ContributionModel> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());

            }
        });
    }

}
