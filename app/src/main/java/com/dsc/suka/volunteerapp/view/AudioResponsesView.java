package com.dsc.suka.volunteerapp.view;

import com.dsc.suka.volunteerapp.model.ResponseAudioAll;

import java.util.List;

public interface AudioResponsesView {

    void showLoading();
    void hideLoading();
    void showAudioResponses(List<ResponseAudioAll> responseAudioAllRespons);

}
