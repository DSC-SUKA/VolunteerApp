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

import com.dsc.suka.volunteerapp.util.ItemClickSupport;
import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.adapter.RequestAdapter;
import com.dsc.suka.volunteerapp.activity.TunaNetraRecordActivity;
import com.dsc.suka.volunteerapp.model.RequestItems;
import com.dsc.suka.volunteerapp.model.RequestModel;
import com.dsc.suka.volunteerapp.rest.ApiClient;
import com.dsc.suka.volunteerapp.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TunaNetraFragment extends Fragment {
    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RequestAdapter mAdapter;


    public TunaNetraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.tuna_netra_request, container, false);

        mRecyclerView = v.findViewById(R.id.rv_request_tuna_netra);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getContext(), TunaNetraRecordActivity.class);
                startActivity(intent);
            }
        });

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        refresh();

        return v;
    }

    public void refresh() {
        Call<RequestModel> requestModelCall = mApiInterface.getRequests();
        requestModelCall.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {
                List<RequestItems> requestItemsList = response.body().getRequestItems();
                Log.d("Retrofit Get", "Request Count: " + String.valueOf(requestItemsList.size()));
                mAdapter = new RequestAdapter(requestItemsList, getContext());
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<RequestModel> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });

    }

}
