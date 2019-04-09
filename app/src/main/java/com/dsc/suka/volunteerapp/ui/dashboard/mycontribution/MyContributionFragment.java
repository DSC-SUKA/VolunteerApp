package com.dsc.suka.volunteerapp.ui.dashboard.mycontribution;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.activity.MyContributionExtendedActivity;
import com.dsc.suka.volunteerapp.model.ResponseAudioSingle;
import com.dsc.suka.volunteerapp.utils.ItemClickSupport;
import com.dsc.suka.volunteerapp.utils.SessionManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyContributionFragment extends Fragment implements MyContributionView {
    private RecyclerView mRecyclerView;
    private MyContributionAdapter mAdapter;
    private List<ResponseAudioSingle> mContributionList;
    private MyContributionPresenter mPresenter;
    private SessionManager mSessionManager;

    public MyContributionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_contributions, container, false);
        initView(view);
        initComponent();
        return view;
    }

    private void initComponent() {
        mPresenter = new MyContributionPresenter(this);
        mSessionManager = new SessionManager(getContext());
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.rv_my_contribution);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getContext(), MyContributionExtendedActivity.class);
                intent.putExtra(MyContributionExtendedActivity.EXTRA_CONTRIBUTION, mContributionList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // NOTE
        // Service for this method not avalaible on API for now,
        // if the API is ready, just delete this comment. Thanks
        // mPresenter.getUserContribution(mSessionManager.getUserData().getUserId());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showContributionList(List<ResponseAudioSingle> responseList) {
        mContributionList = responseList;
        mAdapter = new MyContributionAdapter(mContributionList, getContext());
        mRecyclerView.setAdapter(mAdapter);
    }
}
