package com.weisj.pj.viewinterface;

import com.weisj.pj.bean.GoodBean;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface ISearchHighListView {

    int getOrderField();

    int getOrderType();

    void getData(GoodBean goodBean);

    void getInitData(GoodBean goodBean);


}
