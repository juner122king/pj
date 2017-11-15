package com.weisj.pj.base.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemSpecialGoodAdapter;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.bean.HomeBean;
import com.weisj.pj.bean.ShareData;
import com.weisj.pj.bean.SpecialGoodsBean;
import com.weisj.pj.presenter.SpecialGoodsListPresenter;
import com.weisj.pj.utils.CommenString;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.pj.view.dialog.ShareViewDialog;
import com.weisj.pj.viewinterface.ISpecialGoodsListView;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017-07-10.
 */

public class SpecialGoodsListActivity extends BaseActivity implements View.OnClickListener, AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener, ISpecialGoodsListView {
    private TextView synthesizeText, priceText, discountText;
    private ImageView priceImageUp, priceImageDown, discountImageUp, discountImageDown;
    private int lastState = 0;
    private int upOrDown = 0;// 0降，1升
    private ListView listView;
    private AbPullToRefreshView refreshView;
    private SpecialGoodsListPresenter presenter;
    private ArrayList<HomeBean.DataEntity.AreaGoodsListListEntity> list = new ArrayList<>();
    private ItemSpecialGoodAdapter adapter;

    private String adId = "68";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_search_high_list, null);
        initView(view);
        presenter = new SpecialGoodsListPresenter(this, this);
        presenter.getInitData();
        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        refreshView = (AbPullToRefreshView) view.findViewById(R.id.refresh_view);
        refreshView.setOnFooterLoadListener(this);
        refreshView.setOnHeaderRefreshListener(this);
        view.findViewById(R.id.choose_synthesize).setOnClickListener(this);
        view.findViewById(R.id.choose_price).setOnClickListener(this);
        view.findViewById(R.id.choose_discount).setOnClickListener(this);
        listView.setEmptyView(View.inflate(this, R.layout.view_empty, null));

        synthesizeText = (TextView) view.findViewById(R.id.choose_synthesize);
        priceText = (TextView) view.findViewById(R.id.choose_price_text);
        discountText = (TextView) view.findViewById(R.id.choose_discount_text);

        priceImageUp = (ImageView) view.findViewById(R.id.choose_price_up);
        priceImageDown = (ImageView) view.findViewById(R.id.choose_price_down);
        discountImageUp = (ImageView) view.findViewById(R.id.choose_discount_up);
        discountImageDown = (ImageView) view.findViewById(R.id.choose_discount_down);

        synthesizeText.setSelected(true);

        setRightText("分享",true);
    }

    @Override
    public String setTitleStr() {
        return "特色专区";
    }

    @Override
    public void onHeadClick(View v) {
        if (v.getId() == R.id.root_head_right_text) {
            String websit = "http://m.sfddj.com/Shop/Goods/activity_all_goods.html?ad_id="+list.get(0).getAdId()+ "&sell_member_id="+ PersonMessagePreferencesUtils.getUid();
            ShareData shareData = new ShareData(true, CommenString.selectCity+"区"+ list.get(0).getAdName(), CommenString.selectCity+"区"+ list.get(0).getAdName(), websit);
            new ShareViewDialog(this, shareData).show();

        }
    }

    @Override
    public void getRefreshData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_discount:// 折扣
                selectNumber(2);
                break;
            case R.id.choose_price:// 价格
                selectNumber(1);
                break;
            case R.id.choose_synthesize:// 综合
                selectNumber(0);
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
            case 2:// 折扣
                discountText.setSelected(false);
                discountImageUp.setSelected(false);
                discountImageDown.setSelected(false);
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
            case 2:// 折扣
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

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getInitData();
    }


    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        presenter.getData();
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
    public String getAd_id() {
        return getIntent().getStringExtra("ad_id");
    }

    @Override
    public void getData(SpecialGoodsBean goodBean) {
        list.addAll(goodBean.getData());
        adapter.notifyDataSetChanged();
        refreshView.onFooterLoadFinish();
    }

    @Override
    public void getInitData(SpecialGoodsBean goodBean) {
        list.clear();
        list.addAll(goodBean.getData());
        if (adapter == null) {
            adapter = new ItemSpecialGoodAdapter(this, list);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        refreshView.onHeaderRefreshFinish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SpecialGoodsList Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
