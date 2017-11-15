package com.weisj.pj.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemNoticeAdapter;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.bean.NoticeBean;
import com.weisj.pj.presenter.NoticePresenter;
import com.weisj.pj.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.pj.viewinterface.INoticeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/30 0030.
 */
public class SystemNoticeActivity extends BaseActivity implements INoticeView, AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener, AdapterView.OnItemClickListener {
    private List<NoticeBean.DataEntity> list = new ArrayList<>();
    private AbPullToRefreshView refreshView;
    private ListView listView;
    private ItemNoticeAdapter adapter;
    private NoticePresenter presenter;

    private void initView(View view) {
        refreshView = (AbPullToRefreshView) view.findViewById(R.id.refresh_view);
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        presenter = new NoticePresenter(this, this);
        presenter.getInitData();
        refreshView.setOnFooterLoadListener(this);
        refreshView.setOnHeaderRefreshListener(this);
    }


    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_notice, null);
        initView(view);
        return view;
    }

    @Override
    public String setTitleStr() {
        return "公告";
    }

    @Override
    public void getRefreshData() {
        presenter.getInitData();
    }

    @Override
    public void getData(NoticeBean noticeBean) {
        list.addAll(noticeBean.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getInitData(NoticeBean noticeBean) {
        list.clear();
        list.addAll(noticeBean.getData());
        if (adapter == null) {
            adapter = new ItemNoticeAdapter(this, list);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        refreshView.onHeaderRefreshFinish();
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getInitData();
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        presenter.getData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NoticeBean.DataEntity dataEntity = list.get(position);
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("web_title", dataEntity.getTitle());
        intent.putExtra("url", dataEntity.getWebsibe());
        this.startActivity(intent);
    }
}
