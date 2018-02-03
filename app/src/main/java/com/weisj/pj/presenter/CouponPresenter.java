package com.weisj.pj.presenter;

import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.ActiBean;
import com.weisj.pj.bean.CouponBean;
import com.weisj.pj.bean.HomeCouponbean;
import com.weisj.pj.manager.ICouponManager;
import com.weisj.pj.manager.impl.CouponManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.viewinterface.ICouponView;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class CouponPresenter implements IOnManagerListener {
    private ICouponManager couponManager;
    private BaseViewState viewState;
    private ICouponView iCouponView;
    private int page = 1;
    private int pageNum = 10;
    private int categoryId = 0;

    public CouponPresenter(ICouponView iCouponView, BaseViewState viewState) {
        this.iCouponView = iCouponView;
        this.viewState = viewState;
        couponManager = new CouponManager();
    }

    public void getData(String pronvin, boolean b) {
        if (b) {
            viewState.showInitLoading();
        } else {
//            viewState.showLoading();
        }
        couponManager.getHomeCouponData(pronvin, this);
        couponManager.getActiData(this);
    }

    public void getInitData(int categoryId) {
//        viewState.showLoading();
        page = 1;
        this.categoryId = categoryId;
        couponManager.getCouponData(categoryId, page, pageNum, this);
    }

    public void getData() {
//        viewState.showLoading();
        page++;
        couponManager.getCouponData(categoryId, page, pageNum, this);
    }

    @Override
    public void onSuccess(Object data, String url) {
        if (url.equals(Urls.couponpage)) {
            HomeCouponbean homeCouponbean = (HomeCouponbean) data;
            if (homeCouponbean.getCode().equals("1")) {
                viewState.showLoadFinish();
                iCouponView.getInitData((HomeCouponbean) data);
            } else {
                viewState.showNoData();
            }
        } else if (url.equals(Urls.getAllGuessActivitys)) {
            ActiBean actiBean = (ActiBean) data;
            if (actiBean.getCode().equals("1")) {
                iCouponView.getActiData(actiBean);
            } else {
                iCouponView.getActiFail();
            }
        } else {
            viewState.showLoadFinish();
            String page = url.split("&page=")[1];
            if (page.equals("1")) {
                iCouponView.getInitData((CouponBean) data);
            } else {
                iCouponView.getData((CouponBean) data);
            }
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showNoNetWork();
    }
}
