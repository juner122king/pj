package com.weisj.pj.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemVIPYHJAdapter;
import com.weisj.pj.bean.YHJBean;
import com.weisj.pj.utils.SystemConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 2017/12/10.
 */

public class VipOfDialog extends AlertDialog implements View.OnClickListener {

    private Context context;
    private boolean isdone;//成功of失败
    private TextView tv1;

    public VipOfDialog(Context context, boolean isdone) {
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
        setContentView(R.layout.dialog_vip_of);
        findViewById(R.id.tv_caler).setOnClickListener(this);
        findViewById(R.id.tv_ent).setOnClickListener(this);
        tv1 = (TextView) findViewById(R.id.tv1);
        if (isdone)
            tv1.setText("购卡成功，是否跳转到我的卡包");
        else
            tv1.setText("支付失败，重新支付");


    }


    private void layout() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (SystemConfig.getScreenWidth() * 0.9);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_caler:
                Toast.makeText(context, "否", Toast.LENGTH_LONG).show();

                this.dismiss();

                break;

            case R.id.tv_ent:

                if (isdone)
                    Toast.makeText(context, "跳转到我的卡包", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, "重新支付", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
