package com.fansy.smz.fansygame.login.IM;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.fansy.smz.fansygame.login.GameHomeActivity;
import com.fansy.smz.fansygame.login.utils.IMApplication;
import com.fansy.smz.fansygame.login.utils.PreferencesUtils;
import com.fansy.smz.fansygame.login.utils.ToastUtils;
import com.fansy.smz.fansygame.login.view.CustomLoadingDialog;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;


/**
 * Login Async task
 * 1.	 onPreExecute       UI
 * 2.	 doInBackground
 * 3.    publishProgress
 * 4.    onProgressUpdate   UI
 * 5.    onPostExecute	    UI
 */

public class LoginAsyncTask extends AsyncTask<String, String, Boolean> {
    private CustomLoadingDialog loadDialog;
    private Context mContext;

    public LoginAsyncTask(Context context) {
        mContext = context;
    }


    /**
     * UI, init
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //显示登录提示条
        loadDialog = new CustomLoadingDialog(mContext);
        loadDialog.setTitle("正在登录...");
        loadDialog.show();
    }


    /**
     * could use publishProgress() to trigger onProgressUpdate
     */
    @Override
    protected Boolean doInBackground(String... strings) {

        String username = strings[0];
        String pwd = strings[1];

        SmackUtils.getInstance().getXMPPConnection();

        if (SmackUtils.getInstance().login(username, pwd)) {

            PreferencesUtils.getInstance().putString("username", username);
            PreferencesUtils.getInstance().putString("pwd", pwd);

            IMApplication.connection.addAsyncStanzaListener(
                    new PacketListener(),
                    new StanzaFilter() {
                        @Override
                        public boolean accept(Stanza stanza) {
                            return true;
                        }
                    }
            );
            return true;// to onPostExecute
        }
        return false;// to onPostExecute
    }

    /**
     * UI, after doInBackground
     */
    @Override
    protected void onPostExecute(Boolean bool) {

        if (loadDialog.isShowing()) {
            loadDialog.dismiss();
        }

        if (bool) {
            Intent intent = new Intent(mContext, GameHomeActivity.class);
            mContext.startActivity(intent);
            ((Activity)mContext).finish();
        } else {
            ToastUtils.showShortToast("请检查用户名和密码是否正确/网络是否可用");
        }
    }
}
