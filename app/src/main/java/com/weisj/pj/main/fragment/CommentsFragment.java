package com.weisj.pj.main.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.Request;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemCarListAdapter;
import com.weisj.pj.adapter.ItemCommentAdapter;
import com.weisj.pj.base.BaseFragment;
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
 * Created by jun on 2018/1/22.
 */

public class CommentsFragment extends BaseFragment {

    View view;

    RecyclerView recyclerView;
    ItemCommentAdapter adapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_comments, null);

        initView();
        return view;
    }


    private void initView() {


        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


    }

    @Override
    public void onResume() {
        super.onResume();
        getdata();

    }

    private void getdata() {
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
                if (code.equals("0")) {
                    showNotData();


                } else {
                    //弹出成功窗口

                    adapter = new ItemCommentAdapter(response.getData());
                    recyclerView.setAdapter(adapter);


                }
            }
        });
    }


    private void showNotData() {


    }

    @NonNull
    @Override
    public String setTitleStr() {
        return "晒图";
    }

    @Override
    public void getRefreshData() {

    }
}
