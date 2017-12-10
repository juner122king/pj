package com.weisj.pj.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.weisj.pj.R;
import com.weisj.pj.utils.SystemConfig;

/**
 * Created by jun on 2017/12/9.
 */

public class VipJHDialog extends AlertDialog implements View.OnClickListener {
    private Context context;

    private TextView tv_kahao, tv_miss;
    private EditText et_mima;

    public VipJHDialog(Context context) {
        super(context, R.style.dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        layout();
    }


    private void init() {
        setContentView(R.layout.dialog_vip_jh);
        findViewById(R.id.tv_caler).setOnClickListener(this);
        findViewById(R.id.tv_ent).setOnClickListener(this);
        tv_kahao = (TextView) findViewById(R.id.text_kahao);
        tv_miss = (TextView) findViewById(R.id.tv_miss);
        et_mima = (EditText) findViewById(R.id.et_mima);


    }

    private void layout() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (SystemConfig.getScreenWidth() * 0.8);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//弹出键盘
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.tv_caler:
                this.cancel();
                break;
            case R.id.tv_ent:

                if (TextUtils.isEmpty(et_mima.getText()))
                    tv_miss.setVisibility(View.VISIBLE);
                else {
                    //TODO 支付
                }
                break;
        }
    }

}
