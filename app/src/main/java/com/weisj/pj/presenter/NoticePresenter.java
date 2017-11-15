package com.weisj.pj.presenter;

import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.NoticeBean;
import com.weisj.pj.manager.INoticeManager;
import com.weisj.pj.manager.impl.NoticeManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.viewinterface.INoticeView;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class NoticePresenter implements IOnManagerListener {
    private INoticeManager noticeManager;
    private BaseViewState viewState;
    private INoticeView noticeView;
    private int page = 1;
    private int pageNum = 10;

    public NoticePresenter(INoticeView noticeView, BaseViewState viewState) {
        this.noticeView = noticeView;
        this.viewState = viewState;
        noticeManager = new NoticeManager();
    }

    public void getInitData() {
        viewState.showLoading();
        page = 1;
        noticeManager.getData(page, pageNum, 1, this);
    }

    public void getData() {
        page++;
        noticeManager.getData(page, pageNum, 1, this);
    }

    @Override
    public void onSuccess(Object data, String url) {
        viewState.showLoadFinish();
        String page = url.split("&page=")[1];
        NoticeBean noticeBean = (NoticeBean) data;
        if (page.equals("1")) {
            if (noticeBean.getCode().equals("1")) {
                noticeView.getInitData(noticeBean);
            } else {
                viewState.showNoData();
            }
        } else {
            noticeView.getData(noticeBean);
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showNoNetWork();
    }
}
