package com.weisj.pj.presenter;

import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.UserBean;
import com.weisj.pj.manager.ILoginManager;
import com.weisj.pj.manager.impl.LoginManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.viewinterface.ILoginView;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class LoginPresenter implements IOnManagerListener {
    private ILoginView loginView;
    private ILoginManager loginManager;
    private BaseViewState viewState;

    public LoginPresenter(ILoginView loginView, BaseViewState viewState) {
        this.loginView = loginView;
        this.loginManager = new LoginManager();
        this.viewState = viewState;
    }

    public void login() {
        if (loginView.getUserName() != null) {
            if (  SystemConfig.isPassword(loginView.getUserPassWord())) {
                viewState.showLoading();
                loginManager.login(loginView.getUserName(), loginView.getUserPassWord(), this);
            }
        } else {
            SystemConfig.showToast("请输入正确手机号");
        }
    }


    @Override
    public void onSuccess(Object data, String url) {
        viewState.showLoadFinish();
        UserBean ub = (UserBean) data;
        if (ub.getCode().equals("1")) {
            PersonMessagePreferencesUtils.storeInfo(ub);
            loginView.successLogin();
        } else {
            SystemConfig.showToast(ub.getMsg());
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showLoadFinish();
        SystemConfig.showToast("网络错误");
    }
}
