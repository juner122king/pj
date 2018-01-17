package com.weisj.pj.viewinterface;

import com.weisj.pj.bean.CartGoodBean;
import com.weisj.pj.bean.OrderBean;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface IOrderView {

    void getOrderData(OrderBean orderBean);

    void getInitOrderData(OrderBean orderBean);

    void getOrderFail();

    void getRecommendStr(String money);

    void deleteOrderSuccess();

    void deleteOrderFail();

    void getCartList(CartGoodBean cartGoodBean);
    void getCartListFail();
}
