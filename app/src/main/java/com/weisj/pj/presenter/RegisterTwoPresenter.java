package com.weisj.pj.presenter;

import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.bean.VerCodeBean;
import com.weisj.pj.manager.IPhoneVerManager;
import com.weisj.pj.manager.impl.PhoneVerManger;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.viewinterface.IRegisterTwoView;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class RegisterTwoPresenter implements IOnManagerListener {
    private BaseViewState viewState;
    private IRegisterTwoView iRegisterTwoView;
    private IPhoneVerManager iPhoneVerManager;

    public RegisterTwoPresenter(BaseViewState viewState, IRegisterTwoView iRegisterTwoView) {
        this.viewState = viewState;
        this.iRegisterTwoView = iRegisterTwoView;
        iPhoneVerManager = new PhoneVerManger();
    }

    public void verCode() {
        if (iRegisterTwoView.getVerCode() != null && !iRegisterTwoView.getVerCode().trim().equals("")) {
            viewState.showLoading();
            iPhoneVerManager.verifactionCode(iRegisterTwoView.getPhoneNumber(), 1, iRegisterTwoView.getVerCode(), this);
        } else {
            SystemConfig.showToast("请输入验证码");
        }
    }

    public void againGetCode() {
        iPhoneVerManager.getPhoneVerCode(iRegisterTwoView.getPhoneNumber(), 1, this);
    }


    @Override
    public void onSuccess(Object data, String url) {
        viewState.showLoadFinish();
        if (url.equals(Urls.checkticket)) {
            iRegisterTwoView.successVerCode((VerCodeBean) data);
        } else {
            iRegisterTwoView.successAgainGetCode((BaseBean) data);
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showLoadFinish();
        SystemConfig.showToast("网络错误");
    }
}
