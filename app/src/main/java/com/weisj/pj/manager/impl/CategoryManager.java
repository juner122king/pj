package com.weisj.pj.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.pj.bean.GoodBean;
import com.weisj.pj.manager.ICategoryManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class CategoryManager implements ICategoryManager {


    //查询商品
    @Override
    public void getCateContent(final String page, final String goods_params, final IOnManagerListener onManagerListener) {
        Map<String, String> params = new HashMap<>();
        params.put("page", page);
        params.put("page_num", "10");
        params.put("json_goods_params", goods_params);//商品类别
        OkHttpClientManager.postAsyn(Urls.searchByProperties, params, new OkHttpClientManager.ResultCallback<GoodBean>() {
            @Override
            public void onError(Request request, Exception e) {
                onManagerListener.onFail(e, Urls.searchByProperties);
            }

            @Override
            public void onResponse(GoodBean response) {
                if (response != null) {
                    onManagerListener.onSuccess(response, Urls.searchByProperties);
                } else {
                    onManagerListener.onFail(new RuntimeException("null"), Urls.searchByProperties);
                }
            }
        });
    }


}
