package com.fansy.smz.fansygame.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fansy.smz.fansygame.R;
import com.fansy.smz.fansygame.login.IM.SmackUtils;
import com.fansy.smz.fansygame.login.view.CustomPopWindow;
import com.fansy.smz.fansygame.slotgame.SlotGameActivity;

/**
 * Game Home
 *
 * @author Ken Hong
 */

public class GameHomeActivity extends Activity implements View.OnClickListener {
    Context mContext;
    ImageView[] gameImageView = new ImageView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.game_home_activity_main);
        findView();
        init();
    }

    private void findView() {
        gameImageView[0] = findViewById(R.id.gameImageView1);
        gameImageView[1] = findViewById(R.id.gameImageView2);
        gameImageView[2] = findViewById(R.id.gameImageView3);
        gameImageView[3] = findViewById(R.id.gameImageView4);
    }

    private void init() {
        gameImageView[0].setOnClickListener(this);
        gameImageView[1].setOnClickListener(this);
        gameImageView[2].setOnClickListener(this);
        gameImageView[3].setOnClickListener(this);

    }

    //-----------------------------------------------------
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.gameImageView1:
                Intent intent = new Intent(mContext, SlotGameActivity.class);
                mContext.startActivity(intent);
                break;

            case R.id.gameImageView2:

                break;

            default:
                break;
        }
    }

    public void onLogout(View view){
        //Button button = findViewById(R.id.exit_button);
        showPopView(view);
    }

    private void showPopView(View exit) {
        View popview = LayoutInflater.from(mContext).inflate(R.layout.pop_exit, null);

        CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(mContext)
                .setView(popview)
                .setAnimationStyle(android.R.style.Animation_InputMethod)
                .create()
                .showAtLocation(exit, Gravity.BOTTOM, 0, 0);
        initPop(popWindow, popview);

    }

    public void initPop(final CustomPopWindow popWindow, View popview) {
        TextView exit = popview.findViewById(R.id.exit);
        TextView cancel = popview.findViewById(R.id.cancel);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                /**
                 * Disconnect
                 */
                SmackUtils.getInstance().exitConnect();
                //Intent intent = new Intent(mContext, LoginActivity.class);
                //mContext.startActivity(intent);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                popWindow.dissmiss();
            }
        });
    }
}
