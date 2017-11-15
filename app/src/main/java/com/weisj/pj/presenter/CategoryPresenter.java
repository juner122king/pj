package com.weisj.pj.presenter;

import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.CategoryBean;
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

    public void getTitleList() {
        viewState.showInitLoading();
        iCategoryManager.getCateTitle(this);
    }

    public void getContentList(int id) {
        viewState.showLoading();
        iCategoryManager.getCateContent(id, this);
    }


    @Override
    public void onSuccess(Object data, String url) {
        CategoryBean categoryBean = (CategoryBean) data;
        viewState.showLoadFinish();
        if (categoryBean.getCode().equals("1")) {
            if (url.equals(Urls.getcategorybytopfilter)) {
                iCategoryView.getTitle(categoryBean);
            } else {
                iCategoryView.getContent(categoryBean);
            }
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
