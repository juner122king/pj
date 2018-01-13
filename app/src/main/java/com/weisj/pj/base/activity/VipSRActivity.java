package com.weisj.pj.base.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.weisj.pj.R;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.view.dialog.VipJHDialog;
import com.weisj.pj.view.dialog.VipOfDialog;
import com.weisj.pj.view.dialog.VipYHJDialog;

/**
 * Created by jun on 2017/12/8.
 * VIP送人
 */

public class VipSRActivity extends BaseActivity {


    private int zf_type;//支付方式  0 微信， 1 支付宝
    private final static int zb = 1;
    private final static int wx = 0;

    private RadioButton rb_wx, rb_zb;
    private CheckBox checkBox;
    private TextView title, tv2, tv2_2;


    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_vipsr, null);
        initView(view);
        return view;
    }

    private void initView(View view) {


    }

    @Override
    public String setTitleStr() {
        return "购卡送人";
    }


    @Override
    public void getRefreshData() {

    }

}
