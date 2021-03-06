package com.weisj.pj.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.weisj.pj.MainActivity;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemHomeUserCommentAdapter;
import com.weisj.pj.base.BaseFragment;
import com.weisj.pj.base.activity.CouponDetail1;
import com.weisj.pj.base.activity.GoodDetailActivity;
import com.weisj.pj.base.activity.LocationActivity;
import com.weisj.pj.base.activity.SearchActivity;
import com.weisj.pj.base.activity.SearchListActivity;
import com.weisj.pj.base.activity.VipActivity;
import com.weisj.pj.base.activity.WebViewActivity;
import com.weisj.pj.bean.ActivityBean;
import com.weisj.pj.bean.Ad;
import com.weisj.pj.bean.Comment;
import com.weisj.pj.bean.HomeBanner;
import com.weisj.pj.bean.HomeBean;
import com.weisj.pj.presenter.HomePresenter;
import com.weisj.pj.utils.CommenString;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.TextViewUtils;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.pj.view.bgabanner.BGABanner;
import com.weisj.pj.view.photocheck.GlideRoundTransform;
import com.weisj.pj.viewinterface.IHomeView;

import java.util.List;


/**
 * Created by zh on 16/6/21.
 * 首页主页
 */
public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener, AbPullToRefreshView.OnHeaderRefreshListener, IHomeView, View.OnClickListener {
    private HomePresenter homePresenter;
    //    private String pronvin = CommenString.selectCity;
    private RecyclerView recyclerView;//最底部的recyclerView
    private ItemHomeUserCommentAdapter adapter_comment;
    private TextView placeName, tv_acton_title, tv_acton_info, tv_dayandcon;
    private ImageView iv_acton, homeBanner_vip;
    private BGABanner homeBanner, homeBanner2;

    private HorizontalScrollView scrollView;
    public static String shareCity;

    private LinearLayout addressLocation, horizontalLinear;
    View headView, tv_vip;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenter(this, this);


    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        headView = mInflater.inflate(R.layout.head_home, null);

        loadView(view);
        rootView.hintBackView(false);
        setHideHead();
        return view;
    }

    private void loadView(View view) {


        tv_acton_title = (TextView) headView.findViewById(R.id.tv_acton_title);
        tv_acton_info = (TextView) headView.findViewById(R.id.tv_acton_info);
        iv_acton = (ImageView) headView.findViewById(R.id.iv_acton);
        tv_dayandcon = (TextView) headView.findViewById(R.id.tv_dayandcon);
        tv_vip = headView.findViewById(R.id.tv_vip);


        placeName = (TextView) view.findViewById(R.id.root_place);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        headView.findViewById(R.id.home_more).setOnClickListener(this);
        view.findViewById(R.id.root_head_search).setOnClickListener(this);
        iv_acton.setOnClickListener(this);
        tv_vip.setOnClickListener(this);
        homeBanner = (BGABanner) headView.findViewById(R.id.home_banner);
        homeBanner2 = (BGABanner) headView.findViewById(R.id.home_banner2);
        homeBanner_vip = (ImageView) headView.findViewById(R.id.home_banner_vip);


        scrollView = (HorizontalScrollView) headView.findViewById(R.id.home_scroll_view);
        horizontalLinear = (LinearLayout) headView.findViewById(R.id.home_boss_good_linear);

        headView.findViewById(R.id.im_tv_button0).setOnClickListener(this);
        headView.findViewById(R.id.im_tv_button1).setOnClickListener(this);
        headView.findViewById(R.id.im_tv_button2).setOnClickListener(this);

        homeBanner_vip.setOnClickListener(this);

        homePresenter.getInitData(true);
        addressLocation = (LinearLayout) view.findViewById(R.id.root_left);
        addressLocation.setOnClickListener(this);

        homeBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                final Ad bean = (Ad) model;

                Glide.with(getActivity())
                        .load(Urls.imageUrl + bean.getAdPic())

                        .centerCrop()
                        .into((ImageView) view);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toWebViewActivity(bean.getDiyAdLink(), bean.getAdName());
                    }
                });
            }
        });

        homeBanner2.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                final Ad bean = (Ad) model;

                Glide.with(getActivity())
                        .load(Urls.imageUrl + bean.getAdPic())


                        .centerCrop()
                        .transform(new GlideRoundTransform(getActivity(), 3))
                        .into((ImageView) view.findViewById(R.id.iv));


                ((TextView) view.findViewById(R.id.tv_title)).setText(bean.getAdName());
                ((TextView) view.findViewById(R.id.tv_content)).setText(bean.getAdDesc());

                view.findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toWebViewActivity(bean.getDiyAdLink(), bean.getAdName());
                    }
                });
            }
        });
