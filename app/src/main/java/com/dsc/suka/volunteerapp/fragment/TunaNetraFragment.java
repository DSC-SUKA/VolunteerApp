package com.dsc.suka.volunteerapp.fragment;


import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.activity.TunaNetraRecordActivity;
import com.dsc.suka.volunteerapp.adapter.RequestAdapter;
import com.dsc.suka.volunteerapp.newModel.RequestModelData;
import com.dsc.suka.volunteerapp.presenter.TunaNetraPresenter;
import com.dsc.suka.volunteerapp.service.ApiClientService;
import com.dsc.suka.volunteerapp.service.ApiInterfaceService;
import com.dsc.suka.volunteerapp.util.ItemClickSupport;
import com.dsc.suka.volunteerapp.view.TunaNetraView;
import com.google.firebase.storage.FirebaseStorage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class TunaNetraFragment extends Fragment implements TunaNetraView {
    private ApiInterfaceService apiInterfaceService;
    private RecyclerView mRecyclerView;
    private RequestAdapter mAdapter;
    private List<RequestModelData> requestItems;
    private TunaNetraPresenter presenter;
    private MediaPlayer mp;
    private int currentPlayPosition;
    FirebaseStorage storage;



    public TunaNetraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.tuna_netra_request, container, false);

        mRecyclerView = v.findViewById(R.id.rv_request_tuna_netra);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        storage = FirebaseStorage.getInstance();

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getContext(), TunaNetraRecordActivity.class);
                startActivity(intent);
            }
        });


        apiInterfaceService = ApiClientService.getClient().create(ApiInterfaceService.class);
        presenter = new TunaNetraPresenter(this, apiInterfaceService);
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
        AssetFileDescriptor afd = Objects.requireNonNull(getContext()).getResources().openRawResourceFd(R.raw.cek);
        String downloadUrl = storage.getReferenceFromUrl(url).toString();

        try {
            mp.setDataSource(url);
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
    public void showRequestList(List<RequestModelData> requestData) {
        requestItems = requestData;

        mAdapter = new RequestAdapter(requestItems, getContext(), new RequestAdapter.RequestAdapterClickListener() {
            @Override
            public void onClickListener(String audioUrl, int adapterPosition, boolean isPlaying) {
                Log.d("audio", audioUrl);
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
