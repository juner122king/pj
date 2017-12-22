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


    @Override
    public void onSuccess(Object data, String url) {
        viewState.showLoadFinish();

    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showLoadFinish();
        SystemConfig.showToast("网络错误");
    }
}
