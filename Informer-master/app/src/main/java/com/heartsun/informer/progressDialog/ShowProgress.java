package com.heartsun.informer.progressDialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;

import com.heartsun.informer.R;


public class ShowProgress {

    Context context;
    ProgressDialog pDialog;

    public ShowProgress(Context context) {
        this.context = context;
    }

    ////// show progress dialog /////
    public void showProgress(){

        pDialog = ProgressDialog.show(new ContextThemeWrapper(context, R.style.NewDialog),"", "Please Wait...",true);

        pDialog.show();

    }

    public void hideProgress(){
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
