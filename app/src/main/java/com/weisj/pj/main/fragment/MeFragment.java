package com.weisj.pj.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.weisj.pj.R;
import com.weisj.pj.base.BaseFragment;
//import com.weisj.pj.base.activity.BecomeShopActivity;
//import com.weisj.pj.base.activity.BingPhoneActivity;
//import com.weisj.pj.base.activity.SearchListActivity.ConsigneeAddressActivity;
//import com.weisj.pj.base.activity.ListViewActivity;
//import com.weisj.pj.base.activity.MyCollectionActivity;
//import com.weisj.pj.base.activity.SetUpActivity;
//import com.weisj.pj.base.activity.SystemNoticeActivity;
//import com.weisj.pj.base.activity.UserInfoActivity;
//import com.weisj.pj.base.activity.WalletActivity;
//import com.weisj.pj.base.activity.WebActivity;
import com.weisj.pj.base.activity.AgentOrderActivity;
import com.weisj.pj.base.activity.ConsigneeAddressActivity;
import com.weisj.pj.base.activity.LoginActivity;
import com.weisj.pj.base.activity.MyCardActivity;
import com.weisj.pj.base.activity.UserInfoActivity;
import com.weisj.pj.base.activity.VipActivity;
import com.weisj.pj.base.activity.WalletActivity;
import com.weisj.pj.bean.CenterBean;
import com.weisj.pj.presenter.CenterPresenter;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.PreferencesUtils;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.view.dialog.ApplyAgentDialog;
import com.weisj.pj.viewinterface.ICenterView;

/**
 * Created by Administrator on 2016/6/27 0027.
 * 我的主页
 */
public class MeFragment extends BaseFragment implements View.OnClickListener, ICenterView {
    private View view, view_dl;
    private CenterPresenter presenter;
    private final String GETHINTIMAGECLICK = "isClickImage";
    private ImageView iv_head;
    private TextView name, user_lv, user_lv_info, numb1, numb2, numb3, numb4, logout, tv_day;
    private View v_daili, ll_day, ll_date;

    private LinearLayout ll4, ll5, ll6;

