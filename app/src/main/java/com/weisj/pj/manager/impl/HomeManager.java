package com.weisj.pj.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.pj.bean.ActivityBean;
import com.weisj.pj.bean.HomeBanner;
import com.weisj.pj.bean.HomeBean;
import com.weisj.pj.manager.IHomeManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class HomeManager implements IHomeManager {

    @Override
    public void getHomeInfo(final IOnManagerListener onHomeListener) {
        Map<String, String> params = new HashMap<>();
        OkHttpClientManager.postAsyn(Urls.homepage, params, new OkHttpClientManager.ResultCallback<HomeBean>() {
            @Override
            public void onError(Request request, Exception e) {
                onHomeListener.onFail(e, Urls.homepage);
            }

            @Override
            public void onResponse(HomeBean response) {
                if (response != null) {
                    onHomeListener.onSuccess(response, Urls.homepage);
                } else {
                    onHomeListener.onFail(new RuntimeException("null"), Urls.homepage);
                }
            }
        });
    }

    @Override
    public void getHomeBanner(final IOnManagerListener onHomeListener) {
        OkHttpClientManager.getAsyn(Urls.material, new OkHttpClientManager.ResultCallback<HomeBanner>() {
            @Override
            public void onError(Request request, Exception e) {
                onHomeListener.onFail(e, Urls.material);
            }

            @Override
            public void onResponse(HomeBanner response) {
                if (response != null) {
                    onHomeListener.onSuccess(response, Urls.material);
                } else {
                    onHomeListener.onFail(new RuntimeException("null"), Urls.material);
                }
            }
        });
    }


    @Override
    public void getActivitysInHome(final IOnManagerListener onHomeListener) {
        Map<String, String> params = new HashMap<>();
        if (PersonMessagePreferencesUtils.getUid() == null) {
            return;
        }
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        OkHttpClientManager.postAsyn(Urls.getActivitysInHome, params, new OkHttpClientManager.ResultCallback<ActivityBean>() {
            @Override
            public void onError(Request request, Exception e) {
                onHomeListener.onFail(e, Urls.getActivitysInHome);
            }

            @Override
            public void onResponse(ActivityBean response) {
                if (response != null) {
                    onHomeListener.onSuccess(response, Urls.getActivitysInHome);
                } else {
                    onHomeListener.onFail(new RuntimeException("null"), Urls.getActivitysInHome);
                }
            }
        });
    }


}
