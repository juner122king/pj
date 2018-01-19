package com.weisj.pj.base.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.squareup.okhttp.Request;
import com.weisj.pj.MainActivity;
import com.weisj.pj.R;
import com.weisj.pj.adapter.CardPagerAdapter;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.base.activity.fragment.OneRegisterFragment;
import com.weisj.pj.base.activity.fragment.ThreeRegisterFragment;
import com.weisj.pj.base.activity.fragment.TwoRegisterFragment;
import com.weisj.pj.base.activity.fragment.VIP1Fragment;
import com.weisj.pj.base.activity.fragment.VIP2Fragment;
import com.weisj.pj.bean.ADBean;
import com.weisj.pj.bean.CardBean;
import com.weisj.pj.bean.CardTypeBean;
import com.weisj.pj.bean.ComfirmPayCardBean;
import com.weisj.pj.bean.ShareData;
import com.weisj.pj.utils.ImageLoaderUtils;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.view.ShadowTransformer;
import com.weisj.pj.view.dialog.SelectCityDialog;
import com.weisj.pj.view.dialog.ShareViewDialog;
import com.weisj.pj.view.dialog.VipJHDialog;
import com.weisj.pj.view.dialog.VipKTDialog;
import com.weisj.pj.view.dialog.VipYHJDialog;
import com.weisj.pj.wxapi.WXPayEntryActivity;
import com.weisj.pj.wxapi.WxPayUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jun on 2017/12/8.
 */

public class VipActivity extends BaseActivity implements View.OnClickListener {
    private Fragment VIP1_fragment, VIP2_fragment;
    private FragmentManager fragmentManager;
    private int number = 0;
    private int zf_type;//支付方式  0 微信， 1 支付宝
    private final static int zb = 1;
    private final static int wx = 0;

    private RadioButton rb_wx, rb_zb, rb1, rb2;
    private RadioGroup rg;
    private CheckBox checkBox;
    private TextView tv1, tv2, tv3, tv4, tv5, tv6;
    private TextView tv_zj21, tv_zj2;
    private TextView container;//支付按钮
    private List<CardTypeBean.DataEntity> cardTypes;
    private List<CardBean.DataEntity> cards;
    private static CardBean.DataEntity JH_card;//待激活的实体卡
    private static CardBean.DataEntity ZF_card;//待支付的实体卡
    public final static int GET_Success = 0;
    public final static int JH_Success = 1;
    private ViewPager viewPager;
    private CardPagerAdapter cardPagerAdapter;
    private ShadowTransformer mCardShadowTransformer;


