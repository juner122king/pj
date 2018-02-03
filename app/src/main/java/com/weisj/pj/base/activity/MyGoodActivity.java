package com.weisj.pj.base.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.squareup.okhttp.Request;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemMyGoodAdapter;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.bean.OrderBean;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.presenter.ShowAddressPresenter;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.Urls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jun on 2018/2/3.
 */

public class MyGoodActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ItemMyGoodAdapter adapter;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_consignee_address, null);
        initView(view);
        return view;
    }


    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        getOrderData();
    }

    public void getOrderData() {

        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("order_state", "3");
        params.put("page", "1");
        params.put("page_num", "10");

        OkHttpClientManager.postAsyn(Urls.myorders, params, new OkHttpClientManager.ResultCallback<OrderBean>() {
            @Override
            public void onError(Request request, Exception e) {
            }
            @Override
            public void onResponse(OrderBean response) {
                if (response != null) {

                    if (response.getData().size() > 0) {


                        adapter = new ItemMyGoodAdapter(response.getData().get(0).getOrder_info_domain_list());
                        recyclerView.setAdapter(adapter);

                        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                                finish();


                            }
                        });

                    }
                }
            }
        });
    }


    @Override
    public String setTitleStr() {
        return "我的商品";
    }

    @Override
    public void getRefreshData() {

    }
}
