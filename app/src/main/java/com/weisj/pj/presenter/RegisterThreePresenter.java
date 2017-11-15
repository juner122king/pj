package com.weisj.pj.presenter;

import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.bean.UserBean;
import com.weisj.pj.manager.ILoginManager;
import com.weisj.pj.manager.IRegisterManager;
import com.weisj.pj.manager.impl.LoginManager;
import com.weisj.pj.manager.impl.RegisterManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.viewinterface.IRegisterThreeView;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class RegisterThreePresenter implements IOnManagerListener {
    private BaseViewState viewState;
    private IRegisterManager iRegisterManager;
    private IRegisterThreeView iRegisterThreeView;
    private ILoginManager loginManager;

    public RegisterThreePresenter(IRegisterThreeView iRegisterThreeView, BaseViewState viewState) {
        this.iRegisterThreeView = iRegisterThreeView;
        this.viewState = viewState;
        iRegisterManager = new RegisterManager();
        loginManager = new LoginManager();
    }

    public void register() {
        if (SystemConfig.isPassword(iRegisterThreeView.getPassword()) && iRegisterThreeView.getPassword().equals(iRegisterThreeView.getTwoPassword())) {
            viewState.showLoading();
            iRegisterManager.registerNumber(iRegisterThreeView.getPhoneNumber(), iRegisterThreeView.getPassword(), iRegisterThreeView.getVCode(), iRegisterThreeView.getStaffCode(), iRegisterThreeView.getStaffCity(), this);
        } else {
            SystemConfig.showToast("两次密码不一致");
        }
    }

    public void forgetPass() {
        if (SystemConfig.isPassword(iRegisterThreeView.getPassword()) && iRegisterThreeView.getPassword().equals(iRegisterThreeView.getTwoPassword())) {
            viewState.showLoading();
            iRegisterManager.forgetPass(iRegisterThreeView.getPhoneNumber(), iRegisterThreeView.getPassword(), iRegisterThreeView.getVCode(), this);
        } else {
            SystemConfig.showToast("两次密码不一致");
        }
    }


    @Override
    public void onSuccess(Object data, String url) {
        if (url.equals(Urls.register)) {
            if (SystemConfig.isGetNetSuccess((BaseBean) data)) {
                loginManager.login(iRegisterThreeView.getPhoneNumber(), iRegisterThreeView.getPassword(), this);
            }
        } else {
            viewState.showLoadFinish();
            UserBean userBean = (UserBean) data;
            if (userBean.getCode().equals("1")) {
                PersonMessagePreferencesUtils.storeInfo(userBean);
                iRegisterThreeView.successSetPass();
            } else {
                SystemConfig.showToast(userBean.getMsg());
            }
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showLoadFinish();
        SystemConfig.showToast("网络失败");
    }
}
