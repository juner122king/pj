package com.weisj.pj.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.pj.bean.CenterBean;
import com.weisj.pj.manager.ICenterManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class CenterManager implements ICenterManager {
    @Override
    public void getCenterInfo(final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        if (PersonMessagePreferencesUtils.getUid() == null) {
            return;
        }
        params.put("member", PersonMessagePreferencesUtils.getUid());
        OkHttpClientManager.postAsyn(Urls.membercenter, params, new OkHttpClientManager.ResultCallback<CenterBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.membercenter);
            }

            @Override
            public void onResponse(CenterBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.membercenter);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.membercenter);
                }
            }
        });
    }
}
