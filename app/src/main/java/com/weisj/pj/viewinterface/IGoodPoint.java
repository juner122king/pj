package com.weisj.pj.viewinterface;

import com.weisj.pj.bean.GoodPoint;

/**
 * Created by Administrator on 2016/11/2 0002.
 */

public interface IGoodPoint {
    int getGoodId();

    void getInitData(GoodPoint goodPoint);

    void getData(GoodPoint goodPoint);

    void getFail();
}
