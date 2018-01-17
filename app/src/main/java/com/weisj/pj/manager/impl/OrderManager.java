package com.weisj.pj.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.bean.CartGoodBean;
import com.weisj.pj.bean.OrderBean;
import com.weisj.pj.bean.OrderShareBean;
import com.weisj.pj.bean.RecommendBean;
import com.weisj.pj.manager.IOrderManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class OrderManager implements IOrderManager {

    @Override
    public void getOrderData(int state, final int page, int pageNum, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("order_state", String.valueOf(state));
        params.put("page", String.valueOf(page));
        params.put("page_num", String.valueOf(pageNum));

        OkHttpClientManager.postAsyn(Urls.myorders, params, new OkHttpClientManager.ResultCallback<OrderBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.myorders);
            }

            @Override
            public void onResponse(OrderBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.myorders + "&page=" + page);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.myorders);
                }
            }
        });
    }


    @Override
    public void cartlist(final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();

        params.put("member_id", PersonMessagePreferencesUtils.getUid());

        OkHttpClientManager.postAsyn(Urls.cartlist, params, new OkHttpClientManager.ResultCallback<CartGoodBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.cartlist);
            }

            @Override
            public void onResponse(CartGoodBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.cartlist);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.cartlist);
                }
            }
        });
    }
    @Override
    public void getOrderRecordData(final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        OkHttpClientManager.postAsyn(Urls.getsharerecordsbymemberid, params, new OkHttpClientManager.ResultCallback<OrderShareBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.getsharerecordsbymemberid);
            }

            @Override
            public void onResponse(OrderShareBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.getsharerecordsbymemberid);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.getsharerecordsbymemberid);
                }
            }
        });
    }

    @Override
    public void getMyRecommend(final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        OkHttpClientManager.postAsyn(Urls.getcommissionincome, params, new OkHttpClientManager.ResultCallback<RecommendBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.getcommissionincome);
            }

            @Override
            public void onResponse(RecommendBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.getcommissionincome);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.getcommissionincome);
                }
            }
        });
    }

    @Override//删除订单
    public void deleteOrder(int id, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("order_brand_id", String.valueOf(id));
        OkHttpClientManager.postAsyn(Urls.deleteorder, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.deleteorder);
            }

            @Override
            public void onResponse(BaseBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.deleteorder);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.deleteorder);
                }
            }
        });
    }
}