//        homeBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
//            @Override
//            public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
//                HomeBanner.ResultBean.MaterialBean materialBean = (HomeBanner.ResultBean.MaterialBean) model;
//                if (position == 0 && materialBean.getEntity() !=null) {
//                    HomeCouponbean.DataEntity.SingleCouponListEntity dataEntity = materialBean.getEntity();
//                    Intent intent = new Intent(getContext(), WebViewActivity.class);
//                    intent.putExtra("url", dataEntity.getHtmlAddress());
//                    intent.putExtra("web_title", dataEntity.getCouponTitle());
//                    intent.putExtra("imageUrl", dataEntity.getSharePic() != null ? dataEntity.getSharePic() : dataEntity.getCouponPic());
//                    intent.putExtra("coupon_id", dataEntity.getCouponId());
//                    getContext().startActivity(intent);
//                } else {
//                    Intent intent = new Intent(getContext(), WebViewActivity.class);
//                    if (materialBean.getUrl().contains("?")) {
//                        intent.putExtra("url", materialBean.getUrl() + "&sell_member_id=" + PersonMessagePreferencesUtils.getUid());
//                    } else {
//                        intent.putExtra("url", materialBean.getUrl() + "?sell_member_id=" + PersonMessagePreferencesUtils.getUid());
//                    }
//                    intent.putExtra("web_title", materialBean.getTitle());
//                    intent.putExtra("imageUrl", Urls.imageUrl + materialBean.getImageUrl());
//                    HomeFragment.this.startActivity(intent);
//                }
//            }
//        });


//        homeBanner_vip.setOnItemClickListener(new BGABanner.OnItemClickListener() {
//            @Override
//            public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
//
//                startActivity(new Intent(getContext(), VipActivity.class));
//
//            }
//        });
//

        placeName.setText(CommenString.selectCity);


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public String setTitleStr() {
        return "菲尔南多";
    }

    @Override
    public void onHeadClick(View v) {
        switch (v.getId()) {
            case R.id.root_head_back:
                startActivity(new Intent(this.getActivity(), CouponDetail1.class));
                break;
        }
    }

    @Override
    public void getRefreshData() {
        homePresenter.getInitData(false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }


    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        homePresenter.getInitData(false);
    }


