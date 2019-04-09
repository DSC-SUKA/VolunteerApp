package com.dsc.suka.volunteerapp.ui.dashboard.tunanetra;

import com.dsc.suka.volunteerapp.model.ResponseRequestSingle;

import java.util.List;

public interface TunaNetraView {

    void showLoading();
    void hideLoading();
    void showRequestList(List<ResponseRequestSingle> data);

}
