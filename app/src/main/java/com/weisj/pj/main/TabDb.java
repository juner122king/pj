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
    public static String[] tabs = {"首页", "品类", "首饰盒", "晒图", "我的"};
    public static List<Bitmap> tabImage = new ArrayList<>();
    public static List<Bitmap> selectImage = new ArrayList<>();

    public static String[] getTabsTxt() {
        return tabs;
    }

    public static void setTabsTxt(String[] tbas) {
        tabs = tbas;
    }

    public static int[] getTabsImg() {
        int[] ids = {R.mipmap.icon_shouye, R.mipmap.icon_pinlei, R.mipmap.icon_shoushi, R.mipmap.icon_shaitu, R.mipmap.icon_gerenzhongxin};
        return ids;
    }

    public static int[] getTabsImgLight() {
        int[] ids = {R.mipmap.icon_shouye_dianji, R.mipmap.icon_pinlei_dianji, R.mipmap.icon_shoushi_dianji, R.mipmap.icon_shaitu_dianji, R.mipmap.icon_gerenzhongxin_dianji};
        return ids;
    }

    public static Class[] getFragments() {
        Class[] clz = {HomeFragment.class, CategoryFragment.class, OrderFragment.class, ActiFragment.class, MeFragment.class};
        return clz;
    }


}
