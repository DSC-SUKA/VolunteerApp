package com.dsc.suka.volunteerapp.ui.dashboard.tunanetra;


import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.activity.TunaNetraRecordActivity;
import com.dsc.suka.volunteerapp.model.ResponseRequestSingle;
import com.dsc.suka.volunteerapp.utils.ItemClickSupport;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class TunaNetraFragment extends Fragment implements TunaNetraView {
    private List<ResponseRequestSingle> requestItems;
    private RecyclerView mRecyclerView;
    private TunaNetraAdapter mAdapter;
    private TunaNetraPresenter mPresenter;
    private MediaPlayer mediaPlayer;

    public TunaNetraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tuna_netra_request, container, false);
        initView(view);
        initComponent();
        return view;
    }

    private void initComponent() {
        mPresenter = new TunaNetraPresenter(this);
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.rv_request_tuna_netra);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getContext(), TunaNetraRecordActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getAllRequest();
    }

    private void playMedia(String url) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        } else {
            mediaPlayer.reset();
        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(Objects.requireNonNull(getContext()), Uri.parse(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRequestList(List<ResponseRequestSingle> requestData) {
        requestItems = requestData;

        mAdapter = new TunaNetraAdapter(requestItems, getContext(), new TunaNetraAdapter.TunaNetraAdapterClickListener() {
            @Override
            public void onClickListener(String audioUrl, int adapterPosition, boolean isPlaying) {
                Log.d("audioUrl", audioUrl);
                if (isPlaying) {
                    mediaPlayer.pause();
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
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
    }
}
