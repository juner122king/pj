package com.weisj.pj.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.weisj.pj.R;
import com.weisj.pj.main.fragment.OrderFragment;

/**
 * Created by jun on 2017/12/15.
 */

public class OrderActivity extends FragmentActivity {

    // Fragment管理器
    private FragmentManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //获取Fragment管理器
        manager = getSupportFragmentManager();
        OrderFragment fragment;
        FragmentTransaction transaction;

        // 新建一个Fragment
        fragment = new OrderFragment();

        // 开启一个新事务
        transaction = manager.beginTransaction();
        // 使用add方法添加Fragment，第一个参数是要把Fragment添加到的布局Id
        // 第二个就是要添加的Fragment
        transaction.add(R.id.fragments, fragment);
        // 提交事务，否则添加就没成功
        transaction.commit();


    }
}