//    private void loadHomeCoupon(List<HomeCouponbean.DataEntity.SingleCouponListEntity> list) {
//        couponLinear.removeAllViews();
//
//        if (list != null && list.size() > 0) {
//            HomeBanner.ResultBean.MaterialBean beanData = new HomeBanner.ResultBean.MaterialBean();
//            beanData.setEntity(list.get(0));
//            if (homeBanner.getmModels() != null && homeBanner.getmModels().size() > 0 && !isRefresh) {
//                List datas = homeBanner.getmModels();
//                datas.add(0, beanData);
//
//                homeBanner.setData(datas, null);
//                homeBanner2.setData(R.layout.bgabanner2_item, datas, null);
//            } else {
//                isRefresh = false;
//                List data = new ArrayList();
//                data.add(beanData);
//                homeBanner.setData(data, null);
//                homeBanner2.setData(R.layout.bgabanner2_item, data, null);
//            }
//        }
//
//        for (int i = 1; i < list.size(); i++) {
//            ImageView image = new ImageView(this.getContext());
//            SystemConfig.dynamicSetWidthAndHeight(image, -1, 260, 10, 0, 0, 0);
//            couponLinear.addView(image);
//            ImageLoaderUtils.getInstance().display(image, list.get(i).getCouponPic());
//            image.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    HomeCouponbean.DataEntity.SingleCouponListEntity dataEntity = (HomeCouponbean.DataEntity.SingleCouponListEntity) v.getTag();
//                    Intent intent = new Intent(getContext(), WebViewActivity.class);
//                    intent.putExtra("url", dataEntity.getHtmlAddress());
//                    intent.putExtra("web_title", dataEntity.getCouponTitle());
//                    intent.putExtra("imageUrl", dataEntity.getSharePic() != null ? dataEntity.getSharePic() : dataEntity.getCouponPic());
//                    intent.putExtra("coupon_id", dataEntity.getCouponId());
//                    getContext().startActivity(intent);
//                }
//            });
//            image.setTag(list.get(i));
//        }
//    }


    @Override
    public void getData(HomeBean homeBean) {
        shareCity = homeBean.getData().getGuanName();
//        ImageLoaderUtils.getInstance().display(iv_acton, homeBean.getData().getHighCommissionPicUrl());
//
//        if (homeBean.getData().getDistrictGoodsList().size() > 0) {
//            loadHomeThreeGoods(homeBean, 0, goodName1, goodPrice1, goodImage1);
//        } else {
//            goodLinear.setVisibility(View.GONE);
//        }
//        if (homeBean.getData().getDistrictGoodsList().size() > 1) {
//            loadHomeThreeGoods(homeBean, 1, goodName2, goodPrice2, goodImage2);
//        } else {
//            goodPrice2.setVisibility(View.INVISIBLE);
//        }
//        if (homeBean.getData().getDistrictGoodsList().size() > 2) {
//            loadHomeThreeGoods(homeBean, 2, goodName3, goodPrice3, goodImage3);
//        } else {
//            goodPrice3.setVisibility(View.INVISIBLE);
//        }
//
//
//        if (homeBean.getData().getPositionAreaGoodsList().size() > 0) {
////            AreaGoodsListView.setVisibility(View.VISIBLE);
//            areaGoodsAdapter = new ItemHomeAreaGoodsAdapter(getContext(), homeBean.getData().getPositionAreaGoodsList());
//            AreaGoodsListView.setAdapter(areaGoodsAdapter);
//        }
//
//        loadHomeCoupon(homeBean.getData().getDistrictCouponList());
//        adapter = new ItemHomeGoodAdapter(getContext(), homeBean.getData().getCountryGoodsList());
//

        List<Comment.DataBean> comments = homeBean.getData().getCommentList(); //用户评论
        List<Ad> topAdList = homeBean.getData().getTopAdList();//顶部轮播图
        List<Ad> insertAdList = homeBean.getData().getInsertAdList();//vip卡图
        List<Ad> rotateAdList = homeBean.getData().getRotateAdList();//专题轮播图
        List<HomeBean.DataEntity.DistrictGoodsListEntity> recommendGoodsList = homeBean.getData().getRecommendGoodsList();//推荐商品

        Glide.with(getActivity())
                .load(Urls.imageUrl + insertAdList.get(0).getAdPic())

                .transform(new GlideRoundTransform(getActivity(), 3))
                .into(homeBanner_vip);

        adapter_comment = new ItemHomeUserCommentAdapter(comments);
        adapter_comment.addHeaderView(headView);
        recyclerView.setAdapter(adapter_comment);


        if (null != topAdList) {
            homeBanner.setData(topAdList, null);
        } else {
            homeBanner.setVisibility(View.GONE);
        }


        if (null != rotateAdList) {
            homeBanner2.setData(R.layout.bgabanner2_item, rotateAdList, null);

        } else {
            homeBanner2.setVisibility(View.GONE);
        }


        horizontalLinear.removeAllViews();

        if (null != recommendGoodsList) {
            for (int i = 0; i < recommendGoodsList.size(); i++) {
                View view = getView(recommendGoodsList.get(i));
                horizontalLinear.addView(view);
            }
        }


    }

    @Override
    public void getActivitys(ActivityBean bean) {

        ActivityBean.DataEntity bean1 = bean.getData().get(0);


        Glide.with(getActivity())
                .load(bean1.getActivityPic())
                .transform(new GlideRoundTransform(getActivity(), 3))
                .into(iv_acton);

        Long l1 = Long.valueOf(bean1.getStartTime());
        Long l2 = Long.valueOf(bean1.getEndTime());
        Long l3 = Long.valueOf(bean1.getCreateTime());

        String day = String.valueOf((l2 - System.currentTimeMillis()) / (1000 * 3600 * 24));

        tv_dayandcon.setText("剩余" + day + "天 已报名" + bean1.getAttendCount() + "人");
        tv_acton_title.setText(bean1.getActivityName());


    }

    private View getView(final HomeBean.DataEntity.DistrictGoodsListEntity item) {
        View view = mInflater.inflate(R.layout.item_home_scroll, null);


        Glide.with(getContext()).load(Urls.imageUrl + item.getImg1()).crossFade().into((ImageView) view.findViewById(R.id.image));

        TextViewUtils.setText((TextView) view.findViewById(R.id.good_name), item.getGoodsName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), GoodDetailActivity.class);
                intent.putExtra("goodId", item.getGoodsId());
                getContext().startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {

            case R.id.home_banner_vip:

//              intent = new Intent(getContext(), VipActivity.class);
//              startActivity(intent);

                startActivityForResult(new Intent(getActivity(), VipActivity.class), MainActivity.HomeTOVip);
                break;

            case R.id.root_left:
                intent = new Intent(this.getActivity(), LocationActivity.class);
                startActivityForResult(intent, 10);
                break;


            case R.id.iv_acton:
                toWebViewActivity("http://shop.party-queen.com/Shop/Notice/index.html?id=58", "千人计划—“最美体验官”火热招募中！");
                break;

            case R.id.tv_vip:
                toWebViewActivity("http://shop.party-queen.com/Shop/Notice/index.html?id=58", "千人计划—“最美体验官”火热招募中！");
                break;


            case R.id.home_more:
                intent = new Intent(getContext(), SearchListActivity.class);
                intent.putExtra("from", 1);
                startActivity(intent);
                break;
            case R.id.root_head_search:
                intent = new Intent(this.getActivity(), SearchActivity.class);
                startActivity(intent);
                break;


            case R.id.im_tv_button0:
                toWebViewActivity("http://shop.party-queen.com/Shop/Notice/index.html?id=50", "首饰共享");
                break;
            case R.id.im_tv_button1:
                toWebViewActivity("http://shop.party-queen.com/Shop/Notice/index.html?id=51", "会员权益");
                break;
            case R.id.im_tv_button2:
                toWebViewActivity("http://shop.party-queen.com/Shop/Notice/index.html?id=52", "邀请有礼");
                break;

        }
    }


    private void toWebViewActivity(String url, String title) {

        if (url.equals("http://shop.party-queen.com/Shop/Cards/index.html")) {
            startActivityForResult(new Intent(getActivity(), VipActivity.class), MainActivity.HomeTOVip);
            return;
        }
        Intent intent = new Intent(this.getActivity(), WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("web_title", title);
        this.getActivity().startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 10) {
//            placeName.setText(data.getStringExtra("city"));
//            CommenString.selectCity = data.getStringExtra("city");
//            if (!pronvin.equals(CommenString.selectCity)) {
//                pronvin = CommenString.selectCity;
//                homePresenter.getInitData(true);
//            }
//        }


    }
}
