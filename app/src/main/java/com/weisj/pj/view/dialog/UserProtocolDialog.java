package com.weisj.pj.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.weisj.pj.R;
import com.weisj.pj.main.fragment.HomeFragment;
import com.weisj.pj.utils.SystemConfig;


/**
 * Created by Administrator on 2015/12/9 0009.
 */
public class UserProtocolDialog extends AlertDialog {
    private Context context;
    private HomeFragment homeFragment;

    public UserProtocolDialog(Context context) {
        super(context, R.style.dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_user_protocol);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (SystemConfig.getScreenWidth() * 0.8);
        lp.height = (int) (SystemConfig.getScreenHeight() * 0.8);
    }


}
