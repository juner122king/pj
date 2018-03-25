package com.weisj.pj.main.fragment.order;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemCarListAdapter;
import com.weisj.pj.adapter.ItemDistributionCommissionAdapter;
import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.base.activity.GoodDetailActivity;
import com.weisj.pj.base.activity.OrderConfirmActivity;
import com.weisj.pj.base.activity.WebViewActivity;
import com.weisj.pj.bean.CartGoodBean;
import com.weisj.pj.bean.HomeBean;
import com.weisj.pj.bean.OrderBean;
import com.weisj.pj.presenter.OrderPresenter;
import com.weisj.pj.utils.SystemConfig;
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
    private View rootView, selectView1, selectView2, selectView3, selectView4, lastView, refresh_view;
    private RootView rootRela;
    private LinearLayout ll_zhifu;//支付分局  只在待选中页面显示
    private ExpandableListView expandableListView;
    private List<OrderBean.DataEntity> dataList = new ArrayList<>();
    private ItemDistributionCommissionAdapter adapter;
    private ItemCarListAdapter carListAdapter;
    private AbPullToRefreshView refreshView;
    public OrderPresenter presenter;
    private int state = 0;//0等待买家待选中,  order_state=1未发货未签收；order_state=2已发货未签收,  12 待评价 -1表示全部  3待归还  14 已完成
    private TextView noDataView;
    private TextView tv_p;//押金
    private TextView tv_zf_text;//支付按钮文字
    private RecyclerView recyclerView_carlist;
    private View view_delete;//删除选择按钮

    public static String yj;

    private boolean isZFtype = true;//当前是否为支付状态


    public void setWxAndFilterType(int filter_type, String wxName) {

        presenter.getcartList();
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
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expand_list_order);
        expandableListView.setEmptyView(ViewStateUtil.getNoDataView(inflater.getContext(), "暂无数据", Color.parseColor("#ffffff")));
        expandableListView.setOnChildClickListener(this);
        selectView1 = rootView.findViewById(R.id.order_commission_no_pay);
        selectView2 = rootView.findViewById(R.id.order_commission_no_send);
        selectView3 = rootView.findViewById(R.id.order_commission_no_receive);
        selectView4 = rootView.findViewById(R.id.order_commission_no_evaluate);
        refresh_view = rootView.findViewById(R.id.refresh_view);

        recyclerView_carlist = (RecyclerView) rootView.findViewById(R.id.recyclerView_carlist);
        tv_p = (TextView) rootView.findViewById(R.id.tv_p);
        ll_zhifu = (LinearLayout) rootView.findViewById(R.id.ll_zhifu);
        tv_zf_text = (TextView) rootView.findViewById(R.id.tv_zf_text);
        tv_p = (TextView) rootView.findViewById(R.id.tv_p);

        noDataView = (TextView) rootView.findViewById(R.id.no_data);
        lastView = selectView1;
        selectView1.setSelected(true);
        selectView1.setOnClickListener(this);
        selectView2.setOnClickListener(this);
        selectView3.setOnClickListener(this);
        selectView4.setOnClickListener(this);

        ll_zhifu.setOnClickListener(this);


    }

    public void hidezhifu() {
        ll_zhifu.setVisibility(View.GONE);
    }

    public void getdata() {
        replaceData();
        presenter.getInitOrderData(state);
    }

    public void getcart() {
//        replaceCartData();
        presenter.getcartList();
    }

    public void changeOrderState(int state) {
        this.state = state;
        presenter.getInitOrderData(state);
    }

    public void setZFType(boolean isZFtype) {

        if (isZFtype) {

            tv_zf_text.setText("去支付");

        } else {
            tv_zf_text.setText("确认删除");
        }

        this.isZFtype = isZFtype;

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
                getcart();
                ll_zhifu.setVisibility(View.VISIBLE);
                refresh_view.setVisibility(View.GONE);
                recyclerView_carlist.setVisibility(View.VISIBLE);
                expandableListView.setVisibility(View.GONE);
                break;
            case R.id.order_commission_no_send:   //  包含  OrderState = 1  OrderState = 2 ,的数据
                lastView.setSelected(false);
                v.setSelected(true);
                lastView = selectView2;
                changeOrderState(2);
                hidezhifu();
                refresh_view.setVisibility(View.VISIBLE);
                recyclerView_carlist.setVisibility(View.GONE);
                break;
            case R.id.order_commission_no_receive:
                lastView.setSelected(false);
                v.setSelected(true);
                lastView = selectView3;
                changeOrderState(3);
                hidezhifu();
                refresh_view.setVisibility(View.VISIBLE);
                recyclerView_carlist.setVisibility(View.GONE);

                break;
            case R.id.order_commission_no_evaluate:
                lastView.setSelected(false);
                v.setSelected(true);
                lastView = selectView4;
                changeOrderState(14);
                hidezhifu();
                refresh_view.setVisibility(View.VISIBLE);
                recyclerView_carlist.setVisibility(View.GONE);

                break;

            case R.id.no_data:

                // todo 跳到品类页面

                break;
            case R.id.ll_zhifu:

                if (isZFtype) {

                    if (goodBean.getData().size() == 0) {
                        Toast.makeText(rootRela.getContext(), "首饰盒中暂无商品，请先选择商品加入首饰盒", Toast.LENGTH_SHORT).show();
                    } else {

                        // 跳到品类页面
                        Intent intent = new Intent(rootView.getContext(), OrderConfirmActivity.class);
                        intent.putExtra("cartId", cartId);
                        intent.putExtra("yj", yj);

                        rootView.getContext().startActivity(intent);

                    }
                } else {

                    Toast.makeText(rootRela.getContext(), "删除", Toast.LENGTH_SHORT).show();
                    presenter.deleteOrder(getDelId());

                }
                break;
        }
    }

    private String getDelId() {
        String id = "";
        List<String> list = new ArrayList();
        int s = carListAdapter.mCheckStates.size();
        for (int i = 0; i < s; i++) {

            if (carListAdapter.mCheckStates.get(i) == true) {

                list.add(carListAdapter.getData().get(i).getGoodsId());
            }
        }

        for (int i = 0; i < list.size(); i++) {

            if (i == 0) {
                id = list.get(i);
            } else
                id = id.concat("," + list.get(i));


        }


        return id;
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
        noDataView.setOnClickListener(null);
        if (dataList.size() > 0) {
            noDataView.setVisibility(View.GONE);
        } else {
            noDataView.setText("没有更多了");
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
        noDataView.setOnClickListener(null);

        if (dataList.size() > 0) {
            noDataView.setVisibility(View.GONE);
        } else {

            noDataView.setText("没有更多了");
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


    String cartId;//商品cartid
    CartGoodBean goodBean;

    @Override
    public void getCartList(final CartGoodBean cartGoodBean) {
        goodBean = cartGoodBean;
        cartId = "";
        if (cartGoodBean.getData().size() > 0) {
            noDataView.setVisibility(View.GONE);

            if (cartGoodBean.getData().get(0).getRentNum().equals("3")) {
                tv_p.setText("￥500");
                yj = "500";
            } else {
                tv_p.setText("￥1000");
                yj = "1000";
            }
        } else {
            tv_p.setText("￥0");
            noDataView.setOnClickListener(this);
            noDataView.setText("没有选择商品");
            noDataView.setVisibility(View.VISIBLE);


        }
        recyclerView_carlist.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        carListAdapter = new ItemCarListAdapter(cartGoodBean.getData());
        carListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(rootView.getContext(), GoodDetailActivity.class);
                intent.putExtra("goodId", Integer.valueOf((cartGoodBean.getData().get(position)).getGoodsId()));
                rootView.getContext().startActivity(intent);
            }
        });

        recyclerView_carlist.setAdapter(carListAdapter);


        for (int i = 0; i < cartGoodBean.getData().size(); i++) {
            String cart_id = cartGoodBean.getData().get(i).getCartId();

            if (i == 0)
                cartId = cartId.concat(cart_id);
            else
                cartId = cartId.concat("," + cart_id);
        }


    }


    @Override
    public void getCartListFail() {

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
