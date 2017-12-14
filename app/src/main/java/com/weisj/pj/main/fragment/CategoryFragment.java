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

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, null);
        rootView.isHintHeadBar(true);
//        presenter = new CategoryPresenter(this, this);
        initView(view);
//        presenter.getTitleList();
        return view;
    }

    private void initView(final View view) {
        CommodityShow commodityShow1 = new CommodityShow("https://img12.360buyimg.com/n7/jfs/t9163/217/1078094329/137867/d66b5174/59bb4a2aNd0d375bf.jpg", "标题1", 99);
        CommodityShow commodityShow2 = new CommodityShow("https://img12.360buyimg.com/n7/jfs/t9163/217/1078094329/137867/d66b5174/59bb4a2aNd0d375bf.jpg", "标题1", 99);
        CommodityShow commodityShow3 = new CommodityShow("https://img12.360buyimg.com/n7/jfs/t9163/217/1078094329/137867/d66b5174/59bb4a2aNd0d375bf.jpg", "标题1", 99);

        List<CommodityShow> list = new ArrayList<>();
        list.add(commodityShow1);
        list.add(commodityShow2);
        list.add(commodityShow3);

        adapter = new ItemCategoryCommodityAdapter(null);

        final View headView = mInflater.inflate(R.layout.fragment_category_head, null);
        adapter.addHeaderView(headView);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        imageView1 = (ImageView) headView.findViewById(R.id.image_category1);
        home_more = (TextView) headView.findViewById(R.id.home_more);
        view.findViewById(R.id.root_head_search).setOnClickListener(this);


        // 加载网络图片
        Glide.with(this).load("https://img11.360buyimg.com/n7/jfs/t4609/96/4691595833/60668/1ba1ced0/59140bc9N987f5b57.jpg").crossFade().into(imageView1);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        recyclerView.setAdapter(adapter);


    }

    @Override
    public String setTitleStr() {
        return "分类";
    }

    @Override
    public void getRefreshData() {
//        presenter.getTitleList();
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
    public void getTitle(CategoryBean categoryTitleBean) {
//        listView.setAdapter(new ItemCategoryTextAdapter(this.getContext(), categoryTitleBean.getData()));
//        listView.setOnItemClickListener(this);
//        if (categoryTitleBean.getData() != null && categoryTitleBean.getData().size() > 0) {
//            presenter.getContentList(categoryTitleBean.getData().get(0).getCategoryId());
//        }
    }

    @Override
    public void getContent(CategoryBean categoryContentBean) {
//        failView.setVisibility(View.GONE);
//        gridView.setAdapter(new ItemCategoryGoodAdapter(this.getContext(), categoryContentBean.getData()));
//        gridView.setOnItemClickListener(this);

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
