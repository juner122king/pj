package com.weisj.pj.main.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weisj.pj.R;
import com.weisj.pj.base.BaseFragment;
//import com.weisj.pj.base.activity.BecomeShopActivity;
//import com.weisj.pj.base.activity.BingPhoneActivity;
//import com.weisj.pj.base.activity.ConsigneeAddressActivity;
//import com.weisj.pj.base.activity.ListViewActivity;
//import com.weisj.pj.base.activity.MyCollectionActivity;
//import com.weisj.pj.base.activity.SetUpActivity;
//import com.weisj.pj.base.activity.SystemNoticeActivity;
//import com.weisj.pj.base.activity.UserInfoActivity;
//import com.weisj.pj.base.activity.WalletActivity;
//import com.weisj.pj.base.activity.WebActivity;
import com.weisj.pj.bean.CenterBean;
import com.weisj.pj.bean.ShareData;
import com.weisj.pj.presenter.CenterPresenter;
import com.weisj.pj.utils.ImageLoaderUtils;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.PreferencesUtils;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.view.dialog.LinkShareDialog;
import com.weisj.pj.viewinterface.ICenterView;

/**
 * Created by Administrator on 2016/6/27 0027.
 * 我的主页
 */
public class MeFragment extends BaseFragment implements View.OnClickListener, ICenterView {
    private View view;
    private CenterPresenter presenter;
    private CenterBean centerBean;
    private View imageHeadHint;
    private final String GETHINTIMAGECLICK = "isClickImage";

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        initView(view);
        rootView.isHintHeadBar(true);
        this.view = view;
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.me_wallet).setOnClickListener(this);
        view.findViewById(R.id.my_collection_linear).setOnClickListener(this);
        view.findViewById(R.id.me_become_shop_linear).setOnClickListener(this);
        view.findViewById(R.id.user_address_linear).setOnClickListener(this);
        view.findViewById(R.id.user_share).setOnClickListener(this);
        view.findViewById(R.id.notice_image).setOnClickListener(this);
        view.findViewById(R.id.user_setup).setOnClickListener(this);
        view.findViewById(R.id.image_head).setOnClickListener(this);
        view.findViewById(R.id.user_jc).setOnClickListener(this);
        view.findViewById(R.id.share_pingan).setVisibility(View.GONE);
        imageHeadHint = view.findViewById(R.id.image_head_hint);
        presenter = new CenterPresenter(this, this);

    }

    @Override
    public String setTitleStr() {
        return "Found";
    }

    @Override
    public void getRefreshData() {
        presenter.getMemberCenter();
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.me_wallet:
//                if (PreferencesUtils.getString("cellphone") != null && PreferencesUtils.getString("cellphone").length() == 11) {
//                    startActivity(new Intent(this.getContext(), WalletActivity.class));
//                } else {
//                    startActivity(new Intent(this.getContext(), BingPhoneActivity.class));
//                }
//                break;
//            case R.id.my_collection_linear:
//                startActivity(new Intent(this.getContext(), MyCollectionActivity.class));
//                break;
//            case R.id.me_become_shop_linear:
//                startActivity(new Intent(this.getContext(), BecomeShopActivity.class));
//                break;
//            case R.id.user_address_linear:
//                startActivity(new Intent(this.getContext(), ConsigneeAddressActivity.class));
//                break;
//            case R.id.user_share:
//                ShareData data = new ShareData(BitmapFactory.decodeResource(this.getResources(), R.mipmap.icon_share_sf), "顺丰大当家，分享每一刻", "顺丰大当家，分享每一刻", Urls.IP + "/Shop/MDownload/down_load", 0, 0);
//                new LinkShareDialog(this.getContext(), data).show();
//                break;
//            case R.id.notice_image:
//                startActivity(new Intent(this.getContext(), SystemNoticeActivity.class));
//                break;
//            case R.id.user_setup:
//                startActivity(new Intent(this.getContext(), SetUpActivity.class));
//                break;
//            case R.id.image_head:
//                if (centerBean != null) {
//                    PreferencesUtils.putBoolean(GETHINTIMAGECLICK, true);
//                    Intent intent = new Intent(this.getContext(), UserInfoActivity.class);
//                    intent.putExtra("member_pic", centerBean.getData().getMember_pic());
//                    intent.putExtra("sex", centerBean.getData().getSex());
//                    intent.putExtra("member_name", centerBean.getData().getMember_name());
//                    intent.putExtra("district_id", centerBean.getData().getDistrict_id());
//                    intent.putExtra("staff_id", centerBean.getData().getStaff_id());
//                    startActivity(intent);
//                }
//                break;
//            case R.id.user_jc:
//                startActivity(new Intent(this.getContext(), ListViewActivity.class));
//                break;
//
//            case R.id.share_pingan:
//                Intent intent = new Intent(this.getContext(), WebActivity.class);
//                intent.putExtra("web", "https://bank-static-stg.pingan.com.cn/bbc/index/index.html?mchId=B170700601&entrance=01&accessType=0");
//                startActivity(intent);
//                break;
//
//        }
    }

    @Override
    public void getCenter(CenterBean centerBean) {
        this.centerBean = centerBean;
        ImageLoaderUtils.getInstance().display((ImageView) (view.findViewById(R.id.image_head)), centerBean.getData().getMember_pic(), R.mipmap.icon_head);
        ((TextView) (view.findViewById(R.id.user_name))).setText(centerBean.getData().getMember_name() != null ? centerBean.getData().getMember_name() : "");
        PreferencesUtils.putString("member_name", centerBean.getData().getMember_name());
        ((TextView) (view.findViewById(R.id.user_money))).setText(SystemConfig.moneymulti(centerBean.getData().getCurrent_money()));
        ((TextView) (view.findViewById(R.id.user_point))).setText(String.valueOf(centerBean.getData().getPoint()));
        ((TextView) (view.findViewById(R.id.user_collec))).setText(String.valueOf(centerBean.getData().getCupon_num()));
        ((TextView) (view.findViewById(R.id.user_coupon))).setText(String.valueOf(centerBean.getData().getCupon_num()) + "张");
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (PersonMessagePreferencesUtils.getUid() == null) {
//            getActivity().finish();
//        } else {
//            presenter.getMemberCenter();
//        }
//        if (PreferencesUtils.getBoolean(GETHINTIMAGECLICK)) {
//            imageHeadHint.setVisibility(View.GONE);
//        } else {
//            imageHeadHint.setVisibility(View.VISIBLE);
//        }
    }
}
