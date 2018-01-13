package com.weisj.pj.manager.impl;

import com.squareup.okhttp.Request;
import com.umeng.message.UmengRegistrar;
import com.weisj.pj.bean.UserBean;
import com.weisj.pj.manager.ILoginManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class LoginManager implements ILoginManager {
    @Override
    public void login(String userName, String password, final IOnManagerListener loginListerer) {
        Map<String, String> params = new HashMap<>();
        params.put("member", userName);
        try {
            params.put("password", SystemConfig.md5Encode(password));
        } catch (Exception e) {
            e.printStackTrace();
            loginListerer.onFail(new RuntimeException("运行失败"), Urls.login);
            return;
        }
//        String device_token = UmengRegistrar.getRegistrationId(SystemConfig.getContext());
//        if (device_token != null) {
//            params.put("mobile_id", device_token);
//        } else {
//            params.put("mobile_id", "123456");
//        }
        OkHttpClientManager.postAsyn(Urls.login, params, new OkHttpClientManager.ResultCallback<UserBean>() {
            @Override
            public void onError(Request request, Exception e) {
                loginListerer.onFail(e, Urls.login);
            }

            @Override
            public void onResponse(UserBean response) {
                if (response != null) {
                    loginListerer.onSuccess(response, Urls.login);
                } else {
                    loginListerer.onFail(new RuntimeException("null"), Urls.login);
                }
            }
        });
    }
}
