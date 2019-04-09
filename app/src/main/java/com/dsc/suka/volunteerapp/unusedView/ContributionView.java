package com.dsc.suka.volunteerapp.unusedView;

import com.dsc.suka.volunteerapp.model.ResponseAudioAll;

import java.util.List;

public interface ContributionView {
    void showLoading();
    void hideLoading();
    void showContributionList(List<ResponseAudioAll> responseList);
}
