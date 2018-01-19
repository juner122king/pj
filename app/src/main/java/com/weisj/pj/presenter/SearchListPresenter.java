package com.weisj.pj.presenter;

import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.GoodBean;
import com.weisj.pj.bean.Region;
import com.weisj.pj.bean.SearchBrand;
import com.weisj.pj.manager.ISearchListGoodManager;
import com.weisj.pj.manager.impl.SearchListGoodManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.viewinterface.ISearchListView;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class SearchListPresenter implements IOnManagerListener {
    private ISearchListGoodManager searchListGoodManager;
    private ISearchListView iSearchListView;
    private BaseViewState viewState;
    private int page = 1;
    private int pageNum = 10;
    private int from;

    public SearchListPresenter(ISearchListView iSearchListView, BaseViewState viewState, int from) {
        this.iSearchListView = iSearchListView;
        this.viewState = viewState;
        searchListGoodManager = new SearchListGoodManager();
        this.from = from;
    }

    public SearchListPresenter(ISearchListView iSearchListView, BaseViewState viewState, String url, int from) {
        this.iSearchListView = iSearchListView;
        this.viewState = viewState;
        searchListGoodManager = new SearchListGoodManager(url);
        this.from = from;
    }

    public void getInitData() {
        viewState.showLoading();
        page = 1;
        searchListGoodManager.getGoods(iSearchListView.getGoodName(), iSearchListView.getCategoryId(), iSearchListView.getOrderField(), iSearchListView.getOrderType(), page, pageNum, iSearchListView.getBrandId(), iSearchListView.getDirectId(), from, this);
    }

    public void getData() {
        viewState.showLoading();
        page++;
        searchListGoodManager.getGoods(iSearchListView.getGoodName(), iSearchListView.getCategoryId(), iSearchListView.getOrderField(), iSearchListView.getOrderType(), page, pageNum, iSearchListView.getBrandId(), iSearchListView.getDirectId(), from, this);
    }

    public void getRegions() {
        searchListGoodManager.getallregions(this);
    }

    public void getbrandbydistrict(int district) {
        searchListGoodManager.getbrandbydistrict(district, this);
    }

    @Override
    public void onSuccess(Object data, String url) {
        if (url.equals(Urls.getallregions)) {
            iSearchListView.getRegions((Region) data);
        } else if (url.equals(Urls.getbrandbydistrict)) {
            iSearchListView.getBrandsByRegion((SearchBrand) data);
        } else {
            viewState.showLoadFinish();
            GoodBean goodBean = (GoodBean) data;
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

    }

    @Override
    public void onFail(Exception e, String url) {
        e.printStackTrace();
        viewState.showNoNetWork();
    }
}
