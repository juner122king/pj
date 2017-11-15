package com.weisj.pj.manager;

import com.weisj.pj.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface ISearchListGoodManager {
    void getGoods(String goodName, int category_id, int order_field, int order_type, int page, int pageNum, int brand_id, int district_id, int from, IOnManagerListener onManagerListener);

    void getallregions(IOnManagerListener onManagerListener);

    void getbrandbydistrict(int district_id, IOnManagerListener onManagerListener);

}
