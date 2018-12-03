package com.dsc.suka.volunteerapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsc.suka.volunteerapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogoutAlert extends Fragment {


    public LogoutAlert() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.logout_alert, container, false);
    }

}
