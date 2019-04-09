package com.dsc.suka.volunteerapp.ui.dashboard.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.model.ResponseAudioSingle;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView {
    private RecyclerView recyclerView;
    private HomeAdapter mAdapter;
    private List<ResponseAudioSingle> responseList;
    private HomePresenter mPresenter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initComponent();
        return view;
    }

    private void initView(View v) {
        recyclerView = v.findViewById(R.id.rv_latest_contributions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void initComponent() {
        mPresenter = new HomePresenter(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getAllResponses();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(List<ResponseAudioSingle> data) {
        responseList = data;
        mAdapter = new HomeAdapter(responseList, getContext());
        recyclerView.setAdapter(mAdapter);
    }
}
