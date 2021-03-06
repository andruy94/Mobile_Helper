package com.a1101studio.mobile_helper_open;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import static android.R.string.no;
import static android.R.string.ok;

/**
 * Created by andruy94 on 26.11.2017.
 */

public class CreateColumnDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.add_column_dialog_layout, null, false);
        adb.setTitle("Title!").setPositiveButton(ok, (dialog, which) -> {

        })
                .setNegativeButton(no, null)
                .setView(inflate);
        return adb.create();
    }
}
