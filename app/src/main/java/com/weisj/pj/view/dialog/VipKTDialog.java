package com.weisj.pj.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.weisj.pj.R;
import com.weisj.pj.utils.SystemConfig;

/**
 * Created by jun on 2017/12/10.
 */

public class VipKTDialog extends AlertDialog implements View.OnClickListener {

    private Context context;
    private boolean isdone;
    private TextView tv1, tv2, tv3;

    public VipKTDialog(Context context, boolean isdone) {
        super(context);
        this.context = context;
        this.isdone = isdone;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        layout();
    }


    private void init() {
        setContentView(R.layout.dialog_vip_kt);
        findViewById(R.id.tv2).setOnClickListener(this);
        findViewById(R.id.tv3).setOnClickListener(this);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        if (isdone) {
            tv1.setText("会员卡开通成功");
            tv2.setVisibility(View.VISIBLE);
            tv3.setVisibility(View.GONE);
        } else {
            tv1.setText("会员卡开通失败");
            tv3.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.GONE);
        }
    }


    private void layout() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (SystemConfig.getScreenWidth() * 0.9);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv2:
                Toast.makeText(context, "去逛逛", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv3:
                Toast.makeText(context, "重新选择", Toast.LENGTH_LONG).show();
                break;


        }
    }
}
