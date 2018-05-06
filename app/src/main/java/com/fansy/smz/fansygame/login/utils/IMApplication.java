package com.fansy.smz.fansygame.login.utils;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
/**
 * Application
 *
 * @author Ken Hong
 */

public class IMApplication extends Application {
    private static Context myAppContext = null;
    public static XMPPTCPConnection connection = null;

    @Override
    public void onCreate() {
        super.onCreate();
        myAppContext = getApplicationContext();

        // Use Baidu Map SDK to store input info.
        SDKInitializer.initialize(myAppContext);
    }

    public static Context getMyAppContext() {
        return myAppContext;
    }
}
