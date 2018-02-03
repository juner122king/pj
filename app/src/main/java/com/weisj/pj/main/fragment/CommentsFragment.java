package com.weisj.pj.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemCarListAdapter;
import com.weisj.pj.adapter.ItemCommentAdapter;
import com.weisj.pj.base.BaseFragment;
import com.weisj.pj.base.activity.MyShowActivity;
import com.weisj.pj.base.activity.VipActivity;
import com.weisj.pj.bean.CardBean;
import com.weisj.pj.bean.Comment;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.view.dialog.VipKTDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * 晒图
 * Created by jun on 2018/1/22.
 */

public class CommentsFragment extends BaseFragment implements View.OnClickListener {

    View view;

    TextView tv1, tv2, tv_notdata;
    RecyclerView recyclerView;
    ItemCommentAdapter adapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_comments, null);

        initView();
        return view;
    }


    private void initView() {

        rootView.isHintHeadBar(true);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv2);

        tv_notdata = (TextView) view.findViewById(R.id.tv_notdata);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        view.findViewById(R.id.tv_r).setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        getdatahot();

    }

    private void getdatahot() {
        Map<String, String> params = new HashMap<>();


        params.put("page", "1");
        params.put("rows", "10");


        OkHttpClientManager.postAsyn(Urls.getNewComments, params, new OkHttpClientManager.ResultCallback<Comment>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(Comment response) {

                String code = response.getCode();
                if (code.equals("0") || response.getData().size() == 0) {
                    showNotData();

                } else {
                    //弹出成功窗口
                    showData();
                    adapter = new ItemCommentAdapter(response.getData());
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }


    private void getdatamid() {
        Map<String, String> params = new HashMap<>();


        params.put("page", "1");
        params.put("rows", "10");
        params.put("member_id", PersonMessagePreferencesUtils.getUid());


        OkHttpClientManager.postAsyn(Urls.getMyComments, params, new OkHttpClientManager.ResultCallback<Comment>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(Comment response) {

                String code = response.getCode();
                if (code.equals("0") || response.getData().size() == 0) {
                    showNotData();

                } else {
                    //弹出成功窗口
                    showData();
                    adapter = new ItemCommentAdapter(response.getData());
                    recyclerView.setAdapter(adapter);


                }
            }
        });
    }


    private void showNotData() {
        recyclerView.setVisibility(View.GONE);
        tv_notdata.setVisibility(View.VISIBLE);

    }

    private void showData() {
        recyclerView.setVisibility(View.VISIBLE);
        tv_notdata.setVisibility(View.GONE);

    }

    @NonNull
    @Override
    public String setTitleStr() {
        return "晒图";
    }

    @Override
    public void getRefreshData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.tv1:
                getdatahot();
                tv1.setTextColor(getResources().getColor(R.color.colorHome));
                tv2.setTextColor(getResources().getColor(R.color.color1b1b1b));

                break;

            case R.id.tv2:
                getdatamid();
                tv2.setTextColor(getResources().getColor(R.color.colorHome));
                tv1.setTextColor(getResources().getColor(R.color.color1b1b1b));
                break;

            case R.id.tv_r:
                startActivity(new Intent(getContext(), MyShowActivity.class));

                break;
        }


    }
}
