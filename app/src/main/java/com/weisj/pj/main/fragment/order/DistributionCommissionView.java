package com.weisj.pj.main.fragment.order;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemDistributionCommissionAdapter;
import com.weisj.pj.base.BaseViewState;
//import com.weisj.pj.base.activity.OrderDetailActivity;
//import com.weisj.pj.base.activity.WalletActivity;
import com.weisj.pj.bean.OrderBean;
import com.weisj.pj.presenter.OrderPresenter;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.utils.TextViewUtils;
import com.weisj.pj.utils.ViewStateUtil;
import com.weisj.pj.view.RootView;
import com.weisj.pj.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.pj.viewinterface.IOrderView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class DistributionCommissionView implements View.OnClickListener, BaseViewState, IOrderView, AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener, RootView.NoWifiListener, ExpandableListView.OnChildClickListener {
    private LayoutInflater inflater;
    private View rootView, selectView1, selectView2, selectView3, selectView4, lastView;
    private RootView rootRela;
    private LinearLayout ll_zhifu;//支付分局  只在待选中页面显示
    private ExpandableListView expandableListView;
    private List<OrderBean.DataEntity> dataList = new ArrayList<>();
    private ItemDistributionCommissionAdapter adapter;
    private AbPullToRefreshView refreshView;
    public OrderPresenter presenter;
    private int state = 0;//0等待买家待选中,1买家已付款,2卖家已发货，待买家收货,  12 待评价 -1表示全部
    private TextView noDataView;
    private TextView tv_p;//押金


    public void setWxAndFilterType(int filter_type, String wxName) {

        presenter.getInitOrderData(state);
    }

    public DistributionCommissionView(LayoutInflater inflater) {
        this.inflater = inflater;
        initView();
    }

    public View getRootView() {
        return rootRela;
    }

    private void initView() {
        rootRela = new RootView(inflater.getContext());
        presenter = new OrderPresenter(this, this);
        rootView = inflater.inflate(R.layout.view_distribution_commission, null);
        refreshView = (AbPullToRefreshView) rootView.findViewById(R.id.refresh_view);
        refreshView.setOnHeaderRefreshListener(this);
        refreshView.setOnFooterLoadListener(this);
        rootRela.addContentView(rootView);
        rootRela.isHintHeadBar(true);
        rootRela.setNoWifiListener(this);
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expand_list_view);
        expandableListView.setEmptyView(ViewStateUtil.getNoDataView(inflater.getContext(), "暂无数据", Color.parseColor("#ffffff")));
        expandableListView.setOnChildClickListener(this);
        selectView1 = rootView.findViewById(R.id.order_commission_no_pay);
        selectView2 = rootView.findViewById(R.id.order_commission_no_send);
        selectView3 = rootView.findViewById(R.id.order_commission_no_receive);
        selectView4 = rootView.findViewById(R.id.order_commission_no_evaluate);
        tv_p = (TextView) rootView.findViewById(R.id.tv_p);
        ll_zhifu = (LinearLayout) rootView.findViewById(R.id.ll_zhifu);

        noDataView = (TextView) rootView.findViewById(R.id.no_data);
        lastView = selectView1;
        selectView1.setSelected(true);
        selectView1.setOnClickListener(this);
        selectView2.setOnClickListener(this);
        selectView3.setOnClickListener(this);
        selectView4.setOnClickListener(this);
        presenter.getInitOrderData(state);
    }

    public void hidezhifu() {
        ll_zhifu.setVisibility(View.GONE);
    }

    public void getdata() {
        replaceData();
        presenter.getInitOrderData(state);
    }

    public void changeOrderState(int state) {
        this.state = state;
        presenter.getInitOrderData(state);
    }


    public void setExpandListView() {
        if (expandableListView != null) {
            expandableListView.setGroupIndicator(null);
            for (int i = 0; i < dataList.size(); i++) {
                expandableListView.expandGroup(i);
            }
            expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                    OrderBean.DataEntity orderData = dataList.get(groupPosition);
//                    Intent intent = new Intent(inflater.getContext(), OrderDetailActivity.class);
//                    intent.putExtra("orderId", orderData.getOrder_brand_id());
//                    inflater.getContext().startActivity(intent);
                    return true;
                }
            });
        }
    }


    public void replaceData() {
        adapter = new ItemDistributionCommissionAdapter(inflater, dataList, this);
        expandableListView.setAdapter(adapter);
        setExpandListView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_commission_no_pay:
                lastView.setSelected(false);
                v.setSelected(true);
                lastView = selectView1;
                changeOrderState(0);
                ll_zhifu.setVisibility(View.VISIBLE);
                break;
            case R.id.order_commission_no_send:
                lastView.setSelected(false);
                v.setSelected(true);
                lastView = selectView2;
                changeOrderState(1);
                hidezhifu();
                break;
            case R.id.order_commission_no_receive:
                lastView.setSelected(false);
                v.setSelected(true);
                lastView = selectView3;
                changeOrderState(2);
                hidezhifu();
                break;
            case R.id.order_commission_no_evaluate:
                lastView.setSelected(false);
                v.setSelected(true);
                lastView = selectView4;
                changeOrderState(12);
                hidezhifu();
                break;
        }
    }

    @Override
    public void showLoading() {
        changeState(RootView.ViewState.LOADING);
    }

    @Override
    public void showInitLoading() {
        changeState(RootView.ViewState.ONELOADING);
    }

    @Override
    public void showNoNetWork() {
        changeState(RootView.ViewState.NONETWORK);
    }

    @Override
    public void showNoData() {
        changeState(RootView.ViewState.NODATA);
    }

    @Override
    public void showLoadFinish() {
        changeState(RootView.ViewState.SUCCESS);
    }

    public void changeState(RootView.ViewState state) {
        rootRela.changeRootViewState(state);
    }

    @Override
    public void getOrderData(OrderBean orderBean) {
        if (orderBean != null) {
            dataList.addAll(orderBean.getData());
            adapter.notifyDataSetChanged();
            setExpandListView();
        }
        refreshView.onFooterLoadFinish();
        refreshView.onHeaderRefreshFinish();
    }

    @Override
    public void getInitOrderData(OrderBean orderBean) {
        dataList.clear();
        dataList.addAll(orderBean.getData());
        if (dataList.size() > 0) {
            noDataView.setVisibility(View.GONE);
        } else {
            noDataView.setVisibility(View.VISIBLE);
        }
        adapter = new ItemDistributionCommissionAdapter(inflater, dataList, this);
        expandableListView.setAdapter(adapter);
        setExpandListView();
        refreshView.onFooterLoadFinish();
        refreshView.onHeaderRefreshFinish();
    }

    @Override
    public void getOrderFail() {
        dataList.clear();
        if (dataList.size() > 0) {
            noDataView.setVisibility(View.GONE);
        } else {
            noDataView.setVisibility(View.VISIBLE);
        }
        if (adapter == null) {
            adapter = new ItemDistributionCommissionAdapter(inflater, dataList, this);
            expandableListView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        refreshView.onFooterLoadFinish();
        refreshView.onHeaderRefreshFinish();
    }

    @Override
    public void getRecommendStr(String money) {

    }

    @Override
    public void deleteOrderSuccess() {
        SystemConfig.showToast("订单删除成功,刷新中···");
        presenter.getInitOrderData(state);
    }

    @Override
    public void deleteOrderFail() {
        SystemConfig.showToast("订单删除失败");
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getInitOrderData(state);
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        presenter.getOrderdata(state);
    }

    @Override
    public void onNoWifiLister(View v) {
        getdata();
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        return true;
    }

}
