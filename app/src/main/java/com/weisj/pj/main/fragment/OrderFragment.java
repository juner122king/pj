package com.weisj.pj.main.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemOrderFragmentAdapter;
import com.weisj.pj.base.BaseFragment;
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
public class OrderFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener, FiltratePopupWindow.FiltrateListener {
    List<View> list = new ArrayList<>();
    private View view;
    private ViewPager viewPager;
    private DistributionCommissionView commissionView;
    private DistributionRecordView recordView;
    private FiltratePopupWindow popupWindow;
    private BaseBean baseBean;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order, null);
        this.view = view;
        rootView.isHintHeadBar(true);
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        commissionView.getdata();
        commissionView.getcart();
//        recordView.getdata();


    }

    private void initView() {
        commissionView = new DistributionCommissionView(mInflater);
//        recordView = new DistributionRecordView(mInflater);
        list.add(commissionView.getRootView());
//        list.add(recordView.getRootView());
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new ItemOrderFragmentAdapter(list));
        viewPager.addOnPageChangeListener(this);


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
    }

    private void changeBarState(int state) {
//        if (state == 0) {
//            topImage.setVisibility(View.GONE);
//            filtrateBt.setVisibility(View.VISIBLE);
//        } else {
//            topImage.setVisibility(View.VISIBLE);
//            filtrateBt.setVisibility(View.GONE);
//        }
//        leftBar.setSelected(state == 0);
//        rightBar.setSelected(state == 1);
//        topImage.setSelected(state == 0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        changeBarState(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSure(String wxName, int filter_type) {
        if (commissionView != null){
            commissionView.setWxAndFilterType(filter_type,wxName);
        }
    }
}
