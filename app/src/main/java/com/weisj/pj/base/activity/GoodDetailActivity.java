package com.weisj.pj.base.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weisj.pj.MainActivity;
import com.weisj.pj.R;
import com.weisj.pj.TestData;
import com.weisj.pj.adapter.ItemGoodDetailCommentAdapter;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.bean.GoodDetail;
import com.weisj.pj.bean.GoodDetailImageBean;
import com.weisj.pj.presenter.GoodDetailPresenter;
import com.weisj.pj.utils.ImageLoaderUtils;
import com.weisj.pj.utils.TextViewUtils;
import com.weisj.pj.view.MyRatingBar;
import com.weisj.pj.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.pj.view.bgabanner.BGABanner;
import com.weisj.pj.viewinterface.IGoodDetailView;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class GoodDetailActivity extends BaseActivity implements IGoodDetailView, AbPullToRefreshView.OnHeaderRefreshListener, View.OnClickListener {
    private BGABanner bgaBanner;
    private TextView goodname, goodPrice, goodPerson, tv_sku;
    private RecyclerView recyclerView;
    private GoodDetailPresenter presenter;
    private AbPullToRefreshView refreshView;
    private GoodDetail goodDetail;
    private MyRatingBar goodBar;
    private int state = 0;


    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_good_detail, null);
        state = getIntent().getIntExtra("good_state", 0);
        initView(view);
        presenter = new GoodDetailPresenter(this, this);
        presenter.getData(true);
        return view;
    }

    private void initView(View view) {
        refreshView = (AbPullToRefreshView) view.findViewById(R.id.refresh_view);

        view.findViewById(R.id.good_image_linear).setOnClickListener(this);
        view.findViewById(R.id.good_point).setOnClickListener(this);
        view.findViewById(R.id.tv_gp_more).setOnClickListener(this);
        view.findViewById(R.id.tv_b1).setOnClickListener(this);
        view.findViewById(R.id.tv_b2).setOnClickListener(this);
        view.findViewById(R.id.tv_b3).setOnClickListener(this);
        view.findViewById(R.id.tv_b4).setOnClickListener(this);
        refreshView.setLoadMoreEnable(false);
        refreshView.setOnHeaderRefreshListener(this);
        bgaBanner = (BGABanner) view.findViewById(R.id.bagbanner_container);
        bgaBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                ImageLoaderUtils.getInstance().display((ImageView) view, (String) model);
            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        goodname = (TextView) view.findViewById(R.id.good_name);
        tv_sku = (TextView) view.findViewById(R.id.tv_sku);
        goodPrice = (TextView) view.findViewById(R.id.good_price);
        goodPerson = (TextView) view.findViewById(R.id.good_detail_person);
        goodBar = (MyRatingBar) view.findViewById(R.id.good_detail_bar);

    }

    @Override
    public String setTitleStr() {
        return "详情";
    }

    @Override
    public void getRefreshData() {
        presenter.getData(true);
    }

    @Override
    public int getGoodId() {
        return getIntent().getIntExtra("goodId", 1);
    }

    @Override
    public void getData(GoodDetail goodDetail) {
        this.goodDetail = goodDetail;
        if (goodDetail.getData().getImages() != null && goodDetail.getData().getImages().size() > 0) {
            bgaBanner.setData(goodDetail.getData().getImages(), null);
        }

        TextViewUtils.setText(goodname, goodDetail.getData().getGoodsName());
        TextViewUtils.setText(tv_sku, goodDetail.getData().getGoodsName());


        TextViewUtils.setTextAndleftOther(goodPrice, goodDetail.getData().getPrice(), "市场价:￥");

        TextViewUtils.setText(goodPerson, String.valueOf("精彩评价(" + goodDetail.getData().getCommentNum() + ")"));

        try {
            goodBar.setPoint(Integer.valueOf(TextViewUtils.sprStringMoneyToInt(goodDetail.getData().getGoodsPoint())));
//            goodBar.setPoint(4);
        } catch (Exception e) {
            e.printStackTrace();
            goodBar.setPoint(0);
        }
        if (goodDetail.getData().getCommentNum() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new ItemGoodDetailCommentAdapter(TestData.getGoodPoint_DataBean()));
        } else
            recyclerView.setVisibility(View.GONE);

        refreshView.onHeaderRefreshFinish();
    }


    @Override
    public void getImageData(GoodDetailImageBean goodDetailImageBean) {

    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public int getActivityId() {
        return getIntent().getIntExtra("activity_id", 0);
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
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getData(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.good_share:
//                if (goodDetail != null) {
//                    if ((state > 0 && goodDetail.getData().getSharePic() != null) || (state == 0 && goodDetail.getData().getImages() != null && goodDetail.getData().getImages().size() > 0)) {
//                        ImageLoaderUtils.getInstance().display(state == 0 ? goodDetail.getData().getImages().get(0) : goodDetail.getData().getSharePic(), new SimpleImageLoadingListener() {
//                            @Override
//                            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                                ShareData shareData = new ShareData(loadedImage, goodDetail.getData().getImages(), goodDetail.getData().getShareDes(), state == 0 ? goodDetail.getData().getShareTitle() : goodDetail.getData().getActivityName(), goodDetail.getData().getWebsite(), getGoodId(), 0, goodDetail.getData().getPrice(), goodDetail.getData().getDelMoney());
//                                new ShareViewDialog(GoodDetailActivity.this, shareData).show();
//                            }
//
//                            @Override
//                            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                                ShareData shareData = new ShareData(BitmapFactory.decodeResource(GoodDetailActivity.this.getResources(), R.mipmap.icon_share_sf), goodDetail.getData().getShareDes(), state == 0 ? goodDetail.getData().getShareTitle() : goodDetail.getData().getActivityName(), goodDetail.getData().getWebsite(), getGoodId(), 0, goodDetail.getData().getPrice(), goodDetail.getData().getDelMoney());
//                                new ShareViewDialog(GoodDetailActivity.this, shareData).show();
//                            }
//                        });
//                    } else {
//                        ShareData shareData = new ShareData(BitmapFactory.decodeResource(GoodDetailActivity.this.getResources(), R.mipmap.icon_share_sf), goodDetail.getData().getShareDes(), state == 0 ? goodDetail.getData().getShareTitle() : goodDetail.getData().getActivityName(), goodDetail.getData().getWebsite(), getGoodId(), 0, goodDetail.getData().getPrice(), goodDetail.getData().getDelMoney());
//                        new ShareViewDialog(GoodDetailActivity.this, shareData).show();
//                    }
//                }
//                break;
            case R.id.good_image_linear:
                Intent intent = new Intent(this, GoodImageDetailActivity.class);
                intent.putExtra("goodId", getGoodId());
                startActivity(intent);
                break;
//
//
            case R.id.tv_gp_more:
                Intent intent2 = new Intent(GoodDetailActivity.this, GoodPointActivity.class);

                intent2.putExtra("pic_url", goodDetail.getData().getImages().get(0));
                intent2.putExtra("title", goodDetail.getData().getGoodsName());
                intent2.putExtra("jq", goodDetail.getData().getPrice());

                startActivity(intent2);

                break;
            case R.id.tv_b1:

                Dialog alertDialog = new AlertDialog.Builder(this).
                        setTitle("联系客服").
                        setMessage("400-099-828").
                        create();
                alertDialog.show();

                break;

            case R.id.tv_b2:
                Toast.makeText(GoodDetailActivity.this, "暂不开放", Toast.LENGTH_SHORT).show();

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
