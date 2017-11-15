package com.weisj.pj.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.pj.bean.GoodBean;
import com.weisj.pj.bean.Region;
import com.weisj.pj.bean.SearchBrand;
import com.weisj.pj.manager.ISearchListGoodManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.CommenString;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class SearchListGoodManager implements ISearchListGoodManager {
    private String url;

    public SearchListGoodManager(String url) {
        this.url = url;
    }

    public SearchListGoodManager() {
        this.url = Urls.getgoodsbycategoryorder;
    }

    @Override
    public void getGoods(String goodName, int category_id, int order_field, int order_type, final int page, int pageNum, int brand_id, int district_id, int from, final IOnManagerListener onManagerListener) {
        Map<String, String> params = new HashMap<>();

        String log = "";

        if (category_id != 0)
            params.put("category_id", String.valueOf(category_id));
        log = log+"category_id = " +String.valueOf(category_id)+"   ";
        params.put("order_field", String.valueOf(order_field));
        if (order_field != 0) {
            params.put("order_type", String.valueOf(order_type));
            log = log+"order_type = " +String.valueOf(order_type)+"   ";
        }
        if (goodName != null) {
            params.put("goods_name", goodName);
            log = log+"goods_name = " +String.valueOf(goodName)+"   ";
        }
        if (from != 0) {
            params.put("city", CommenString.selectCity);
            log = log+"city = " +CommenString.selectCity+"   ";
        }
        params.put("page", String.valueOf(page));
        params.put("page_num", String.valueOf(pageNum));
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("brand_id", String.valueOf(brand_id));
        params.put("district_id", String.valueOf(district_id));


        log = log+"brand_id = " +String.valueOf(brand_id)+"   ";
        log = log+"district_id = " +String.valueOf(district_id)+"   ";

        System.out.println("=============++=========" + log);

        OkHttpClientManager.postAsyn(url, params, new OkHttpClientManager.ResultCallback<GoodBean>() {
            @Override
            public void onError(Request request, Exception e) {
                onManagerListener.onFail(e, url + "&page=" + page);
            }

            @Override
            public void onResponse(GoodBean response) {
                if (response != null) {
                    onManagerListener.onSuccess(response, url + "&page=" + page);
                } else {
                    onManagerListener.onFail(new RuntimeException("null"), url + "&page=" + page);
                }
            }
        });
    }

    @Override
    public void getallregions(final IOnManagerListener onManagerListener) {
        OkHttpClientManager.postAsyn(Urls.getallregions, new OkHttpClientManager.ResultCallback<Region>() {
            @Override
            public void onError(Request request, Exception e) {
                onManagerListener.onFail(e, Urls.getallregions);
            }

            @Override
            public void onResponse(Region response) {
                if (response != null) {
                    onManagerListener.onSuccess(response, Urls.getallregions);
                } else {
                    onManagerListener.onFail(new RuntimeException("null"), Urls.getallregions);
                }
            }
        });
    }

    @Override
    public void getbrandbydistrict(int district_id, final IOnManagerListener onManagerListener) {
        Map<String, String> params = new HashMap<>();
        params.put("district_id", String.valueOf(district_id));
        OkHttpClientManager.postAsyn(Urls.getbrandbydistrict, params, new OkHttpClientManager.ResultCallback<SearchBrand>() {
            @Override
            public void onError(Request request, Exception e) {
                onManagerListener.onFail(e, Urls.getbrandbydistrict);
            }

            @Override
            public void onResponse(SearchBrand response) {
                if (response != null) {
                    onManagerListener.onSuccess(response, Urls.getbrandbydistrict);
                } else {
                    onManagerListener.onFail(new RuntimeException("null"), Urls.getbrandbydistrict);
                }
            }
        });
    }
}
