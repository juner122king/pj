package com.weisj.pj.base.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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



    private RecyclerView recyclerView;


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
