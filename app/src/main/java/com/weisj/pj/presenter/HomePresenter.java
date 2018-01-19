package com.weisj.pj.presenter;


import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.HomeBean;
import com.weisj.pj.manager.IHomeManager;
import com.weisj.pj.manager.impl.HomeManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.viewinterface.IHomeView;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class HomePresenter implements IOnManagerListener {
    private IHomeManager iHomeManager;
    private IHomeView iHomeView;
    private BaseViewState viewState;


    public HomePresenter(IHomeView iHomeView, BaseViewState viewState) {
        this.iHomeView = iHomeView;
        this.viewState = viewState;
        this.iHomeManager = new HomeManager();
    }

    public void getInitData(boolean b) {
        if (b) {
            viewState.showInitLoading();
        } else {
            viewState.showLoading();
        }
        iHomeManager.getHomeInfo(this);
//        iHomeManager.getHomeBanner(this);
    }


    @Override
    public void onSuccess(Object data, String url) {
        if (url.equals(Urls.homepage)) {
            HomeBean homeBean = (HomeBean) data;
            if (homeBean.getCode().equals("1")) {
                viewState.showLoadFinish();
                iHomeView.getData(homeBean);
            } else {
                viewState.showNoNetWork();
            }
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        if (url.equals(Urls.homepage)) {
            viewState.showNoNetWork();
        }
    }
}
