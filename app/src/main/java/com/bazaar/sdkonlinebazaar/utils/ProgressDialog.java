package com.bazaar.sdkonlinebazaar.utils;

import android.content.Context;

public class ProgressDialog {


    private android.app.ProgressDialog progressDialog;

    public ProgressDialog(Context context, String message) {
        progressDialog = new android.app.ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
    }


    public void showProgressDialog() {
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
