package com.dsc.suka.volunteerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContributionModel {
    @SerializedName("contributions")
    private List<ContributionItems> mContributionItems;

    public ContributionModel(List<ContributionItems> contributionItems){
        mContributionItems = contributionItems;
    }

    public List<ContributionItems> getmContributionItems() {
        return mContributionItems;
    }

    public void setmContributionItems(List<ContributionItems> mContributionItems) {
        this.mContributionItems = mContributionItems;
    }
}
