package com.dsc.suka.volunteerapp.ui.dashboard.mycontribution;

import com.dsc.suka.volunteerapp.model.ResponseAudioAll;
import com.dsc.suka.volunteerapp.model.ResponseAudioSingle;

import java.util.List;

public interface MyContributionView {
    void showLoading();
    void hideLoading();
    void showContributionList(List<ResponseAudioSingle> responseList);
}
