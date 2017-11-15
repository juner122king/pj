package com.weisj.pj.manager;

import com.weisj.pj.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface ISearchHighListGoodManager {
    void getGoods(int order_field, int order_type, int page, int pageNum, IOnManagerListener onManagerListener);
}
