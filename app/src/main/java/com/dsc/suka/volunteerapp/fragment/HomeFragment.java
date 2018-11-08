package com.dsc.suka.volunteerapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsc.suka.volunteerapp.ItemClickSupport;
import com.dsc.suka.volunteerapp.LatestContributionExtendedActivity;
import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.adapter.LatestContributionAdapter;
import com.dsc.suka.volunteerapp.model.ContributionItems;
import com.dsc.suka.volunteerapp.model.ContributionModel;
import com.dsc.suka.volunteerapp.rest.ApiClient;
import com.dsc.suka.volunteerapp.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ApiInterface mApiInterface;
    private RecyclerView recyclerView;
    private LatestContributionAdapter latestContributionAdapter;
    private List<ContributionItems> contributionItemsList;

    public List<ContributionItems> getContributionItemsList() {
        return contributionItemsList;
    }

    public void setContributionItemsList(List<ContributionItems> contributionItemsList) {
        this.contributionItemsList = contributionItemsList;
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = v.findViewById(R.id.rv_latest_contributions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getContext(), LatestContributionExtendedActivity.class);
                intent.putExtra(LatestContributionExtendedActivity.EXTRA_CONTRIBUTION, getContributionItemsList().get(position));
                startActivity(intent);
            }
        });

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        refresh();

        return v;
    }

    private void refresh() {
        Call<ContributionModel> contributionModelCall = mApiInterface.getLatestContribution();
        contributionModelCall.enqueue(new Callback<ContributionModel>() {
            @Override
            public void onResponse(Call<ContributionModel> call, Response<ContributionModel> response) {
                List<ContributionItems> contributionItems = response.body().getmContributionItems();
                setContributionItemsList(contributionItems);
                Log.d("Retrofit Get", "Request Count: " + String.valueOf(contributionItems.size()));
                latestContributionAdapter = new LatestContributionAdapter(contributionItems, getContext());
                recyclerView.setAdapter(latestContributionAdapter);
            }

            @Override
            public void onFailure(Call<ContributionModel> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });

    }


}
