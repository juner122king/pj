package com.weisj.pj.presenter;

import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.OrderShareBean;
import com.weisj.pj.manager.IOrderManager;
import com.weisj.pj.manager.impl.OrderManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.viewinterface.IOrderShareView;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class OrderSharePresenter implements IOnManagerListener {
    private BaseViewState viewState;
    private IOrderManager orderManager;
    private IOrderShareView orderShareView;

    public OrderSharePresenter(IOrderShareView orderShareView, BaseViewState viewState) {
        this.orderShareView = orderShareView;
        this.viewState = viewState;
        orderManager = new OrderManager();
    }

    public void getData() {
        viewState.showLoading();
        orderManager.getOrderRecordData(this);
    }

    @Override
    public void onSuccess(Object data, String url) {
        OrderShareBean bean = (OrderShareBean) data;
        viewState.showLoadFinish();
        if (bean.getCode().equals("1")) {
            orderShareView.getData(bean);
        } else {
            orderShareView.getFail();
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showNoNetWork();
    }
}
