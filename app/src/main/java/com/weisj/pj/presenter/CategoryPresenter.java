package com.weisj.pj.presenter;

import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.GoodBean;
import com.weisj.pj.manager.ICategoryManager;
import com.weisj.pj.manager.impl.CategoryManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.viewinterface.ICategoryView;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class CategoryPresenter implements IOnManagerListener {

    private ICategoryManager iCategoryManager;

    private ICategoryView iCategoryView;

    private BaseViewState viewState;

    public CategoryPresenter(ICategoryView iCategoryView, BaseViewState viewState) {
        this.iCategoryView = iCategoryView;
        this.iCategoryManager = new CategoryManager();
        this.viewState = viewState;
    }

    public void getContentList(int page, String goods_params) {
        viewState.showLoading();
        iCategoryManager.getCateContent(String.valueOf(page), goods_params, this);
    }


    @Override
    public void onSuccess(Object data, String url) {
        GoodBean bean = (GoodBean) data;
        viewState.showLoadFinish();
        if (bean.getCode().equals("1")) {
            iCategoryView.getContent(bean);
        } else {
            if (url.equals(Urls.getcategorybytopfilter)) {
                viewState.showNoData();
            } else {
                iCategoryView.cateFail();
            }
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        if (url.equals(Urls.getcategorybytopfilter)) {
            viewState.showNoNetWork();
        } else {
            viewState.showLoadFinish();
            iCategoryView.cateFail();
        }
    }
}
