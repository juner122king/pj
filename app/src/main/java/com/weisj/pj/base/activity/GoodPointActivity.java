package com.weisj.pj.base.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemGoodDetailCommentAdapter;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.bean.GoodPoint;
import com.weisj.pj.presenter.GoodPointPresenter;
import com.weisj.pj.view.RoundedCornersTransformation;
import com.weisj.pj.viewinterface.IGoodPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2 0002.
 */

public class GoodPointActivity extends BaseActivity implements IGoodPoint {
    private RecyclerView recyclerView;
    private List<GoodPoint.DataBean> list = new ArrayList<>();
    private ItemGoodDetailCommentAdapter goodPointAdapter;
    private GoodPointPresenter pointPresenter;
    private ImageView imageView;
    private int index = 1;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_good_point, null);
        initView(view);
        pointPresenter = new GoodPointPresenter(this, this);
        pointPresenter.getInitData();
        return view;
    }


    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        imageView = (ImageView) view.findViewById(R.id.iv);
        Glide.with(this)
                .load("http://img1.imgtn.bdimg.com/it/u=1167088769,847502684&fm=200&gp=0.jpg")
                .bitmapTransform(new RoundedCornersTransformation(this, 10))
                .crossFade()
                .into(imageView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public String setTitleStr() {
        return "商品评价";
    }

    @Override
    public void getRefreshData() {

    }

    @Override
    public int getGoodId() {
        return getIntent().getIntExtra("good_id", 0);
    }

    @Override
    public void getInitData(GoodPoint goodPoint) {
        list.clear();
        list.addAll(goodPoint.getData());
        if (goodPointAdapter == null) {
            goodPointAdapter = new ItemGoodDetailCommentAdapter(list);
            recyclerView.setAdapter(goodPointAdapter);
        }
        goodPointAdapter.notifyDataSetChanged();
    }

    @Override
    public void getData(GoodPoint goodPoint) {
        list.addAll(goodPoint.getData());
        goodPointAdapter.notifyDataSetChanged();
    }

    @Override
    public void getFail() {
    }


}
