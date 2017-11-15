package com.weisj.pj.presenter;

import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.manager.IPhoneVerManager;
import com.weisj.pj.manager.impl.PhoneVerManger;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.SystemConfig;
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

    public void getVerCode() {
        if (iRegisterView.getPhoneNumber() != null && iRegisterView.getPhoneNumber().length() == 11) {
            if (iRegisterView.getStaffCode() != null && !iRegisterView.getStaffCode().trim().equals("") && iRegisterView.getStaffCode().trim().length() >= 6) {
                viewState.showLoading();
                iPhoneVerManager.getPhoneVerCode(iRegisterView.getPhoneNumber(), 1, this);
            } else {
                SystemConfig.showToast("请输入正确员工工号");
            }
        } else {
            SystemConfig.showToast("请输入正确手机号");
        }
    }

    @Override
    public void onSuccess(Object data, String url) {
        viewState.showLoadFinish();
        iRegisterView.successGetCode((BaseBean) data);
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showLoadFinish();
        SystemConfig.showToast("获取手机验证码失败");
    }
}
