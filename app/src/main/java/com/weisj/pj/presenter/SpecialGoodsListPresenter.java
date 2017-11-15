package com.weisj.pj.presenter;

import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.SpecialGoodsBean;
import com.weisj.pj.manager.ISpecialGoodsListGoodManager;
import com.weisj.pj.manager.impl.SpecialGoodManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.viewinterface.ISpecialGoodsListView;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class SpecialGoodsListPresenter implements IOnManagerListener {
    private ISpecialGoodsListGoodManager searchListGoodManager;
    private ISpecialGoodsListView iSearchListView;
    private BaseViewState viewState;
    private int page = 1;
    private int pageNum = 10;

    public SpecialGoodsListPresenter(ISpecialGoodsListView iSearchListView, BaseViewState viewState) {
        this.iSearchListView = iSearchListView;
        this.viewState = viewState;
        searchListGoodManager = new SpecialGoodManager();
    }

    public void getInitData() {
        viewState.showLoading();
        page = 1;
        searchListGoodManager.getGoods(iSearchListView.getAd_id(),iSearchListView.getOrderField(), iSearchListView.getOrderType(), page, pageNum, this);
    }

    public void getData() {
        viewState.showLoading();
        page++;
        searchListGoodManager.getGoods(iSearchListView.getAd_id(),iSearchListView.getOrderField(), iSearchListView.getOrderType(), page, pageNum, this);
    }


    @Override
    public void onSuccess(Object data, String url) {
        viewState.showLoadFinish();
        SpecialGoodsBean goodBean = (SpecialGoodsBean) data;
        String page = url.split("&page=")[1];
        if (goodBean.getCode().equals("1")) {
            if (page.equals("1")) {
                iSearchListView.getInitData(goodBean);
            } else {
                iSearchListView.getData(goodBean);
            }
        } else {
            if (page.equals("1")) {
                viewState.showNoData();
            }
        }

    }

    @Override
    public void onFail(Exception e, String url) {
        SystemConfig.showToast("网络错误");
        viewState.showNoNetWork();
    }
}
