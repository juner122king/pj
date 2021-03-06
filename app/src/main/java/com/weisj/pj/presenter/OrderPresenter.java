package com.weisj.pj.presenter;

import android.widget.Toast;

import com.weisj.pj.MyApplication;
import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.bean.CartGoodBean;
import com.weisj.pj.bean.OrderBean;
import com.weisj.pj.bean.RecommendBean;
import com.weisj.pj.manager.IOrderManager;
import com.weisj.pj.manager.impl.OrderManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.viewinterface.IOrderView;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class OrderPresenter implements IOnManagerListener {
    private IOrderManager orderManager;
    private IOrderView orderView;
    private BaseViewState viewState;
    private int page = 1;
    private int pageNum = 10;

    public OrderPresenter(BaseViewState viewState, IOrderView orderView) {
        this.viewState = viewState;
        this.orderView = orderView;
        orderManager = new OrderManager();
    }

    public void getInitOrderData(int orderState) {
        page = 1;
        if (orderState == 1 || orderState == 2) {
            orderManager.getOrderData1and2(page, pageNum, this);
        } else
            orderManager.getOrderData(orderState, page, pageNum, this);
    }

    public void getOrderdata(int orderState) {
        page++;
        if (orderState == 1 || orderState == 2) {
            orderManager.getOrderData1and2(page, pageNum, this);
        } else
            orderManager.getOrderData(orderState, page, pageNum, this);
    }

    public void deleteOrder(String id) {
        orderManager.deleteOrder(id, this);
    }


    public void getcartList() {
        orderManager.cartlist(this);

    }

    @Override
    public void onSuccess(Object data, String url) {
        if (url.equals(Urls.getcommissionincome)) {
            RecommendBean bean = (RecommendBean) data;
            if (((RecommendBean) data).getCode().equals("1")) {
                orderView.getRecommendStr(bean.getData());
            }
        } else if (url.equals(Urls.delToCart)) {
            BaseBean baseBean = (BaseBean) data;
            if (baseBean.getCode().equals("1")) {
                Toast.makeText(MyApplication.getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                getcartList();
            } else {
                orderView.getCartListFail();

            }
        } else if (url.equals(Urls.cartlist)) {
            CartGoodBean cgb = (CartGoodBean) data;
            viewState.showLoadFinish();
            if (cgb.getCode().equals("1") && cgb.getData() != null) {
                orderView.getCartList(cgb);
            } else {
                orderView.getCartListFail();

            }

        } else {
            OrderBean orderBean = (OrderBean) data;
            viewState.showLoadFinish();
            String[] str = url.split("&page=");
            String page = str[1];
            if (page.equals("1")) {
                if (orderBean.getCode().equals("1") && orderBean.getData() != null) {
                    orderView.getInitOrderData(orderBean);
                } else {
                    orderView.getOrderFail();
                }
            } else {
                if (orderBean.getCode().equals("1") && orderBean.getData() != null) {
                    orderView.getOrderData(orderBean);
                } else {
                    orderView.getOrderData(null);
                }
            }
        }
    }

    @Override
    public void onFail(Exception e, String url) {
//        viewState.showNoNetWork();
    }
}
