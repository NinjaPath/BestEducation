package com.ninjapath.besteducation;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class SnackbarMessages {
    public static void makeSnackbarError(View view, String error) {
        Snackbar
                .make(
                        view,
                        error,
                        Snackbar.LENGTH_LONG
                )
                .setTextColor(view.getContext().getResources().getColor(R.color.white))
                .setBackgroundTint(view.getContext().getResources().getColor(R.color.red))
                .show();
    }

    public static void makeSnackbarNotify(View view, String message) {
        Snackbar
                .make(
                        view,
                        message,
                        Snackbar.LENGTH_LONG
                )
                .setTextColor(view.getContext().getResources().getColor(R.color.white))
                .setBackgroundTint(view.getContext().getResources().getColor(R.color.green))
                .show();
    }
}
