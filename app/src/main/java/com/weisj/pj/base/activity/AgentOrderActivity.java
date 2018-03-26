package com.weisj.pj.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemAgentOrderAdapter;
import com.weisj.pj.adapter.ItemCommentAdapter;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.bean.AgentOrder;
import com.weisj.pj.bean.Comment;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.Urls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jun on 2018/2/7.
 */

public class AgentOrderActivity extends BaseActivity implements View.OnClickListener {


    TextView tv1, tv2, tv3, tv_notdata;
    RecyclerView recyclerView;
    String agent_id;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_agentorder, null);

        initView(view);

        return view;
    }

    @Override
    protected void onResume() {

        super.onResume();
        getdata("3");
    }

    private void initView(final View view) {
        agent_id = getIntent().getStringExtra("agent_id");

        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv2);
        tv3 = (TextView) view.findViewById(R.id.tv3);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tv_notdata = (TextView) view.findViewById(R.id.tv_notdata);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
    }

    @Override
    public String setTitleStr() {
        return "代理订单";
    }

    @Override
    public void getRefreshData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.tv1:
                getdata("4");
                tv1.setTextColor(getResources().getColor(R.color.colorHome));
                tv2.setTextColor(getResources().getColor(R.color.color1b1b1b));
                tv3.setTextColor(getResources().getColor(R.color.color1b1b1b));

                break;

            case R.id.tv2:
                getdata("3");
                tv2.setTextColor(getResources().getColor(R.color.colorHome));
                tv1.setTextColor(getResources().getColor(R.color.color1b1b1b));
                tv3.setTextColor(getResources().getColor(R.color.color1b1b1b));
                break;

            case R.id.tv3:
                getdata("5");
                tv3.setTextColor(getResources().getColor(R.color.colorHome));
                tv1.setTextColor(getResources().getColor(R.color.color1b1b1b));
                tv2.setTextColor(getResources().getColor(R.color.color1b1b1b));
                break;
        }


    }


    private void showNotData() {
        recyclerView.setVisibility(View.GONE);
        tv_notdata.setVisibility(View.VISIBLE);

    }

    private void showData(List<AgentOrder.DataBean> list) {
        recyclerView.setVisibility(View.VISIBLE);
        tv_notdata.setVisibility(View.GONE);


    }

    private void getdata(String order_state) {
        Map<String, String> params = new HashMap<>();
        params.put("agent_id", agent_id);
        params.put("order_state", order_state);


        OkHttpClientManager.postAsyn(Urls.getCardOrdersByAgentId, params, new OkHttpClientManager.ResultCallback<AgentOrder>() {
            @Override
            public void onError(Request request, Exception e) {
                showNotData();
            }


            @Override
            public void onResponse(AgentOrder response) {

                String code = response.getCode();
                if (code.equals("0") || response.getData().size() == 0) {
                    showNotData();

                } else {
                    //弹出成功窗口
                    showData(response.getData());
                    ItemAgentOrderAdapter adapter = new ItemAgentOrderAdapter(response.getData());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
