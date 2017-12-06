package com.weisj.pj.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemHomeAreaGoodsAdapter;
import com.weisj.pj.adapter.ItemHomeGoodAdapter;
import com.weisj.pj.adapter.ItemHomeUserCommentAdapter;
import com.weisj.pj.base.BaseFragment;
import com.weisj.pj.base.activity.CouponDetail1;
import com.weisj.pj.base.activity.GoodDetailActivity;
import com.weisj.pj.base.activity.LocationActivity;
import com.weisj.pj.base.activity.SearchActivity;
import com.weisj.pj.base.activity.SearchHighListActivity;
import com.weisj.pj.base.activity.SearchListActivity;
import com.weisj.pj.base.activity.SystemNoticeActivity;
import com.weisj.pj.base.activity.WebViewActivity;
import com.weisj.pj.bean.HomeBanner;
import com.weisj.pj.bean.HomeBean;
import com.weisj.pj.bean.HomeCouponbean;
import com.weisj.pj.bean.ShareData;
import com.weisj.pj.bean.UsershareBean;
import com.weisj.pj.presenter.HomePresenter;
import com.weisj.pj.utils.CommenString;
import com.weisj.pj.utils.ImageLoaderUtils;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.PreferencesUtils;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.utils.TextViewUtils;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.view.MyListView;
import com.weisj.pj.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.pj.view.bgabanner.BGABanner;
import com.weisj.pj.view.dialog.SelectCityDialog;
import com.weisj.pj.view.dialog.ShareViewDialog;
import com.weisj.pj.viewinterface.IHomeView;

import java.util.ArrayList;
import java.util.List;

import static com.umeng.socialize.utils.DeviceConfig.context;


/**
 * Created by zh on 16/6/21.
 * 首页主页
 */
