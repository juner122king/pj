package com.weisj.pj.base.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.okhttp.Request;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemMyCardAdapter;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.bean.CardBean;
import com.weisj.pj.bean.CardTypeBean;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.view.dialog.VipKTDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jun on 2018/2/2.
 */

public class MyCardActivity extends BaseActivity {

    RecyclerView recyclerView;

    ItemMyCardAdapter adapter;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_mycard, null);

        initView(view);

        return view;


    }

    private void initView(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    //我的会员卡
    private void getAllKindsOfCardCount() {

        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("page", "1");
        params.put("page_num", "10");

        OkHttpClientManager.postAsyn(Urls.getAllKindsOfCardCount, params, new OkHttpClientManager.ResultCallback<CardTypeBean>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(CardTypeBean response) {
                if (response != null) {
                    String code = response.getCode();
                    if (code.equals("1")) {
                        adapter = new ItemMyCardAdapter(response.getData());
                        recyclerView.setAdapter(adapter);


                    }
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        getAllKindsOfCardCount();

    }

    @Override
    public String setTitleStr() {
        return "会员卡";

    }

    @Override
    public void getRefreshData() {

    }


}


