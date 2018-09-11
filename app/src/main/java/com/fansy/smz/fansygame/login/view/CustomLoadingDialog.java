package com.fansy.smz.fansygame.login.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.fansy.smz.fansygame.R;


/**
 * Loading UI
 * CustomLoadingDialog
 * @author Ken Hong
 */

public class CustomLoadingDialog extends Dialog {

    private TextView tv;
    private boolean cancelable = true;

    public CustomLoadingDialog(Context context) {
        super(context, R.style.Dialog_style);
        init();
    }

    private void init() {
        View contentView = View.inflate(
                getContext(),
                R.layout.common_loading_dialog,
                null
        );
        setContentView(contentView);
        //点击关闭
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelable) {
                    dismiss();
                }
            }
        });
        tv = findViewById(R.id.tv);
    }

//    @Override
//    public void show() {
//        super.show();
//    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void setCancelable(boolean flag) {
        cancelable = flag;
        super.setCancelable(flag);
    }

    @Override
    public void setTitle(CharSequence title) {
        tv.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getContext().getString(titleId));
    }
}
