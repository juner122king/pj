package com.weisj.pj.base.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.weisj.pj.R;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.base.activity.fragment.OneRegisterFragment;
import com.weisj.pj.base.activity.fragment.ThreeRegisterFragment;
import com.weisj.pj.base.activity.fragment.TwoRegisterFragment;
import com.weisj.pj.base.activity.fragment.VIP1Fragment;
import com.weisj.pj.base.activity.fragment.VIP2Fragment;
import com.weisj.pj.bean.ShareData;
import com.weisj.pj.utils.ImageLoaderUtils;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.view.dialog.SelectCityDialog;
import com.weisj.pj.view.dialog.ShareViewDialog;
import com.weisj.pj.view.dialog.VipJHDialog;
import com.weisj.pj.view.dialog.VipKTDialog;
import com.weisj.pj.view.dialog.VipYHJDialog;

/**
 * Created by jun on 2017/12/8.
 */

public class VipActivity extends BaseActivity implements View.OnClickListener {
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
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_vip, null);
        initView(view);
        return view;
    }


    @Override
    public String setTitleStr() {
        return "会员卡";
    }

    @Override
    public void getRefreshData() {

    }


    private void initView(View view) {

        setRightText("送人", true);


    }

    @Override
    public void onHeadClick(View v) {
        if (v.getId() == R.id.root_head_right_text) {
            startActivity(new Intent(this, VipSRActivity.class));

        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.tv_zf: //支付按钮
                if (checkBox.isChecked()) {
                    if (rb_wx.isChecked()) {
//                        Toast.makeText(this, "微信支付", Toast.LENGTH_SHORT).show();
                        new VipKTDialog(this, true).show();
                    } else if (rb_zb.isChecked()) {
//                        Toast.makeText(this, "支付宝支付", Toast.LENGTH_SHORT).show();
                        new VipKTDialog(this, false).show();
                    } else if (checkBox.isChecked())
                        Toast.makeText(this, "请选支付方式", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "请同意协议", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_rhj:
                Toast.makeText(this, "优惠劵", Toast.LENGTH_SHORT).show();
                new VipYHJDialog(this).show();
                break;

            case R.id.ll_jh:
                Toast.makeText(this, "激活实体卡", Toast.LENGTH_SHORT).show();
                new VipJHDialog(this).show();
                break;

        }
    }
}
