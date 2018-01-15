package com.weisj.pj.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemCategoryCommodityAdapter;
import com.weisj.pj.adapter.ItemConsigneeAddress;
import com.weisj.pj.adapter.ItemHomeGoodAdapter;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.bean.AdressBean;
import com.weisj.pj.bean.GoodBean;
import com.weisj.pj.bean.HomeBean;
import com.weisj.pj.bean.Region;
import com.weisj.pj.bean.SearchBrand;
import com.weisj.pj.presenter.SearchListPresenter;
import com.weisj.pj.presenter.ShowAddressPresenter;
import com.weisj.pj.utils.PreferencesUtils;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.pj.viewinterface.ISearchListView;
import com.weisj.pj.viewinterface.IShowAddressView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class SearchListActivity extends BaseActivity implements View.OnClickListener, ISearchListView, AbPullToRefreshView.OnFooterLoadListener, AbPullToRefreshView.OnHeaderRefreshListener, AdapterView.OnItemClickListener, BaseQuickAdapter.OnItemClickListener {
    private TextView synthesizeText, saleText, priceText, discountText;
    private ImageView saleImageUp, saleImageDown, priceImageUp, priceImageDown, discountImageUp, discountImageDown;
    private int lastState = 0;
    private int upOrDown = 0;// 0降，1升
    private ListView listView;
    private RecyclerView recyclerView;
    private SearchListPresenter presenter;
    private List<HomeBean.DataEntity.DistrictGoodsListEntity> list = new ArrayList<>();
    private ItemHomeGoodAdapter adapter;
    private ItemCategoryCommodityAdapter adapter2;

    private AbPullToRefreshView refreshView;
    private List<Region.DataEntity> regionList = new ArrayList<>();
    private List<SearchBrand.DataEntity> brandList = new ArrayList<>();
    private int brandId, directId;
    private int from;
    private String goodName;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_search_list, null);
        initView(view);
        from = getIntent().getIntExtra("from", 0);
        goodName = getIntent().getStringExtra("goodName");
        if (getIntent().getIntExtra("couponId", 0) > 0) {
            presenter = new SearchListPresenter(this, this, Urls.getgoodsbycouponyorder, from);
        } else {
            presenter = new SearchListPresenter(this, this, from);
        }
        presenter.getInitData();
        presenter.getRegions();
        presenter.getbrandbydistrict(0);
        return view;
    }

    @Override
    public void onRootViewClick(View v) {
        switch (v.getId()) {
            case R.id.root_head_right_text:
                break;
            case R.id.root_head_back:
                finish();

        }
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        refreshView = (AbPullToRefreshView) view.findViewById(R.id.refresh_view);
        refreshView.setOnFooterLoadListener(this);
        refreshView.setOnHeaderRefreshListener(this);
        view.findViewById(R.id.tv0).setOnClickListener(this);
        view.findViewById(R.id.view1).setOnClickListener(this);
        view.findViewById(R.id.view2).setOnClickListener(this);
        view.findViewById(R.id.view3).setOnClickListener(this);
        listView.setEmptyView(View.inflate(this, R.layout.view_empty, null));

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        synthesizeText = (TextView) view.findViewById(R.id.tv0);
        saleText = (TextView) view.findViewById(R.id.tv2);
        priceText = (TextView) view.findViewById(R.id.tv1);
        discountText = (TextView) view.findViewById(R.id.tv3);

        saleImageUp = (ImageView) view.findViewById(R.id.iv2_up);
        saleImageDown = (ImageView) view.findViewById(R.id.iv2_down);
        priceImageUp = (ImageView) view.findViewById(R.id.iv1_up);
        priceImageDown = (ImageView) view.findViewById(R.id.iv1_down);
        discountImageUp = (ImageView) view.findViewById(R.id.iv3_up);
        discountImageDown = (ImageView) view.findViewById(R.id.iv3_down);

        synthesizeText.setSelected(true);
    }

    @Override
    public String setTitleStr() {
        return goodName;
    }

    @Override
    public void getRefreshData() {
        presenter.getInitData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv0:// 折扣
                selectNumber(0);
                break;
            case R.id.view1:// 特卖
                selectNumber(1);
                break;
            case R.id.view2:// 价格
                selectNumber(2);
                break;
            case R.id.view3:// 综合
                selectNumber(3);
                break;
        }
    }

    // 选中
    private void selectNumber(int number) {
        if (lastState != number) {
            closeLastSelect();
        }
        openSelect(number);
        lastState = number;
        presenter.getInitData();
    }

    // 打开这次选中
    private void openSelect(int state) {
        switch (state) {
            case 0:// 综合
                synthesizeText.setSelected(true);
                break;
            case 1:// 价格
                if (!priceText.isSelected()) {
                    priceText.setSelected(true);
                    priceImageUp.setSelected(true);
                    priceImageDown.setSelected(false);
                    upOrDown = 1;
                } else {
                    priceImageUp.setSelected(!priceImageUp.isSelected());
                    priceImageDown.setSelected(!priceImageDown.isSelected());
                    upOrDown = priceImageUp.isSelected() ? 1 : 0;
                }
                break;
            case 2:// 特卖
                if (!saleText.isSelected()) {
                    saleText.setSelected(true);
                    saleImageUp.setSelected(false);
                    saleImageDown.setSelected(true);
                    upOrDown = 0;
                } else {
                    saleImageUp.setSelected(!saleImageUp.isSelected());
                    saleImageDown.setSelected(!saleImageDown.isSelected());
                    upOrDown = saleImageUp.isSelected() ? 1 : 0;
                }
                break;
            case 3:// 折扣
                if (!discountText.isSelected()) {
                    discountText.setSelected(true);
                    discountImageUp.setSelected(false);
                    discountImageDown.setSelected(true);
                    upOrDown = 0;
                } else {
                    discountImageUp.setSelected(!discountImageUp.isSelected());
                    discountImageDown.setSelected(!discountImageDown.isSelected());
                    upOrDown = discountImageUp.isSelected() ? 1 : 0;
                }
                break;
        }
    }

    // 关闭上次选中
    private void closeLastSelect() {
        switch (lastState) {
            case 0:// 综合
                synthesizeText.setSelected(false);
                break;
            case 1:// 价格
                priceText.setSelected(false);
                priceImageUp.setSelected(false);
                priceImageDown.setSelected(false);
                break;
            case 2:// 特卖
                saleText.setSelected(false);
                saleImageUp.setSelected(false);
                saleImageDown.setSelected(false);
                break;
            case 3:// 折扣
                discountText.setSelected(false);
                discountImageUp.setSelected(false);
                discountImageDown.setSelected(false);
                break;
        }
    }

    @Override
    public int getCategoryId() {
        return getIntent().getIntExtra("categoryId", 0);
    }

    @Override
    public int getOrderField() {
        return lastState;
    }

    @Override
    public int getOrderType() {
        return upOrDown;
    }

    @Override
    public int getBrandId() {
        return brandId;
    }

    @Override
    public int getDirectId() {
        return directId;
    }

    @Override
    public void getData(GoodBean goodBean) {
        list.addAll(goodBean.getData());
        adapter.notifyDataSetChanged();
        refreshView.onFooterLoadFinish();
        adapter2.notifyDataSetChanged();
    }

    @Override
    public void getInitData(GoodBean goodBean) {
        list.clear();
        list.addAll(goodBean.getData());
        if (adapter == null) {
            adapter = new ItemHomeGoodAdapter(this, list);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        refreshView.onHeaderRefreshFinish();


        if (adapter2 == null) {
            adapter2 = new ItemCategoryCommodityAdapter(list);
            recyclerView.setAdapter(adapter2);
            adapter2.setOnItemClickListener(this);
        } else {
            adapter2.notifyDataSetChanged();
        }

    }

    @Override
    public void getRegions(Region region) {
        if (region.getCode().equals("1")) {
            regionList.clear();
            regionList.addAll(region.getData());
        }
    }

    @Override
    public void getBrandsByRegion(SearchBrand searchBrand) {
        if (searchBrand.getCode().equals("1")) {
            brandList.clear();
            brandList.addAll(searchBrand.getData());
        }
    }

    @Override
    public String getGoodName() {
        return getIntent().getStringExtra("goodName");
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        presenter.getData();
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getInitData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HomeBean.DataEntity.DistrictGoodsListEntity data = (HomeBean.DataEntity.DistrictGoodsListEntity) adapter.getItem(position);
        Intent intent = new Intent(this, GoodDetailActivity.class);
        intent.putExtra("goodId", data.getGoodsId());
        startActivity(intent);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeBean.DataEntity.DistrictGoodsListEntity data = (HomeBean.DataEntity.DistrictGoodsListEntity) adapter.getItem(position);
        Intent intent = new Intent(this, GoodDetailActivity.class);
        intent.putExtra("goodId", data.getGoodsId());
        startActivity(intent);
    }

    /**
     * Created by Administrator on 2016/7/22 0022.
     */
    public static class ConsigneeAddressActivity extends BaseActivity implements IShowAddressView {
        private RecyclerView recyclerView;
        private ShowAddressPresenter presenter;
        private AdressBean adressBean;

        @Override
        public View initView(Bundle savedInstanceState) {
            View view = mLayoutInflater.inflate(R.layout.activity_consignee_address, null);
            initView(view);
            return view;
        }

        public ShowAddressPresenter getPresenter() {
            return presenter;
        }

        private void initView(View view) {
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            rootView.setRightText("添加",true);
            presenter = new ShowAddressPresenter(this, this);
        }

        @Override
        public String setTitleStr() {
            return "我的收货地址";
        }

        @Override
        public void showAddress(AdressBean adressBean) {
            this.adressBean = adressBean;
            recyclerView.setAdapter(new ItemConsigneeAddress(ConsigneeAddressActivity.this, adressBean));
            recyclerView.setLayoutManager(new LinearLayoutManager(ConsigneeAddressActivity.this));
        }

        @Override
        protected void onResume() {
            super.onResume();
            presenter.getAddress();
        }

        @Override
        public void successAddress(String addressId) {
            AdressBean.DataEntity data = null;
            for (AdressBean.DataEntity dataEntity : adressBean.getData()) {
                if (String.valueOf(dataEntity.getRecipient_id()).equals(addressId)) {
                    data = dataEntity;
                    break;
                }
            }
            adressBean.getData().remove(data);
            recyclerView.getAdapter().notifyDataSetChanged();
            if (!(adressBean.getData().size() > 0)) {
                showNoData();
                PreferencesUtils.putInt("has_default_consignee", 0);
            }
        }

        @Override
        public void onHeadClick(View v) {
            if (v.getId() == R.id.root_head_right_text) {
                Intent intent = new Intent(this, EditAddressActivity.class);
                intent.putExtra("Title", "add");
                startActivity(intent);
            }
        }

        @Override
        public void getRefreshData() {
            presenter.getAddress();
        }
    }
}
