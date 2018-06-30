package com.omagrahari.demonobroker.Utilities;

import android.app.Activity;
import android.app.AlertDialog;

import dmax.dialog.SpotsDialog;

/**
 * Created by omprakash on 30/06/18.
 */

public class Utils {
    AlertDialog dialog;

    public void showDialog(Activity activity) {
        try {
            dialog = new SpotsDialog(activity);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDialog() {
        try {
            dialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
