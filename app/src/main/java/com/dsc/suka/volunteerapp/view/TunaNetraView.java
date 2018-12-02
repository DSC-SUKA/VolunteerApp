package com.dsc.suka.volunteerapp.view;

import com.dsc.suka.volunteerapp.model.RequestItems;

import java.util.List;

public interface TunaNetraView {

    void showLoading();
    void hideLoading();
    void showRequestList(List<RequestItems> requestData);

}
