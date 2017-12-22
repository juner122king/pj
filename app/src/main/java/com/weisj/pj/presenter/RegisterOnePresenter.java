package com.weisj.pj.presenter;

import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.bean.VerCodeBean;
import com.weisj.pj.manager.IPhoneVerManager;
import com.weisj.pj.manager.impl.PhoneVerManger;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.utils.Utility;
import com.weisj.pj.viewinterface.IRegisterOneView;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class RegisterOnePresenter implements IOnManagerListener {
    private IRegisterOneView iRegisterView;
    private IPhoneVerManager iPhoneVerManager;
    private BaseViewState viewState;

    public RegisterOnePresenter(IRegisterOneView iRegisterView, BaseViewState viewState) {
        this.iRegisterView = iRegisterView;
        iPhoneVerManager = new PhoneVerManger();
        this.viewState = viewState;
    }


    //验证手机验证码
    public void verCode() {
        if (iRegisterView.getVerCode() != null && !iRegisterView.getVerCode().trim().equals("")) {
            viewState.showLoading();
            iPhoneVerManager.verifactionCode(iRegisterView.getPhoneNumber(), 1, iRegisterView.getVerCode(), this);
        } else {
            SystemConfig.showToast("请输入验证码");
        }
    }

    //发送手机验证码
    public void againGetCode() {
        String phone = iRegisterView.getPhoneNumber();

        if (Utility.isMobile(phone))
            iPhoneVerManager.getPhoneVerCode(phone, 1, this);
        else
            SystemConfig.showToast("请输入正确的手机号码");

    }

    @Override
    public void onSuccess(Object data, String url) {
        viewState.showLoadFinish();
        if (url.equals(Urls.checkticket)) {
            iRegisterView.successVerCode((VerCodeBean) data);
        } else {
            iRegisterView.successAgainGetCode((BaseBean) data);
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showLoadFinish();
        SystemConfig.showToast("获取手机验证码失败");
    }
}
