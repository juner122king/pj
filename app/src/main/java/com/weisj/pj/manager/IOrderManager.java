package com.weisj.pj.manager;

import com.weisj.pj.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface IOrderManager {

    void getOrderData(int state, int page, int pageNum, IOnManagerListener listener);

    void getOrderData1and2(int page, int pageNum, IOnManagerListener listener);

    void getOrderRecordData(IOnManagerListener listener);

    void getMyRecommend(IOnManagerListener listener);

    void deleteOrder(String id, IOnManagerListener listener);

    void cartlist(IOnManagerListener listener);
}
