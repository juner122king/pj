package com.weisj.pj.manager.listener;

import com.squareup.okhttp.Request;
import com.weisj.pj.utils.OkHttpClientManager;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class OnResultCallBack extends OkHttpClientManager.ResultCallback {
    private IOnManagerListener listener;
    private String url;

    public OnResultCallBack(IOnManagerListener listener, String url, Class clz) {
        this.listener = listener;
        this.url = url;
        mType = clz;
    }

    @Override
    public void onError(Request request, Exception e) {
        if (listener != null)
            listener.onFail(e, url);
    }

    @Override
    public void onResponse(Object response) {
        if (listener != null) {
            if (response != null) {
                listener.onSuccess(response, url);
            } else {
                listener.onFail(new RuntimeException("null"), url);
            }
        }
    }
}
