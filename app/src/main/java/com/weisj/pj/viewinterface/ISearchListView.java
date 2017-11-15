package com.weisj.pj.viewinterface;

import com.weisj.pj.bean.GoodBean;
import com.weisj.pj.bean.Region;
import com.weisj.pj.bean.SearchBrand;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface ISearchListView {

    int getCategoryId();

    int getOrderField();

    int getOrderType();

    int getBrandId();

    int getDirectId();

    void getData(GoodBean goodBean);

    void getInitData(GoodBean goodBean);

    void getRegions(Region region);

    void getBrandsByRegion(SearchBrand searchBrand);

    String getGoodName();


}
