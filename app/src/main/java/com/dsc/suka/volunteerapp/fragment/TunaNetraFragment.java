package com.dsc.suka.volunteerapp.fragment;


import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsc.suka.volunteerapp.presenter.TunaNetraPresenter;
import com.dsc.suka.volunteerapp.view.TunaNetraView;
import com.dsc.suka.volunteerapp.util.ItemClickSupport;
import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.adapter.RequestAdapter;
import com.dsc.suka.volunteerapp.activity.TunaNetraRecordActivity;
import com.dsc.suka.volunteerapp.model.RequestItems;
import com.dsc.suka.volunteerapp.network.ApiClient;
import com.dsc.suka.volunteerapp.network.ApiInterface;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TunaNetraFragment extends Fragment implements TunaNetraView {
    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RequestAdapter mAdapter;
    private List<RequestItems> requestItems;
    private TunaNetraPresenter presenter;
    private MediaPlayer mp;
    private int currentPlayPosition;


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


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        presenter = new TunaNetraPresenter(this, apiInterface);
        presenter.getRequestList();


        return v;
    }

    private void playMedia(String url){
        if (mp == null){
            mp = new MediaPlayer();
        } else {
            mp.reset();
        }
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        AssetFileDescriptor afd = getContext().getResources().openRawResourceFd(R.raw.cek);

        try {
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRequestList(List<RequestItems> requestData) {
        requestItems = requestData;

        mAdapter = new RequestAdapter(requestItems, getContext(), new RequestAdapter.RequestAdapterClickListener() {
            @Override
            public void onClickListener(String audioUrl, int adapterPosition, boolean isPlaying) {
                if (isPlaying){
                    mp.pause();
                } else {
                    playMedia(audioUrl);
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mp != null){
            if (mp.isPlaying()){
                mp.stop();
            }
            mp.release();
        }
    }
}
