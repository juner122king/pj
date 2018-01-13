package com.weisj.pj.base.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.weisj.pj.R;
import com.weisj.pj.base.BaseFragment;

/**
 * Created by jun on 2018/1/9.
 */

public class VIP1Fragment extends BaseFragment implements View.OnClickListener{
    @Override
    public void onClick(View v) {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView.isHintHeadBar(true);
        View view = inflater.inflate(R.layout.activity_vip_frgment1, null);

        initView(view);

        return view;
    }

    private void initView(View view) {
//        phone = (EditText) view.findViewById(R.id.phone);
//        staff_number = (EditText) view.findViewById(R.id.staff_number);
//        tv_reset_time = (TextView) view.findViewById(R.id.tv_reset_time);
//
//        view.findViewById(R.id.nextBt).setOnClickListener(this);
//        view.findViewById(R.id.tv_reset_time).setOnClickListener(this);
//

    }
    @NonNull
    @Override
    public String setTitleStr() {
        return null;
    }

    @Override
    public void getRefreshData() {

    }
}
