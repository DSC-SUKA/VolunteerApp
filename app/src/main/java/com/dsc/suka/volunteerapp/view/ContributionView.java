package com.dsc.suka.volunteerapp.view;

import com.dsc.suka.volunteerapp.model.ContributionItems;

import java.util.List;

public interface ContributionView {
    void showLoading();
    void hideLoading();
    void showContributionList(List<ContributionItems> contributionData);
}
