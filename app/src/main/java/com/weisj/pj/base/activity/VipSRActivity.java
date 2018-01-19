package com.weisj.pj.base.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.weisj.pj.R;
import com.weisj.pj.base.BaseActivity;

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