public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener, AbPullToRefreshView.OnHeaderRefreshListener, IHomeView, View.OnClickListener {
    private HomePresenter homePresenter;
    private AbPullToRefreshView refreshView;
    private String pronvin = CommenString.selectCity;
    private ListView listView;//最底部的listView
    private TextView goodName1, goodName2, goodName3, goodPrice1, goodPrice2, goodPrice3, userName, areaRecommend;
    private ImageView goodImage1, goodImage2, goodImage3, highImage, iv_acton;
    private LinearLayout couponLinear, goodLinear, user_shareLinear;
    private ItemHomeGoodAdapter adapter;
    private ItemHomeUserCommentAdapter adapter2;
    private TextView placeName, home_boss_text;
    private BGABanner homeBanner, homeBanner2;
    private HorizontalScrollView scrollView;
    private TextView home_recommend;
    public static String shareCity;

    private boolean isRefresh = false;


    private MyListView AreaGoodsListView;
    private ItemHomeAreaGoodsAdapter areaGoodsAdapter;

    private LinearLayout addressLocation, horizontalLinear;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenter(this, this);
    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        loadView(view);
        rootView.hintBackView(false);
        setHideHead();
        return view;
    }

    private void loadView(View view) {
        refreshView = (AbPullToRefreshView) view.findViewById(R.id.refresh_view);
        placeName = (TextView) view.findViewById(R.id.root_place);
        refreshView.setOnHeaderRefreshListener(this);
        refreshView.setLoadMoreEnable(false);
        listView = (ListView) view.findViewById(R.id.listview);
        View headView = mInflater.inflate(R.layout.head_home, null);
        headView.findViewById(R.id.good1).setOnClickListener(this);
        headView.findViewById(R.id.good2).setOnClickListener(this);
        headView.findViewById(R.id.good3).setOnClickListener(this);
        headView.findViewById(R.id.home_more).setOnClickListener(this);
        view.findViewById(R.id.root_head_talk).setOnClickListener(this);
        view.findViewById(R.id.root_head_search).setOnClickListener(this);
        view.findViewById(R.id.root_head_share).setOnClickListener(this);
        homeBanner = (BGABanner) headView.findViewById(R.id.home_banner);
        homeBanner2 = (BGABanner) headView.findViewById(R.id.home_banner2);

//        home_boss_text = (TextView) headView.findViewById(R.id.home_boss_text);
        scrollView = (HorizontalScrollView) headView.findViewById(R.id.home_scroll_view);
        horizontalLinear = (LinearLayout) headView.findViewById(R.id.home_boss_good_linear);
        goodName1 = (TextView) headView.findViewById(R.id.good1_name);
        goodName2 = (TextView) headView.findViewById(R.id.good2_name);
        goodName3 = (TextView) headView.findViewById(R.id.good3_name);
        goodPrice1 = (TextView) headView.findViewById(R.id.good1_price);
        goodPrice2 = (TextView) headView.findViewById(R.id.good2_price);
        goodPrice3 = (TextView) headView.findViewById(R.id.good3_price);
        goodImage1 = (ImageView) headView.findViewById(R.id.good1_image);
        goodImage2 = (ImageView) headView.findViewById(R.id.good2_image);
        goodImage3 = (ImageView) headView.findViewById(R.id.good3_image);
        home_recommend = (TextView) headView.findViewById(R.id.home_recommend);

        goodLinear = (LinearLayout) headView.findViewById(R.id.good_linear);
        couponLinear = (LinearLayout) headView.findViewById(R.id.coupon_linear);
        user_shareLinear = (LinearLayout) headView.findViewById(R.id.home_user_share);

        AreaGoodsListView = (MyListView) headView.findViewById(R.id.AreaGoodsList);
        highImage = (ImageView) headView.findViewById(R.id.image_high_url);
        iv_acton = (ImageView) headView.findViewById(R.id.iv_acton);
        listView.addHeaderView(headView);
        listView.setOnItemClickListener(this);
        homePresenter.getInitData(pronvin, true);
//        TextViewUtils.setTextAndrightOther(userName, PreferencesUtils.getString("member_name", "会员"), ",您好");

        addressLocation = (LinearLayout) view.findViewById(R.id.root_left);
        addressLocation.setOnClickListener(this);
        homeBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                HomeBanner.ResultBean.MaterialBean materialBean = (HomeBanner.ResultBean.MaterialBean) model;
                if (position == 0 && materialBean.getEntity() != null) {
                    ImageLoaderUtils.getInstance().display((ImageView) view, materialBean.getEntity().getCouponPic(), R.mipmap.icon_banner_default);
                } else {
                    ImageLoaderUtils.getInstance().display((ImageView) view, Urls.imageUrl + materialBean.getImageUrl(), R.mipmap.icon_banner_default);
                }
            }
        });
        homeBanner2.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                HomeBanner.ResultBean.MaterialBean materialBean = (HomeBanner.ResultBean.MaterialBean) model;
                ImageView imageView = (ImageView) view.findViewById(R.id.iv);


                if (position == 0 && materialBean.getEntity() != null) {

                    ImageLoaderUtils.getInstance().display(imageView, materialBean.getEntity().getCouponPic(), R.mipmap.icon_banner_default);
                } else {
                    ImageLoaderUtils.getInstance().display(imageView, Urls.imageUrl + materialBean.getImageUrl(), R.mipmap.icon_banner_default);
                }

            }
        });
        homeBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
            @Override
            public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
                HomeBanner.ResultBean.MaterialBean materialBean = (HomeBanner.ResultBean.MaterialBean) model;
                if (position == 0 && materialBean.getEntity() != null) {
                    HomeCouponbean.DataEntity.SingleCouponListEntity dataEntity = materialBean.getEntity();
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", dataEntity.getHtmlAddress());
                    intent.putExtra("web_title", dataEntity.getCouponTitle());
                    intent.putExtra("imageUrl", dataEntity.getSharePic() != null ? dataEntity.getSharePic() : dataEntity.getCouponPic());
                    intent.putExtra("coupon_id", dataEntity.getCouponId());
                    getContext().startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    if (materialBean.getUrl().contains("?")) {
                        intent.putExtra("url", materialBean.getUrl() + "&sell_member_id=" + PersonMessagePreferencesUtils.getUid());
                    } else {
                        intent.putExtra("url", materialBean.getUrl() + "?sell_member_id=" + PersonMessagePreferencesUtils.getUid());
                    }
                    intent.putExtra("web_title", materialBean.getTitle());
                    intent.putExtra("imageUrl", Urls.imageUrl + materialBean.getImageUrl());
                    HomeFragment.this.startActivity(intent);
                }
            }
        });
