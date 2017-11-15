package com.weisj.pj.manager;

import com.weisj.pj.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface ICouponManager {
    void getHomeCouponData(String pronvin, IOnManagerListener listener);

    void getCouponData(int categoryId,int page,int pageNum,IOnManagerListener listener);

    void getActiData(IOnManagerListener listener);
}
