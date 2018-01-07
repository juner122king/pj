package com.weisj.pj.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemCategoryCommodityAdapter;
import com.weisj.pj.base.BaseFragment;
import com.weisj.pj.base.activity.SearchActivity;
import com.weisj.pj.bean.CategoryBean;
import com.weisj.pj.bean.CommodityShow;
import com.weisj.pj.bean.GoodBean;
import com.weisj.pj.bean.GoodsParams;
import com.weisj.pj.presenter.CategoryPresenter;
import com.weisj.pj.viewinterface.ICategoryView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by zh on 16/6/21.
 * 品类首页
 */
public class CategoryFragment extends BaseFragment implements AdapterView.OnItemClickListener, ICategoryView, View.OnClickListener {

    private CategoryPresenter presenter;
    private RecyclerView recyclerView;
    private ImageView imageView1, imageView2, imageView3, imageView4;
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


        imageView1 = (ImageView) headView.findViewById(R.id.image_category1);
        imageView2 = (ImageView) headView.findViewById(R.id.image_category2);
        imageView3 = (ImageView) headView.findViewById(R.id.image_category3);
        imageView4 = (ImageView) headView.findViewById(R.id.image_category4);

        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513048838&di=d0fc7049c9778fd1841e5043be3b8331&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.a-do.tw%2Fphoto%2Fprt%2F4c%5B4-1%5D%2Fp112836%2F%5Bre%5D112836%2F201412213766.jpg").crossFade().into(imageView1);
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513048838&di=d0fc7049c9778fd1841e5043be3b8331&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.a-do.tw%2Fphoto%2Fprt%2F4c%5B4-1%5D%2Fp112836%2F%5Bre%5D112836%2F201412213766.jpg").crossFade().into(imageView2);
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513048838&di=d0fc7049c9778fd1841e5043be3b8331&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.a-do.tw%2Fphoto%2Fprt%2F4c%5B4-1%5D%2Fp112836%2F%5Bre%5D112836%2F201412213766.jpg").crossFade().into(imageView3);
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513048838&di=d0fc7049c9778fd1841e5043be3b8331&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.a-do.tw%2Fphoto%2Fprt%2F4c%5B4-1%5D%2Fp112836%2F%5Bre%5D112836%2F201412213766.jpg").crossFade().into(imageView4);

        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);


        home_more = (TextView) headView.findViewById(R.id.home_more);
        view.findViewById(R.id.root_head_search).setOnClickListener(this);
        RadioGroup rg1 = (RadioGroup) headView.findViewById(R.id.rg1);
        RadioGroup rg2 = (RadioGroup) headView.findViewById(R.id.rg2);
        RadioGroup rg3 = (RadioGroup) headView.findViewById(R.id.rg3);
        RadioGroup rg4 = (RadioGroup) headView.findViewById(R.id.rg4);
        RadioGroup rg5 = (RadioGroup) headView.findViewById(R.id.rg5);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View checkView = headView.findViewById(checkedId);
                if (!checkView.isPressed()) {
                    return;
                }
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
                        goodsParams.setCategoryCode(null);
                        break;
                }
                getGoodList();
            }
        });

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View checkView = headView.findViewById(checkedId);
                if (!checkView.isPressed()) {
                    return;
                }

                switch (checkedId) {
                    case R.id.rg2_rb1:
                        propertyCode2 = "fg_sw";
                        break;
                    case R.id.rg2_rb2:
                        propertyCode2 = "fg_mzf";
                        break;
                    case R.id.rg2_rb3:
                        propertyCode2 = "fg_tm";
                        break;
                    case R.id.rg2_rb4:
                        propertyCode2 = "fg_fg";
                        break;
                    case R.id.rg2_rb5:
                        propertyCode2 = "fg_om";
                        break;
                    case R.id.rg2_rb6:
                        propertyCode2 = "fg_jy";
                        break;
                    case R.id.rg2_rb7:
                        propertyCode2 = "fg_rh";
                        break;
                    case R.id.rg2_rb8:
                        propertyCode2 = "fg_qt";
                        break;
                    case R.id.rg2_rb9:
                        propertyCode2 = null;
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
        rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View checkView = headView.findViewById(checkedId);
                if (!checkView.isPressed()) {
                    return;
                }

                switch (checkedId) {
                    case R.id.rg5_rb1:
                        propertyCode5 = "rq_ql";

                        break;
                    case R.id.rg5_rb2:
                        propertyCode5 = "rq_woman";
                        break;
                    case R.id.rg5_rb3:
                        propertyCode5 = "rq_man";
                        break;
                    case R.id.rg5_rb4:
                        propertyCode5 = null;
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

        if (!v.isPressed()) {
            return;
        }
        switch (v.getId()) {
            case R.id.root_head_search:
                intent = new Intent(this.getActivity(), SearchActivity.class);
                startActivity(intent);
                break;

            case R.id.image_category1:
                propertyCode_top = "cj_wy";
                getGoodList();
                break;
            case R.id.image_category2:
                propertyCode_top = "cj_xx";
                getGoodList();
                break;
            case R.id.image_category3:
                propertyCode_top = "cj_yw";
                getGoodList();
                break;
            case R.id.image_category4:
                propertyCode_top = "cj_jh";
                getGoodList();
                break;

        }


    }
}
