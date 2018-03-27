package com.weisj.pj.base.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemGoodDetailAdapter;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.bean.GoodDetail;
import com.weisj.pj.bean.GoodDetailImageBean;
import com.weisj.pj.presenter.GoodDetailPresenter;
import com.weisj.pj.utils.CommenString;
import com.weisj.pj.view.photocheck.WebImageCheckActivity;
import com.weisj.pj.viewinterface.IGoodDetailView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public class GoodImageDetailActivity extends BaseActivity implements IGoodDetailView, AdapterView.OnItemClickListener, View.OnClickListener {
    private ListView listView;
    private ItemGoodDetailAdapter adapter;
    private GoodDetailPresenter presenter;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_good_image_detail, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        presenter = new GoodDetailPresenter(this, this);
        presenter.getImageData();

        view.findViewById(R.id.tv_b1).setOnClickListener(this);
        view.findViewById(R.id.tv_b2).setOnClickListener(this);
        view.findViewById(R.id.tv_b3).setOnClickListener(this);
        view.findViewById(R.id.tv_b4).setOnClickListener(this);
    }

    @Override
    public String setTitleStr() {
        return "图文详情";
    }

    @Override
    public void getRefreshData() {
        presenter.getImageData();
    }

    @Override
    public int getGoodId() {
        return getIntent().getIntExtra("goodId", 1);
    }

    @Override
    public void getData(GoodDetail goodDetail) {

    }

    @Override
    public void getImageData(GoodDetailImageBean goodDetailImageBean) {
        list.clear();
        list.addAll(goodDetailImageBean.getData());
        adapter = new ItemGoodDetailAdapter(this, list);
        listView.setAdapter(adapter);
    }

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public int getActivityId() {
        return 0;
    }

    @Override
    public void showInfo(String tag) {
        Toast.makeText(this, tag, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toVipActivity() {

        startActivity(new Intent(this, VipActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, WebImageCheckActivity.class);
        intent.putExtra("image_index", position);
        intent.putStringArrayListExtra(CommenString.url, list);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_b1:

                Dialog alertDialog = new AlertDialog.Builder(this).
                        setTitle("联系客服").
                        setMessage("400-099-828").
                        create();
                alertDialog.show();

                break;

            case R.id.tv_b2:
                Toast.makeText(GoodImageDetailActivity.this, "暂不开放", Toast.LENGTH_SHORT).show();

                break;
            case R.id.tv_b3:
//                startActivity(new Intent(this, OrderActivity.class));


                break;
            case R.id.tv_b4:
                presenter.isBuyCard();
                break;
        }
    }
}