    CenterBean.DataEntity dataEntity;
    String user_head_imag;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, null);
        initView();
        rootView.isHintHeadBar(true);
        presenter = new CenterPresenter(this, this);
        return view;
    }

    private void initView() {
        iv_head = (ImageView) view.findViewById(R.id.image_head);
        view_dl = view.findViewById(R.id.view_dl);
        view.findViewById(R.id.user_address_linear).setOnClickListener(this);
        view.findViewById(R.id.tv_logout).setOnClickListener(this);

        name = (TextView) view.findViewById(R.id.tv_name);
        numb1 = (TextView) view.findViewById(R.id.tv_number1);
        numb2 = (TextView) view.findViewById(R.id.tv_number2);
        numb3 = (TextView) view.findViewById(R.id.tv_number3);
        numb4 = (TextView) view.findViewById(R.id.tv_number4);
        logout = (TextView) view.findViewById(R.id.tv_logout);
        ll4 = (LinearLayout) view.findViewById(R.id.ll4);
        ll5 = (LinearLayout) view.findViewById(R.id.ll5);
        ll6 = (LinearLayout) view.findViewById(R.id.ll6);

        ll_day = view.findViewById(R.id.ll_day);
        v_daili = view.findViewById(R.id.tv_daili);
        ll_date = view.findViewById(R.id.ll_date);
        user_lv = (TextView) view.findViewById(R.id.loginBt);
        tv_day = (TextView) view.findViewById(R.id.tv_day);

        user_lv_info = (TextView) view.findViewById(R.id.tv_vip_info);
        iv_head.setOnClickListener(this);
        user_lv.setOnClickListener(this);
        v_daili.setOnClickListener(this);
        ll4.setOnClickListener(this);
        ll5.setOnClickListener(this);
        ll6.setOnClickListener(this);


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
        switch (v.getId()) {
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
            case R.id.user_address_linear:
                startActivity(new Intent(this.getContext(), ConsigneeAddressActivity.class));
                break;
            case R.id.tv_logout:
                PersonMessagePreferencesUtils.deleteInfo();

                startActivity(new Intent(this.getActivity(), LoginActivity.class));
                break;
//            case R.id.user_share:
//                ShareData data = new ShareData(BitmapFactory.decodeResource(this.getResources(), R.mipmap.icon_share_sf), "顺丰大当家，分享每一刻", "顺丰大当家，分享每一刻", Urls.IP + "/Shop/MDownload/down_load", 0, 0);
//                new LinkShareDialog(this.getContext(), data).show();
//                break;
//            case R.id.notice_image:
//                startActivity(new Intent(this.getContext(), SystemNoticeActivity.class));
//                break;
            case R.id.ll4:
                startActivity(new Intent(this.getContext(), MyCardActivity.class));
                break;

            case R.id.ll5:
                startActivity(new Intent(this.getContext(), WalletActivity.class));
                break;

            case R.id.ll6:
                Intent intent2 = new Intent(this.getContext(), AgentOrderActivity.class);
                intent2.putExtra("agent_id", dataEntity.getAgent_id());

                startActivity(intent2);
                break;

            case R.id.image_head:
                if (dataEntity != null) {
                    PreferencesUtils.putBoolean(GETHINTIMAGECLICK, true);
                    Intent intent = new Intent(this.getContext(), UserInfoActivity.class);
//                    intent.putExtra("member_pic",dataEntity.getMember_pic());
                    intent.putExtra("member_pic", user_head_imag);
                    intent.putExtra("sex", dataEntity.getSex());
                    intent.putExtra("member_name", dataEntity.getMember_name());
                    startActivity(intent);
                }
                break;
//            case R.id.user_jc:
//                startActivity(new Intent(this.getContext(), ListViewActivity.class));
//                break;
            case R.id.loginBt:
                startActivity(new Intent(this.getContext(), VipActivity.class));
                break;
            case R.id.tv_daili:
//                Toast.makeText(this.getContext(), "您的申请已提交，耐心等候管理员审核", Toast.LENGTH_SHORT).show();

                new ApplyAgentDialog(view.getContext()).show();
                break;

        }
    }


    @Override
    public void getCenter(CenterBean centerBean) {
        dataEntity = centerBean.getData();
        name.setText(dataEntity.getMember_name());

        numb1.setText(String.valueOf(dataEntity.getCollec_num()));
        numb2.setText(dataEntity.getCurrent_money());
        numb3.setText(String.valueOf(dataEntity.getCupon_num()));
        numb4.setText(String.valueOf(dataEntity.getCard_num()));


        Glide.with(getActivity())
                .load(Urls.IP + dataEntity.getMember_pic())
                .into(iv_head);

        user_head_imag = Urls.IP + dataEntity.getMember_pic();

        if (dataEntity.getGroup_id() == 1) {
            user_lv_info.setText("会员");
            user_lv.setText("续费");
            user_lv.setVisibility(View.INVISIBLE);
            v_daili.setVisibility(View.VISIBLE);
            ll_day.setVisibility(View.VISIBLE);
            tv_day.setText(String.valueOf(dataEntity.getLeft_days()));
            ll_date.setVisibility(View.VISIBLE);
            view_dl.setVisibility(View.GONE);

        } else if (dataEntity.getGroup_id() == 0) {
            user_lv_info.setText("您还不是会员");
            user_lv.setText("成为包月会员");
            user_lv.setVisibility(View.VISIBLE);
            v_daili.setVisibility(View.INVISIBLE);
            ll_day.setVisibility(View.INVISIBLE);
//            view_dl.setVisibility(View.INVISIBLE);
            ll_date.setVisibility(View.INVISIBLE);
            view_dl.setVisibility(View.GONE);
        } else if (dataEntity.getGroup_id() == 2) {
            user_lv_info.setText("您是代理商");
            user_lv.setVisibility(View.INVISIBLE);
            v_daili.setVisibility(View.INVISIBLE);
            ll_day.setVisibility(View.VISIBLE);
            tv_day.setText(String.valueOf(dataEntity.getLeft_days()));
            ll_date.setVisibility(View.VISIBLE);
            view_dl.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void showLvlInfo(String s) {


    }

    @Override
    public void onResume() {
        super.onResume();
        if (PersonMessagePreferencesUtils.getUid() == null) {
            getActivity().finish();
        } else {
            presenter.getMemberCenter();
        }
//        if (PreferencesUtils.getBoolean(GETHINTIMAGECLICK)) {
//            imageHeadHint.setVisibility(View.GONE);
//        } else {
//            imageHeadHint.setVisibility(View.VISIBLE);
//        }
    }
}
