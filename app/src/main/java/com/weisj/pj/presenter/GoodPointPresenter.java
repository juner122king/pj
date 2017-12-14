package com.weisj.pj.presenter;

import com.weisj.pj.TestData;
import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.GoodPoint;
import com.weisj.pj.manager.IGoodDetailmanager;
import com.weisj.pj.manager.impl.GoodDetailManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.viewinterface.IGoodPoint;

/**
 * Created by Administrator on 2016/11/2 0002.
 */

public class GoodPointPresenter implements IOnManagerListener {
    private BaseViewState viewState;
    private IGoodPoint goodPoint;
    private IGoodDetailmanager goodDetailmanager;

    public GoodPointPresenter(BaseViewState viewState, IGoodPoint goodPoint) {
        this.viewState = viewState;
        this.goodPoint = goodPoint;
        goodDetailmanager = new GoodDetailManager();
    }

    public void getInitData() {
        viewState.showInitLoading();
        goodDetailmanager.getGoodPointData(goodPoint.getGoodId(), 1, this);
    }

    public void getData(int number) {
        viewState.showLoading();
        goodDetailmanager.getGoodPointData(goodPoint.getGoodId(), number, this);
    }

    @Override
    public void onSuccess(Object data, String url) {
        viewState.showLoadFinish();
        String[] str = url.split("=page=");
        try {
            GoodPoint goodPoint = (GoodPoint) data;
            goodPoint.setData(TestData.getGoodPoint_DataBean());
            this.goodPoint.getInitData(goodPoint);
//            if (str[1].equals("1")) {
//                if (goodPoint.getCode().equals("1") && goodPoint.getData() != null && goodPoint.getData().size() > 0) {
//                    this.goodPoint.getInitData(goodPoint);
//                } else {
//                    viewState.showNoData();
//                    this.goodPoint.getFail();
//                }
//            } else {
//                if (goodPoint.getCode().equals("1")) {
//                    this.goodPoint.getData((GoodPoint) data);
//                } else {
//                    this.goodPoint.getFail();
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showNoNetWork();
    }
}
