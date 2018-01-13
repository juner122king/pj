package com.weisj.pj.base.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.weisj.pj.R;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.base.activity.fragment.OneRegisterFragment;
import com.weisj.pj.base.activity.fragment.ThreeRegisterFragment;
import com.weisj.pj.base.activity.fragment.TwoRegisterFragment;
import com.weisj.pj.base.activity.fragment.VIP1Fragment;
import com.weisj.pj.base.activity.fragment.VIP2Fragment;
import com.weisj.pj.view.dialog.SelectCityDialog;
import com.weisj.pj.view.dialog.VipJHDialog;
import com.weisj.pj.view.dialog.VipKTDialog;
import com.weisj.pj.view.dialog.VipYHJDialog;

/**
 * Created by jun on 2017/12/8.
 */

public class VipActivity extends FragmentActivity implements View.OnClickListener {
    private Fragment VIP1_fragment, VIP2_fragment;
    private FragmentManager fragmentManager;
    private int number = 0;
    private int zf_type;//支付方式  0 微信， 1 支付宝
    private final static int zb = 1;
    private final static int wx = 0;

    private RadioButton rb_wx, rb_zb;
    private CheckBox checkBox;
    private TextView tv1, tv2, tv3, tv4, tv5, tv6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        initView();
        fragmentManager = getSupportFragmentManager();
        changeFragment(0);
    }

    public void changeFragment(int number) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (number) {
            case 0:
                if (VIP1_fragment == null) {
                    VIP1_fragment = new VIP1Fragment();
                }
                fragment = VIP1_fragment;
                break;
            case 1:
                if (VIP2_fragment == null) {
                    VIP2_fragment = new VIP2Fragment();
                }
                fragment = VIP2_fragment;
                break;

        }
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    private void initView() {

        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.tv_but).setOnClickListener(this);
//        findViewById(R.id.iv_back).setOnClickListener(this);
//        findViewById(R.id.tv_but).setOnClickListener(this);
//        ((TextView) (findViewById(R.id.tv_but))).setText("会员卡");
//
//
//        view.findViewById(R.id.iv).setOnClickListener(this);
//        view.findViewById(R.id.tv_songren).setOnClickListener(this);
//        view.findViewById(R.id.tv_zf).setOnClickListener(this);
//
//
//        view.findViewById(R.id.ll_rhj).setOnClickListener(this);
//        view.findViewById(R.id.ll_jh).setOnClickListener(this);
//
//        rb_wx = (RadioButton) view.findViewById(R.id.rb_wx);
//        rb_zb = (RadioButton) view.findViewById(R.id.rb_zb);
//        checkBox = (CheckBox) view.findViewById(R.id.cb);

    }


    @Override
    public void onBackPressed() {
        if (number == 0) {
            super.onBackPressed();
        } else {
            changeFragment(number - 1);
        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.iv_back:
                if (number == 0) {
                    finish();
                } else {
                    changeFragment(number - 1);
                }
                break;
            case R.id.tv_but:
//                Toast.makeText(this, "送人", Toast.LENGTH_SHORT).show();
                number = 1;
                changeFragment(1);
                break;
//            case R.id.tv_zf: //支付按钮
//                if (checkBox.isChecked()) {
//                    if (rb_wx.isChecked()) {
////                        Toast.makeText(this, "微信支付", Toast.LENGTH_SHORT).show();
//                        new VipKTDialog(this, true).show();
//                    } else if (rb_zb.isChecked()) {
////                        Toast.makeText(this, "支付宝支付", Toast.LENGTH_SHORT).show();
//                        new VipKTDialog(this, false).show();
//                    } else if (checkBox.isChecked())
//                        Toast.makeText(this, "请选支付方式", Toast.LENGTH_SHORT).show();
//                } else
//                    Toast.makeText(this, "请同意协议", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.ll_rhj:
//                Toast.makeText(this, "优惠劵", Toast.LENGTH_SHORT).show();
//                new VipYHJDialog(this).show();
//                break;
//
//            case R.id.ll_jh:
//                Toast.makeText(this, "激活实体卡", Toast.LENGTH_SHORT).show();
//                new VipJHDialog(this).show();
//                break;
//
        }
    }
}
