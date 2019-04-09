package com.dsc.suka.volunteerapp.ui.dashboard.home;

import com.dsc.suka.volunteerapp.model.ResponseAudioAll;
import com.dsc.suka.volunteerapp.model.ResponseAudioSingle;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void showData(List<ResponseAudioSingle> data);
}