//        homeBanner2.setOnItemClickListener(new BGABanner.OnItemClickListener() {
//            @Override
//            public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
//                HomeBanner.ResultBean.MaterialBean materialBean = (HomeBanner.ResultBean.MaterialBean) model;
//                if (position == 0 && materialBean.getEntity() != null) {
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

        placeName.setText(CommenString.selectCity);

    }

    @Override
    public void onResume() {
        super.onResume();
//        TextViewUtils.setTextAndrightOther(userName, PreferencesUtils.getString("member_name", "会员"), ",您好");
        pronvin = CommenString.selectCity;
        home_recommend.setText(pronvin + "推荐");
        if (!PreferencesUtils.getBoolean("one_go_app", false)) {
            PreferencesUtils.putBoolean("one_go_app", true);
            new SelectCityDialog(this.getContext(), this).show();
        } else {
            if (CommenString.locationState) {
                if (!CommenString.selectCity.equals(PreferencesUtils.getString("select_city", ""))) {
                    new SelectCityDialog(this.getContext(), this).show();
                }
            } else {
                String city = PreferencesUtils.getString("select_city", "");
                if (!city.equals("")) {
                    CommenString.selectCity = city;
                    pronvin = CommenString.selectCity;
                    home_recommend.setText(pronvin + "推荐");
                }
            }
        }
    }

    @Override
    public String setTitleStr() {
        return "顺丰大当家";
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
        isRefresh = true;
        homePresenter.getInitData(pronvin, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            HomeBean.DataEntity.DistrictGoodsListEntity data = (HomeBean.DataEntity.DistrictGoodsListEntity) adapter.getItem(position - 1);
            startGoGoodDetail(data);
        }
    }

    private void startGoGoodDetail(HomeBean.DataEntity.DistrictGoodsListEntity data) {
        Intent intent = new Intent(this.getActivity(), GoodDetailActivity.class);
        intent.putExtra("goodId", data.getGoodsId());
        startActivity(intent);
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        isRefresh = true;
        homePresenter.getInitData(pronvin, false);
    }

    /**
     * 加载首页三个固定商品
     */
    private void loadHomeThreeGoods(HomeBean homeBean, int number, TextView name, TextView price, ImageView image) {
        HomeBean.DataEntity.DistrictGoodsListEntity data = homeBean.getData().getDistrictGoodsList().get(number);
        TextViewUtils.setText(name, data.getGoodsName());
        TextViewUtils.setTextAndScale(price, "￥" + TextViewUtils.sprStringMoney(data.getDelMoneyFinish()), this.getContext().getResources().getDimensionPixelOffset(R.dimen.home_text_view_width));
        ImageLoaderUtils.getInstance().display(image, data.getImg1());
        image.setTag(data);
    }

    private void loadHomeCoupon(List<HomeCouponbean.DataEntity.SingleCouponListEntity> list) {
        couponLinear.removeAllViews();

        if (list != null && list.size() > 0) {
            HomeBanner.ResultBean.MaterialBean beanData = new HomeBanner.ResultBean.MaterialBean();
            beanData.setEntity(list.get(0));
            if (homeBanner.getmModels() != null && homeBanner.getmModels().size() > 0 && !isRefresh) {
                List datas = homeBanner.getmModels();
                datas.add(0, beanData);
                homeBanner.setData(datas, null);
                homeBanner2.setData(R.layout.bgabanner2_item, datas, null);
            } else {
                isRefresh = false;
                List data = new ArrayList();
                data.add(beanData);
                homeBanner.setData(data, null);
                homeBanner2.setData(R.layout.bgabanner2_item, data, null);
            }
        }

        for (int i = 1; i < list.size(); i++) {
            ImageView image = new ImageView(this.getContext());
            SystemConfig.dynamicSetWidthAndHeight(image, -1, 260, 10, 0, 0, 0);
            couponLinear.addView(image);
            ImageLoaderUtils.getInstance().display(image, list.get(i).getCouponPic());
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomeCouponbean.DataEntity.SingleCouponListEntity dataEntity = (HomeCouponbean.DataEntity.SingleCouponListEntity) v.getTag();
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", dataEntity.getHtmlAddress());
                    intent.putExtra("web_title", dataEntity.getCouponTitle());
                    intent.putExtra("imageUrl", dataEntity.getSharePic() != null ? dataEntity.getSharePic() : dataEntity.getCouponPic());
                    intent.putExtra("coupon_id", dataEntity.getCouponId());
                    getContext().startActivity(intent);
                }
            });
            image.setTag(list.get(i));
        }
    }


    @Override
    public void getData(HomeBean homeBean) {
        shareCity = homeBean.getData().getGuanName();
//        goodLinear.setVisibility(View.VISIBLE);
        ImageLoaderUtils.getInstance().display(iv_acton, homeBean.getData().getHighCommissionPicUrl());
        //判断原app 热销专区的显示
//        if (homeBean.getData().getHighCommissionPicUrl() != null) {
//            highImage.setVisibility(View.VISIBLE);
//            ImageLoaderUtils.getInstance().display(highImage, homeBean.getData().getHighCommissionPicUrl());
//            highImage.setOnClickListener(this);
//        } else {
//            highImage.setVisibility(View.GONE);
//        }
//

        if (homeBean.getData().getDistrictGoodsList().size() > 0) {
            loadHomeThreeGoods(homeBean, 0, goodName1, goodPrice1, goodImage1);
        } else {
            goodLinear.setVisibility(View.GONE);
        }
        if (homeBean.getData().getDistrictGoodsList().size() > 1) {
            loadHomeThreeGoods(homeBean, 1, goodName2, goodPrice2, goodImage2);
        } else {
            goodPrice2.setVisibility(View.INVISIBLE);
        }
        if (homeBean.getData().getDistrictGoodsList().size() > 2) {
            loadHomeThreeGoods(homeBean, 2, goodName3, goodPrice3, goodImage3);
        } else {
            goodPrice3.setVisibility(View.INVISIBLE);
        }


        if (homeBean.getData().getPositionAreaGoodsList().size() > 0) {
//            AreaGoodsListView.setVisibility(View.VISIBLE);
            areaGoodsAdapter = new ItemHomeAreaGoodsAdapter(getContext(), homeBean.getData().getPositionAreaGoodsList());
            AreaGoodsListView.setAdapter(areaGoodsAdapter);
        }

        loadHomeCoupon(homeBean.getData().getDistrictCouponList());
        adapter = new ItemHomeGoodAdapter(getContext(), homeBean.getData().getCountryGoodsList());


        List<String> list = new ArrayList<>();
        String pic1 = "timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511257175617&di=f1cbd0f2078a86e1e2a421fd971c2c88&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fd8f9d72a6059252dce8c06ce3d9b033b5ab5b964.jpg";

        list.add(pic1);
        list.add(pic1);
        list.add(pic1);


        UsershareBean usershareBean = new UsershareBean("用户1", "http://avatar.csdn.net/3/B/1/3_wqb1319856391.jpg", "可以，不错！", list);

        List<UsershareBean> beans = new ArrayList<>();
        beans.add(usershareBean);
        beans.add(usershareBean);
        beans.add(usershareBean);

        adapter2 = new ItemHomeUserCommentAdapter(beans, getContext());
        listView.setAdapter(adapter2);
        refreshView.onHeaderRefreshFinish();
//        TextViewUtils.setTextAndrightOther(userName, homeBean.getData().getMemberName(), ",您好");
        PreferencesUtils.putString("member_name", homeBean.getData().getMemberName());


    }

    @Override
    public void getBannerData(HomeBanner data) {
        homeBanner.setVisibility(View.VISIBLE);
        homeBanner2.setVisibility(View.VISIBLE);
//        home_boss_text.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.VISIBLE);
        if (data.getResult() != null && data.getResult().getMaterial() != null) {
            if (homeBanner.getmModels() != null && homeBanner.getmModels().size() > 0 && !isRefresh) {
                List datas = homeBanner.getmModels();
                datas.addAll(data.getResult().getMaterial());
                homeBanner.setData(datas, null);
                homeBanner2.setData(R.layout.bgabanner2_item, datas, null);
            } else {
                isRefresh = false;
                homeBanner.setData(data.getResult().getMaterial(), null);
                homeBanner2.setData(R.layout.bgabanner2_item, data.getResult().getMaterial(), null);
            }
        } else {
            homeBanner.setVisibility(View.GONE);
            homeBanner2.setVisibility(View.GONE);
        }

        horizontalLinear.removeAllViews();
        if (data.getResult() != null && data.getResult().getHotGoods() != null) {
            for (int i = 0; i < data.getResult().getHotGoods().size(); i++) {
                View view = getView(data.getResult().getHotGoods().get(i));
                horizontalLinear.addView(view);
            }
        }
//        user_shareLinear.removeAllViews();
//

//        View view = getView(usershareBean);
//        horizontalLinear.addView(view);
    }

    private View getView(final HomeBanner.ResultBean.HotGoodsBean hotGoodsBean) {
        View view = mInflater.inflate(R.layout.item_home_scroll, null);
        ImageLoaderUtils.getInstance().display((ImageView) view.findViewById(R.id.image), Urls.imageUrl + hotGoodsBean.getGoodsImagePath());
        TextViewUtils.setText((TextView) view.findViewById(R.id.good_name), hotGoodsBean.getName());
        TextViewUtils.setTextAndleftOther((TextView) view.findViewById(R.id.good_price), TextViewUtils.sprStringMoney(hotGoodsBean.getSalePrice(), 2), "￥");
        TextViewUtils.setTextAndleftOther((TextView) view.findViewById(R.id.good_number), hotGoodsBean.getProductName(), "/");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", "http://m.sfddj.com/shop/goods/view/" + hotGoodsBean.getGoodsSn());
                intent.putExtra("web_title", hotGoodsBean.getTitle());
                intent.putExtra("content", hotGoodsBean.getName());
                intent.putExtra("imageUrl", Urls.imageUrl + hotGoodsBean.getGoodsImagePath());
                getContext().startActivity(intent);
            }
        });
        return view;
    }


    //用户分享
    private View getView(UsershareBean usershare) {

        View view = mInflater.inflate(R.layout.item_home_user_im, null);
        ImageLoaderUtils.getInstance().display((ImageView) view.findViewById(R.id.iv_head_pic), usershare.getUser_head());
        TextViewUtils.setText((TextView) view.findViewById(R.id.tv_username), usershare.getUser_name());
        TextViewUtils.setText((TextView) view.findViewById(R.id.tv_user_comment), usershare.getUser_comment());

        return view;
    }

    @Override
    public void getBannerFail() {
//        home_boss_text.setVisibility(View.GONE);
        homeBanner.setVisibility(View.GONE);
        homeBanner2.setVisibility(View.GONE);
        scrollView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.good1:
                HomeBean.DataEntity.DistrictGoodsListEntity data = (HomeBean.DataEntity.DistrictGoodsListEntity) goodImage1.getTag();
                if (data != null)
                    startGoGoodDetail(data);
                break;
            case R.id.good2:
                data = (HomeBean.DataEntity.DistrictGoodsListEntity) goodImage2.getTag();
                if (data != null)
                    startGoGoodDetail(data);
                break;
            case R.id.good3:
                data = (HomeBean.DataEntity.DistrictGoodsListEntity) goodImage3.getTag();
                if (data != null)
                    startGoGoodDetail(data);
                break;
            case R.id.root_left:
                intent = new Intent(this.getActivity(), LocationActivity.class);
                startActivityForResult(intent, 10);
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
            case R.id.root_head_talk:
                startActivity(new Intent(this.getActivity(), SystemNoticeActivity.class));
                break;
            case R.id.root_head_share:
                ShareData shareData = new ShareData(true, "有担当，更爱家", "顺丰大当家-" + shareCity + "生活馆", String.format("%s/Shop/Index/home.html?city=%s&sell_member_id=%s", Urls.IP, pronvin, PersonMessagePreferencesUtils.getUid()));
                new ShareViewDialog(this.getContext(), shareData).show();
                break;
            case R.id.image_high_url:
                startActivity(new Intent(this.getActivity(), SearchHighListActivity.class));
                break;

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10) {
            placeName.setText(data.getStringExtra("city"));
            CommenString.selectCity = data.getStringExtra("city");
            if (!pronvin.equals(CommenString.selectCity)) {
                pronvin = CommenString.selectCity;
                isRefresh = true;
                homePresenter.getInitData(pronvin, true);
            }
        }


    }
}
