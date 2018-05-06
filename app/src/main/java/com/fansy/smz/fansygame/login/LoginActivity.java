package com.fansy.smz.fansygame.login;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fansy.smz.fansygame.R;
import com.fansy.smz.fansygame.login.IM.LoginAsyncTask;
import com.fansy.smz.fansygame.login.utils.PreferencesUtils;
import com.fansy.smz.fansygame.login.utils.ToastUtils;

/**
 * Login
 *
 * @author Ken Hong
 */


public class LoginActivity extends Activity {

    public Context mContext;

    // Layout
    private RelativeLayout rl_user;
    private Button mLogin;
    private TextView register;
    private EditText account;
    private EditText password;
    private LoginAsyncTask loginAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        findView();
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();

        String username = PreferencesUtils.getInstance().getString("username");
        String pwd = PreferencesUtils.getInstance().getString("pwd");
        //String username = "";
        //String pwd = "";
        if (!TextUtils.isEmpty(username)) {
            account.setText(username);
        }
        if (!TextUtils.isEmpty(pwd)) {
            password.setText(pwd);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginAsyncTask != null && loginAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            loginAsyncTask.cancel(true);
        }
    }

    //---------------------------------------------------
    private void findView() {
        rl_user = findViewById(R.id.rl_user);
        register = findViewById(R.id.register);
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        mLogin = findViewById(R.id.login);
    }

    private void init() {
        //动画
        Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.login_anim);
        anim.setFillAfter(true);
        rl_user.startAnimation(anim);
        //登录按钮事件
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
        //注册按钮事件
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRegister();
            }
        });
    }

    //---------------------------------------------------
    private void doLogin() {
        String username = account.getText().toString();
        String pwd = password.getText().toString();
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showShortToast("您的账号忘记填写啦");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.showShortToast("您的密码忘记填写啦");
            return;
        }
        //执行异步登录任务
        loginAsyncTask = new LoginAsyncTask(mContext);
        loginAsyncTask.execute(username, pwd);
    }

    private void doRegister() {
        //Intent intent = new Intent(mContext, RegisterActivity.class);
        //startActivity(intent);
    }
}