    @Override

    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_vip, null);

        initView(view);
        getCardType();
        return view;
    }


    @Override
    public String setTitleStr() {
        return "会员卡";
    }

    @Override
    public void getRefreshData() {

    }


    private void initView(final View view) {

        setRightText("送人", true);
        tv1 = (TextView) view.findViewById(R.id.tv_youhui);
        tv2 = (TextView) view.findViewById(R.id.tv_2_2);
        tv3 = (TextView) view.findViewById(R.id.tv_yj);
        tv4 = (TextView) view.findViewById(R.id.tv_yh);
        tv5 = (TextView) view.findViewById(R.id.tv_zj);
        tv6 = (TextView) view.findViewById(R.id.tv_zj2);
        tv_zj21 = (TextView) view.findViewById(R.id.tv_tv_zj21);
        tv_zj2 = (TextView) view.findViewById(R.id.tv_zj2);
        rg = (RadioGroup) view.findViewById(R.id.rg);
        rb1 = (RadioButton) view.findViewById(R.id.rb1);
        rb2 = (RadioButton) view.findViewById(R.id.rb2);
        rg = (RadioGroup) view.findViewById(R.id.rg);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        view.findViewById(R.id.ll_rhj).setOnClickListener(this);
        view.findViewById(R.id.ll_jh).setOnClickListener(this);
        container = (TextView) view.findViewById(R.id.tv_zf);
        container.setOnClickListener(this);

        checkBox = (CheckBox) view.findViewById(R.id.cb);
        rb_wx = (RadioButton) view.findViewById(R.id.rb_wx);
        rb_zb = (RadioButton) view.findViewById(R.id.rb_zb);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View checkView = view.findViewById(checkedId);
                if (!checkView.isPressed()) {
                    return;
                }
                switch (checkedId) {
                    case R.id.rb1:
                        setCardType(cardTypes.get(0));
                        break;
                    case R.id.rb2:
                        setCardType(cardTypes.get(1));
                        break;

                }


            }
        });
        cardPagerAdapter = new CardPagerAdapter();


    }

    private void getCardType() {

        OkHttpClientManager.postAsyn(Urls.getAllCardTypes, null, new OkHttpClientManager.ResultCallback<CardTypeBean>() {
            @Override
            public void onError(Request request, Exception e) {
//                listener.onFail(e, Urls.beginad);
            }

            @Override
            public void onResponse(CardTypeBean response) {
                if (response != null) {
                    cardTypes = response.getData();


                    cardPagerAdapter.setCards(cardTypes);
                    mCardShadowTransformer = new ShadowTransformer(viewPager, cardPagerAdapter);
                    mCardShadowTransformer.enableScaling(true);

                    //滑动VIP卡片
                    viewPager.setAdapter(cardPagerAdapter);
                    viewPager.setPageTransformer(false, mCardShadowTransformer);
                    viewPager.setOffscreenPageLimit(cardTypes.size());
                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {

                            setCardType(cardTypes.get(position));


                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });
                    setCardType(cardTypes.get(0));


                } else {


                }
            }
        });


    }


    //分配一张该类型的卡
    private void setCardType(CardTypeBean.DataEntity cardType) {

        Map<String, String> params = new HashMap<>();


        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("card_type_id", cardType.getCardTypeId());


        OkHttpClientManager.postAsyn(Urls.dispatchOneCardByCardType, params, new OkHttpClientManager.ResultCallback<CardBean>() {
            @Override
            public void onError(Request request, Exception e) {
//                listener.onFail(e, Urls.beginad);
            }

            @Override
            public void onResponse(CardBean response) {
                if (response != null) {
//                    listener.onSuccess(response, Urls.beginad);
                    if (response.getCode().equals("1"))
                        setCardInfo(response.getData(), false);
                    else
                        Toast.makeText(VipActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();

                } else {
//                    listener.onFail(new RuntimeException("null"), Urls.beginad);
                }
            }
        });


    }

    public void setCardInfo(CardBean.DataEntity card, boolean isJh) {

        this.isJh = isJh;
        if (isJh) {
            tv2.setText(String.format("%s", card.getCardNo()));
            container.setText("激活会员卡");
            tv_zj21.setVisibility(View.INVISIBLE);
            tv_zj2.setVisibility(View.INVISIBLE);

        } else {
            ZF_card = card;
            container.setText("去支付");
            tv_zj21.setVisibility(View.VISIBLE);
            tv_zj2.setVisibility(View.VISIBLE);
        }

        tv3.setText(String.format("￥%s", card.getCardMoney()));
        tv4.setText(String.format("-￥%s", card.getCouponMoney()));
        tv5.setText(String.format("￥%s", card.getActualMoney()));
        tv6.setText(String.format("￥%s", card.getActualMoney()));


    }


    @Override
    public void onHeadClick(View v) {
        if (v.getId() == R.id.root_head_right_text) {
            startActivity(new Intent(this, VipSRActivity.class));

        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.tv_zf: //支付按钮
                if (isJh)
                    activeentityCard(JH_card);
                else if (checkBox.isChecked()) {
                    if (rb_wx.isChecked()) {
//                        Toast.makeText(this, "微信支付", Toast.LENGTH_SHORT).show();
//
                        comfirmPayCard(ZF_card, "2");


//                        startActivity(new Intent(VipActivity.this, WXPayEntryActivity.class));


                    } else if (rb_zb.isChecked()) {
                        Toast.makeText(this, "暂不支付宝支付", Toast.LENGTH_SHORT).show();
//                        new VipKTDialog(this, false).show();
                    } else if (checkBox.isChecked())
                        Toast.makeText(this, "请选支付方式", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "请同意协议", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_rhj:
//                Toast.makeText(this, "优惠劵", Toast.LENGTH_SHORT).show();
                new VipYHJDialog(this).show();
                break;

            case R.id.ll_jh:
//                Toast.makeText(this, "激活实体卡", Toast.LENGTH_SHORT).show();
                new VipJHDialog(this, uiHandler).show();
                break;

        }
    }


    //激活实体卡
    private void activeentityCard(CardBean.DataEntity card) {

        Map<String, String> params = new HashMap<>();


        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("card_no", card.getCardNo());
        params.put("card_pass", card.getCardPassword());


        OkHttpClientManager.postAsyn(Urls.activeEntityCard, params, new OkHttpClientManager.ResultCallback<CardBean>() {
            @Override
            public void onError(Request request, Exception e) {
//                listener.onFail(e, Urls.membercenter);
            }

            @Override
            public void onResponse(CardBean response) {
                if (response != null) {
                    String code = response.getCode();
                    if (code.equals("0")) {
                        new VipKTDialog(VipActivity.this, false, uiHandler).show();

                    } else {
                        //弹出成功窗口
                        new VipKTDialog(VipActivity.this, true, uiHandler).show();
                    }
                } else {

//                    listener.onFail(new RuntimeException("null"), Urls.membercenter);
                }
            }
        });


    }

    //确认支付一张虚拟卡
    private void comfirmPayCard(CardBean.DataEntity card, String pay_type) {
        if (null == pay_type) {
            Log.d("确认支付一张虚拟卡》》》", "CardBean.DataEntity == null!");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("card_type_id", String.valueOf(card.getCardTypeId()));
        params.put("card_issue_id", String.valueOf(card.getCardIssueId()));
        params.put("card_num", "1");//数量
        params.put("pay_type", pay_type);//// 1货到付款2微信支付3商城微支付 4、支付宝 5余额支付
        params.put("trade_type", "0");//APP支付，所以传0进来哈
        params.put("openid", "");//openid是微信的OPENID，没有给空字符串
        params.put("agent_id", "0");//是在你获取member数据的时候我传给你的，没有就传0过来
        params.put("is_xcx", "0");//这个参数代表是否小程序 不是就给0


        OkHttpClientManager.postAsyn(Urls.comfirmPayCard, params, new OkHttpClientManager.ResultCallback<ComfirmPayCardBean>() {
            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ComfirmPayCardBean response) {
                if (response != null) {
                    if (response.getCode().equals("0")) {
                        new VipKTDialog(VipActivity.this, false, uiHandler).show();

                    } else {


                        WxPayUtils wxPayUtils = new WxPayUtils(VipActivity.this);
                        wxPayUtils.pay(response.getData());
                        int code = WXPayEntryActivity.GetBaseResp();
                        if (code == 0) {

                        }


                    }
                } else {


                }
            }
        });


    }


    @SuppressLint("HandlerLeak")
    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_Success:
                    Toast.makeText(VipActivity.this, "成功获取实体卡", Toast.LENGTH_SHORT).show();
                    JH_card = (CardBean.DataEntity) msg.obj;
                    setCardInfo(JH_card, true);
                    break;
                case JH_Success:
                    Toast.makeText(VipActivity.this, "成功激活实体卡", Toast.LENGTH_SHORT).show();
                    finish();
//                    setResult(2,intent );


                    break;

            }
        }
    };

    private boolean isJh = false;

}
