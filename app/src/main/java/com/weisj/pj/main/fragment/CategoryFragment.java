package com.weisj.pj.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemCategoryCommodityAdapter;
import com.weisj.pj.base.BaseFragment;
import com.weisj.pj.bean.GoodBean;
import com.weisj.pj.bean.GoodsParams;
import com.weisj.pj.presenter.CategoryPresenter;
import com.weisj.pj.view.MyRadioGroup;
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
    private TextView home_more;
    ItemCategoryCommodityAdapter adapter;


    View headView;

    List data = new ArrayList();


    //商品类别 为空串是所有商品
    GoodsParams goodsParams = new GoodsParams();


    String propertyCode_top;
    String propertyCode2;
    String propertyCode3;
    String propertyCode4;
    String propertyCode5;


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, null);
        headView = mInflater.inflate(R.layout.fragment_category_head, null);


        rootView.isHintHeadBar(true);
        presenter = new CategoryPresenter(this, this);
        initView(view);


        presenter.getContentList(1, "");
        return view;
    }


    public void getGoodList() {

        Gson gson = new Gson();

        List<String> propertyCodeList = new ArrayList();


        if (null != propertyCode_top)
            propertyCodeList.add(propertyCode_top);

        if (null != propertyCode2)
            propertyCodeList.add(propertyCode2);

        if (null != propertyCode3)

            propertyCodeList.add(propertyCode3);
        if (null != propertyCode4)

            propertyCodeList.add(propertyCode4);
        if (null != propertyCode5)
            propertyCodeList.add(propertyCode5);


        goodsParams.setPropertyCodeList(propertyCodeList);


        String goods_params = gson.toJson(goodsParams);

        presenter.getContentList(1, goods_params);
    }

    private void initView(final View view) {


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new ItemCategoryCommodityAdapter(data);
        adapter.addHeaderView(headView);
        recyclerView.setAdapter(adapter);

        home_more = (TextView) headView.findViewById(R.id.home_more);
        view.findViewById(R.id.root_head_search).setOnClickListener(this);
        MyRadioGroup rg1 = (MyRadioGroup) headView.findViewById(R.id.rg1);
        final RadioGroup rgt = (RadioGroup) headView.findViewById(R.id.rgt);
        RadioGroup rg3 = (RadioGroup) headView.findViewById(R.id.rg3);
        RadioGroup rg4 = (RadioGroup) headView.findViewById(R.id.rg4);
        RadioButton rgt1 = (RadioButton) headView.findViewById(R.id.rgt_rb1);
        RadioButton rgt2 = (RadioButton) headView.findViewById(R.id.rgt_rb2);
        RadioButton rgt3 = (RadioButton) headView.findViewById(R.id.rgt_rb3);
        RadioButton rgt4 = (RadioButton) headView.findViewById(R.id.rgt_rb4);

        View.OnTouchListener radioButtonOnTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (((RadioButton) v).isChecked()) {
                    rgt.clearCheck();

                    propertyCode_top = null;
                    getGoodList();
                    return true;
                }
                return false;
            }
        };

        rgt1.setOnTouchListener(radioButtonOnTouchListener);
        rgt2.setOnTouchListener(radioButtonOnTouchListener);
        rgt3.setOnTouchListener(radioButtonOnTouchListener);
        rgt4.setOnTouchListener(radioButtonOnTouchListener);


        rg1.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rg1_rb1:
                        goodsParams.setCategoryCode("jz");
                        break;
                    case R.id.rg1_rb2:
                        goodsParams.setCategoryCode("dz");

                        break;
                    case R.id.rg1_rb3:
                        goodsParams.setCategoryCode("xl");
                        break;
                    case R.id.rg1_rb4:
                        goodsParams.setCategoryCode("sz");
                        break;
                    case R.id.rg1_rb5:
                        goodsParams.setCategoryCode("erh");
                        break;
                    case R.id.rg1_rb6:
                        goodsParams.setCategoryCode("xz");
                        break;
                    case R.id.rg1_rb7:
                        goodsParams.setCategoryCode("weij");
                        break;
                    case R.id.rg1_rb8:
                        goodsParams.setCategoryCode("maoz");
                        break;
                    case R.id.rg1_rb9:
                        goodsParams.setCategoryCode("sb");
                        break;
                    case R.id.rg1_rb10:
                        goodsParams.setCategoryCode("tyj");
                        break;
                    case R.id.rg1_rb11:
                        goodsParams.setCategoryCode(null);
                        break;
                }
                getGoodList();
            }
        });

        rgt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rgt_rb1:

                        propertyCode_top = "cj_wy";
                        break;
                    case R.id.rgt_rb2:
                        propertyCode_top = "cj_yw";
                        break;
                    case R.id.rgt_rb3:
                        propertyCode_top = "cj_xx";
                        break;
                    case R.id.rgt_rb4:
                        propertyCode_top = "cj_jh";
                        break;

                }
                getGoodList();

            }
        });


        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View checkView = headView.findViewById(checkedId);
                if (!checkView.isPressed()) {
                    return;
                }

                switch (checkedId) {
                    case R.id.rg3_rb1:
                        propertyCode3 = "cz_bs";

                        break;
                    case R.id.rg3_rb2:
                        propertyCode3 = "cz_kj";
                        break;
                    case R.id.rg3_rb3:
                        propertyCode3 = "cz_bj";
                        break;
                    case R.id.rg3_rb4:
                        propertyCode3 = null;
                        break;
                }
                getGoodList();

            }
        });
        rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View checkView = headView.findViewById(checkedId);
                if (!checkView.isPressed()) {
                    return;
                }

                switch (checkedId) {
                    case R.id.rg4_rb1:
                        propertyCode4 = "jw_499";

                        break;
                    case R.id.rg4_rb2:
                        propertyCode4 = "jw_1199";
                        break;
                    case R.id.rg4_rb3:
                        propertyCode4 = "jw_2099";
                        break;
                    case R.id.rg4_rb4:
                        propertyCode4 = "jw_2100up";
                        break;
                    case R.id.rg4_rb5:
                        propertyCode4 = null;
                        break;
                }
                getGoodList();
            }
        });

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

        data = goodBean.getData();
        adapter.replaceData(data);


    }

    @Override
    public void cateFail() {
    }

    @Override
    public void onClick(View v) {
        Intent intent;

//        switch (v.getId()) {
//            case R.id.root_head_search:
//                intent = new Intent(this.getActivity(), SearchActivity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.image_category1:
//                propertyCode_top = "cj_wy";
//                getGoodList();
//                break;
//            case R.id.image_category2:
//                propertyCode_top = "cj_yw";
//                getGoodList();
//                break;
//            case R.id.image_category3:
//                propertyCode_top = "cj_xx";
//                getGoodList();
//                break;
//            case R.id.image_category4:
//                propertyCode_top = "cj_jh";
//                getGoodList();
//                break;
//
//        }


    }
}
