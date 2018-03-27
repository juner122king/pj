package com.weisj.pj.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemOrderFragmentAdapter;
import com.weisj.pj.base.BaseFragment;
import com.weisj.pj.base.activity.MyShowActivity;
import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.main.fragment.order.DistributionCommissionView;
import com.weisj.pj.main.fragment.order.DistributionRecordView;
import com.weisj.pj.view.FiltratePopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 16/6/21.
 * 首饰盒主页
 */
public class OrderFragment extends BaseFragment implements View.OnClickListener {
    List<View> list = new ArrayList<>();
    private View view;
    private ViewPager viewPager;
    private DistributionCommissionView commissionView;
    private DistributionRecordView recordView;
    private FiltratePopupWindow popupWindow;
    private BaseBean baseBean;
    private TextView t_r;
    boolean isZFtype = true;//当前是否为支付状态

    private Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    t_r.setVisibility(View.VISIBLE);

                    break;
                case 2:
                    t_r.setVisibility(View.INVISIBLE);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order, null);
        this.view = view;
//        rootView.isHintHeadBar(true);
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        commissionView.getcart();
        t_r.setText("编辑");
        isZFtype = true;
        commissionView.setZFType(isZFtype);
    }

    private void initView() {
        rootView.isHintHeadBar(true);
        commissionView = new DistributionCommissionView(mInflater, myHandler);
//        recordView = new DistributionRecordView(mInflater);
        list.add(commissionView.getRootView());
//        list.add(recordView.getRootView());
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new ItemOrderFragmentAdapter(list));
//        viewPager.addOnPageChangeListener(this);
        t_r = (TextView) view.findViewById(R.id.tv_r);
        t_r.setOnClickListener(this);

    }

    @Override
    public String setTitleStr() {
        return "首饰盒";
    }

    @Override
    public void getRefreshData() {
        if (viewPager.getCurrentItem() == 0) {
            commissionView.getcart();
        } else {
            recordView.getdata();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_r:

                if (isZFtype) {
                    t_r.setText("完成");
                    isZFtype = false;


                } else {
                    t_r.setText("编辑");
                    isZFtype = true;
                }
                commissionView.setZFType(isZFtype);
                break;
        }


    }
}
