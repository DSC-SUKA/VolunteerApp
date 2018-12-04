package com.dsc.suka.volunteerapp.view;

import com.dsc.suka.volunteerapp.model.RequestItems;
import com.dsc.suka.volunteerapp.newModel.RequestModelAll;
import com.dsc.suka.volunteerapp.newModel.RequestModelData;

import java.util.List;

public interface TunaNetraView {

    void showLoading();
    void hideLoading();
    void showRequestList(List<RequestModelData> requestData);

}
