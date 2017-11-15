package com.weisj.pj.main;

import android.graphics.Bitmap;

import com.weisj.pj.R;
import com.weisj.pj.main.fragment.ActiFragment;
import com.weisj.pj.main.fragment.CategoryFragment;
import com.weisj.pj.main.fragment.HomeFragment;
import com.weisj.pj.main.fragment.MeFragment;
import com.weisj.pj.main.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 16/6/21.
 */
public class TabDb {
    public static boolean isNet = false;
    public static String[] tabs = {"首页", "品类", "活动", "订单", "我的"};
    public static List<Bitmap> tabImage = new ArrayList<>();
    public static List<Bitmap> selectImage = new ArrayList<>();

    public static String[] getTabsTxt() {
        return tabs;
    }

    public static void setTabsTxt(String[] tbas) {
        tabs = tbas;
    }

    public static int[] getTabsImg() {
        int[] ids = {R.mipmap.icon_home_normal, R.mipmap.icon_category_normal, R.mipmap.icon_acti_normal, R.mipmap.icon_order_normal, R.mipmap.icon_me_normal};
        return ids;
    }

    public static int[] getTabsImgLight() {
        int[] ids = {R.mipmap.icon_home, R.mipmap.icon_category, R.mipmap.icon_acti_select, R.mipmap.icon_order, R.mipmap.icon_me};
        return ids;
    }

    public static Class[] getFragments() {
        Class[] clz = {HomeFragment.class, CategoryFragment.class, ActiFragment.class, OrderFragment.class, MeFragment.class};
        return clz;
    }


}
