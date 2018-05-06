package com.fansy.smz.fansygame.login.utils;

import android.widget.Toast;

/**
 * Toast package
 *
 * @author Ken Hong
 */
public class ToastUtils {

    public static void showShortToast(String msg) {
        Toast.makeText(IMApplication.getMyAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(String msg) {
        Toast.makeText(IMApplication.getMyAppContext(), msg, Toast.LENGTH_LONG).show();
    }
}
