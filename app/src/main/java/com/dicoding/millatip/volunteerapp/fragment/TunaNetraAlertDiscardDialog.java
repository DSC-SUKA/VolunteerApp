package com.dicoding.millatip.volunteerapp.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dicoding.millatip.volunteerapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TunaNetraAlertDiscardDialog extends DialogFragment implements View.OnClickListener {
    private Button btnDiscardYes, btnDiscardNo;
    private OnOptionDialogListener onOptionDialogListener;

    public TunaNetraAlertDiscardDialog() {
        // Required empty public constructor
    }

    public OnOptionDialogListener getOnOptionDialogListener() {
        return onOptionDialogListener;
    }

    public void setOnOptionDialogListener(OnOptionDialogListener onOptionDialogListener) {
        this.onOptionDialogListener = onOptionDialogListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.tuna_netra_alert_discard, container, false);

        btnDiscardYes = v.findViewById(R.id.btn_discard_yes);
        btnDiscardYes.setOnClickListener(this);

        btnDiscardNo = v.findViewById(R.id.btn_discard_no);
        btnDiscardNo.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_discard_yes:
                getOnOptionDialogListener().onOptionChoosen(true);
                getDialog().cancel();
                break;
            case R.id.btn_discard_no:
                getDialog().cancel();
                break;
        }
    }

    public interface OnOptionDialogListener {
        void onOptionChoosen(boolean optionsChoosen);
    }
}
