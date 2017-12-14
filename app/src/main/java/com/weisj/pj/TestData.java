package com.weisj.pj;

import com.weisj.pj.bean.GoodPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 2017/12/14.
 */

public class TestData {


    /**
     * 商品页面的用户评论列表
     */
    public static List<GoodPoint.DataBean> getGoodPoint_DataBean() {

        GoodPoint.DataBean dataBean1 = new GoodPoint.DataBean("ViVi", "好好看！");
        GoodPoint.DataBean dataBean2 = new GoodPoint.DataBean("GIing", "不好啊！");
        GoodPoint.DataBean dataBean3 = new GoodPoint.DataBean("IBdef", "好好好好好好好好好好好好好好好，好好好好好好！");

        List<GoodPoint.DataBean> list = new ArrayList<>();
        list.add(dataBean1);
        list.add(dataBean2);
        list.add(dataBean3);

        return list;

    }
}
