package com.weisj.pj.base.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.weisj.pj.R;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.view.dialog.SelectCityDialog;
import com.weisj.pj.view.dialog.VipJHDialog;
import com.weisj.pj.view.dialog.VipYHJDialog;

/**
 * Created by jun on 2017/12/8.
 */

public class VipActivity extends BaseActivity implements View.OnClickListener {


    private int zf_type;//支付方式  0 微信， 1 支付宝
    private final static int zb = 1;
    private final static int wx = 0;

    private RadioButton rb_wx, rb_zb;
    private CheckBox checkBox;
    private TextView tv1, tv2, tv3, tv4, tv5, tv6;


    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_vip, null);
        rootView.isHintHeadBar(true);
        initView(view);
        return view;
    }

    private void initView(View view) {

        view.findViewById(R.id.iv).setOnClickListener(this);
        view.findViewById(R.id.tv_songren).setOnClickListener(this);
        view.findViewById(R.id.tv_zf).setOnClickListener(this);

        view.findViewById(R.id.ll_rhj).setOnClickListener(this);
        view.findViewById(R.id.ll_jh).setOnClickListener(this);

        rb_wx = (RadioButton) view.findViewById(R.id.rb_wx);
        rb_zb = (RadioButton) view.findViewById(R.id.rb_zb);
        checkBox = (CheckBox) view.findViewById(R.id.cb);

    }

    @Override
    public String setTitleStr() {
        return "会员卡";
    }


    @Override
    public void getRefreshData() {

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv:
                finish();
                break;
            case R.id.tv_songren:
                Toast.makeText(this, "送人", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_zf: //支付按钮
                if (checkBox.isChecked()) {
                    if (rb_wx.isChecked())
                        Toast.makeText(this, "微信支付", Toast.LENGTH_LONG).show();
                    else if (rb_zb.isChecked())
                        Toast.makeText(this, "支付宝支付", Toast.LENGTH_LONG).show();
                    else if (checkBox.isChecked())
                        Toast.makeText(this, "请选支付方式", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(this, "请同意协议", Toast.LENGTH_LONG).show();
                break;
            case R.id.ll_rhj:
                Toast.makeText(this, "优惠劵", Toast.LENGTH_LONG).show();
                new VipYHJDialog(this).show();
                break;

            case R.id.ll_jh:
                Toast.makeText(this, "激活实体卡", Toast.LENGTH_LONG).show();
                new VipJHDialog(this).show();
                break;

        }
    }
}
