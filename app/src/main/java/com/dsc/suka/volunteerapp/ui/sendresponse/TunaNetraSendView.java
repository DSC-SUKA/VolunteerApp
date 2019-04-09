package com.dsc.suka.volunteerapp.ui.sendresponse;

public interface TunaNetraSendView {
    void showLoading();
    void hideLoading();
    void onSuccessSendAudio(String message);
    void onErrorSendAudio(String error);
}
