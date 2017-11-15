package com.weisj.pj.viewinterface;

import com.weisj.pj.bean.SpecialGoodsBean;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface ISpecialGoodsListView {

    int getOrderField();

    int getOrderType();

    String getAd_id();

    void getData(SpecialGoodsBean goodBean);

    void getInitData(SpecialGoodsBean goodBean);


}
