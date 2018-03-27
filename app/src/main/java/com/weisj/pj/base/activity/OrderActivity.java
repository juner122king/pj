package com.weisj.pj.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemOrderFragmentAdapter;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.main.fragment.OrderFragment;
import com.weisj.pj.main.fragment.order.DistributionCommissionView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 2017/12/15.
 */

public class OrderActivity extends BaseActivity implements View.OnClickListener {
    List<View> list = new ArrayList<>();
    private View view;
    private ViewPager viewPager;
    private DistributionCommissionView commissionView;


    @Override
    public void onClick(View v) {

    }

    @Override
    public View initView(Bundle savedInstanceState) {
        view = mLayoutInflater.inflate(R.layout.fragment_order, null);

        initView();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        commissionView.getcart();


    }

    private void initView() {
        commissionView = new DistributionCommissionView(mLayoutInflater,null);
//        recordView = new DistributionRecordView(mInflater);
        list.add(commissionView.getRootView());
//        list.add(recordView.getRootView());
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new ItemOrderFragmentAdapter(list));


    }

    @Override
    public String setTitleStr() {
        return "首饰盒";
    }

    @Override
    public void getRefreshData() {
        if (viewPager.getCurrentItem() == 0) {
            commissionView.getcart();
        }
    }
}
