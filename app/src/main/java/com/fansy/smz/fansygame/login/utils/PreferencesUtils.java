package com.fansy.smz.fansygame.login.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * SharedPreferences
 *
 * @author Ken Hong
 */

public class PreferencesUtils {

    private static String IMSP = "IMSP";
    private SharedPreferences mySP;

    public static PreferencesUtils getInstance() {
        return SPHolder.sInstance;
    }

    private static class SPHolder {
        private static final PreferencesUtils sInstance = new PreferencesUtils();
    }

    public PreferencesUtils() {
        mySP = IMApplication.getMyAppContext().getSharedPreferences(IMSP, Context.MODE_PRIVATE);
    }

    //----------------------------------------------------------

    public PreferencesUtils putInt(String key, int value) {
        mySP.edit().putInt(key, value).apply();
        return this;
    }

    public PreferencesUtils putString(String key, String value) {
        mySP.edit().putString(key, value).apply();
        return this;
    }

    public PreferencesUtils putBoolean(String key, boolean value) {
        mySP.edit().putBoolean(key, value).apply();
        return this;
    }

    public int getInt(String key) {
        return mySP.getInt(key, -1);
    }

    public String getString(String key) {
        return mySP.getString(key, "");
    }

    public boolean getBoolean(String key) {
        return mySP.getBoolean(key, false);
    }

}
