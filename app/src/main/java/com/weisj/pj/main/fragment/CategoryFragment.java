package com.weisj.pj.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemCategoryCommodityAdapter;
import com.weisj.pj.base.BaseFragment;
import com.weisj.pj.base.activity.SearchActivity;
import com.weisj.pj.bean.CategoryBean;
import com.weisj.pj.bean.CommodityShow;
import com.weisj.pj.bean.GoodBean;
import com.weisj.pj.presenter.CategoryPresenter;
import com.weisj.pj.viewinterface.ICategoryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 16/6/21.
 * 品类首页
 */
public class CategoryFragment extends BaseFragment implements AdapterView.OnItemClickListener, ICategoryView, View.OnClickListener {

    private CategoryPresenter presenter;
    private RecyclerView recyclerView;
    private ImageView imageView1;
    private TextView home_more;
    ItemCategoryCommodityAdapter adapter;


    static String goods_params = "";//商品类别 为空串是所有商品
    View headView;



    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, null);
        headView = mInflater.inflate(R.layout.fragment_category_head, null);


        rootView.isHintHeadBar(true);
        presenter = new CategoryPresenter(this, this);
        initView(view);
        presenter.getContentList(1, goods_params);
        return view;
    }

    private void initView(final View view) {


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        imageView1 = (ImageView) headView.findViewById(R.id.image_category1);
        home_more = (TextView) headView.findViewById(R.id.home_more);
        view.findViewById(R.id.root_head_search).setOnClickListener(this);

    }

    @Override
    public String setTitleStr() {
        return "品类";
    }

    @Override
    public void getRefreshData() {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (parent.getAdapter() instanceof ItemCategoryTextAdapter) {
//            ItemCategoryTextAdapter itemCategoryTextAdapter = (ItemCategoryTextAdapter) parent.getAdapter();
//            CategoryBean.DataEntity dataEntity = (CategoryBean.DataEntity) itemCategoryTextAdapter.getItem(position);
//            itemCategoryTextAdapter.setSelectId(dataEntity.getCategoryId());
//            presenter.getContentList(dataEntity.getCategoryId());
//            itemCategoryTextAdapter.notifyDataSetChanged();
//        } else if (parent.getAdapter() instanceof ItemCategoryGoodAdapter) {
//            ItemCategoryGoodAdapter itemCategoryGoodAdapter = (ItemCategoryGoodAdapter) parent.getAdapter();
//            CategoryBean.DataEntity dataEntity = (CategoryBean.DataEntity) itemCategoryGoodAdapter.getItem(position);
//            Intent intent = new Intent(this.getContext(), SearchListActivity.class);
//            intent.putExtra("categoryId", dataEntity.getCategoryId());
//            startActivity(intent);
//        }
    }


    @Override
    public void getContent(GoodBean goodBean) {
        adapter = new ItemCategoryCommodityAdapter(goodBean.getData());
        adapter.addHeaderView(headView);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void cateFail() {
//        failView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.root_head_search:
                intent = new Intent(this.getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
        }


    }
}
