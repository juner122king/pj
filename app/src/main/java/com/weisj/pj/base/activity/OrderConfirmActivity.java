package com.weisj.pj.base.activity;

import com.squareup.okhttp.Request;
import com.weisj.pj.R;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.bean.AdressBean;
import com.weisj.pj.bean.CardBean;
import com.weisj.pj.bean.ComfirmPayCardBean;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.view.dialog.VipKTDialog;
import com.weisj.pj.wxapi.WXPayEntryActivity;
import com.weisj.pj.wxapi.WxPayUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * 确认订单
 * Created by jun on 2018/1/20.
 */

public class OrderConfirmActivity extends BaseActivity implements View.OnClickListener {
    View view, lladd, notadd;
    AdressBean.DataEntity dataEntity;
    EditText editText;
    RadioButton rb_wx, rb_zf;
    int add_size;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_zf:
                if (add_size == 0) {
                    Toast.makeText(OrderConfirmActivity.this, "您还没有添加地址，请添加", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (rb_wx.isChecked())
                    orderconfirm("2");
                else if (rb_zf.isChecked())
                    orderconfirm("4");
                else
                    Toast.makeText(OrderConfirmActivity.this, "请选择一种支付方式", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ll_add:

                startActivity(new Intent(OrderConfirmActivity.this, ConsigneeAddressActivity.class));
                break;
        }


    }


    @Override
    protected void onResume() {
        super.onResume();

        getAddress();
    }

    @Override
    public View initView(Bundle savedInstanceState) {
        view = mLayoutInflater.inflate(R.layout.activity_order_confirm, null);

        initView();


        return view;
    }

    private void initView() {


        view.findViewById(R.id.tv_zf).setOnClickListener(this);
        view.findViewById(R.id.ll_add).setOnClickListener(this);
        editText = (EditText) view.findViewById(R.id.tv_2_2);
        rb_wx = (RadioButton) view.findViewById(R.id.rb_wx);
        rb_zf = (RadioButton) view.findViewById(R.id.rb_zb);
        lladd = view.findViewById(R.id.lladd);
        notadd = view.findViewById(R.id.tv_notadd);
        ((TextView) view.findViewById(R.id.tv_zj2)).setText("￥" + getIntent().getStringExtra("yj"));

    }

    @Override
    public String setTitleStr() {
        return "确认订单";
    }

    @Override
    public void getRefreshData() {

    }


    //确认支付租商品
    private void orderconfirm(String pay_type) {
        if (null == pay_type) {
            Log.d("确认支付租商品》》》", "pay_type == null!");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("cart_id", getIntent().getStringExtra("cartId"));
        params.put("recipient_id", String.valueOf(dataEntity.getRecipient_id()));//地址
        params.put("leave_message", editText.getText().toString());//留言
        params.put("pay_type", pay_type);//// 1货到付款2微信支付3商城微支付 4、支付宝 5余额支付
        params.put("trade_type", "0");//APP支付，所以传0进来哈
        params.put("openid", "");//openid是微信的OPENID，没有给空字符串
//        params.put("agent_id", "0");//是在你获取member数据的时候我传给你的，没有就传0过来
        params.put("is_xcx", "0");//这个参数代表是否小程序 不是就给0
        params.put("deposit", "0.02");//商品押金


        OkHttpClientManager.postAsyn(Urls.orderconfirm, params, new OkHttpClientManager.ResultCallback<ComfirmPayCardBean>() {
            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ComfirmPayCardBean response) {
                if (response != null) {

                    if (response.getCode().equals("0")) {
                        Toast.makeText(OrderConfirmActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                    } else {
                        WxPayUtils wxPayUtils = new WxPayUtils(OrderConfirmActivity.this);
                        wxPayUtils.pay(response.getData());
                        finish();

                    }
                }
            }
        });

    }

    private void getAddress() {

        Map<String, String> params = new HashMap<>();
        params.put("member", PersonMessagePreferencesUtils.getUid());
        OkHttpClientManager.postAsyn(Urls.getAddress, params, new OkHttpClientManager.ResultCallback<AdressBean>() {
            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(AdressBean response) {
                if (response.getCode().equals("1")) {
                    if (null != response.getData() && response.getData().size() > 0) {
                        add_size = response.getData().size();
                        if (response.getData().size() > 0) {
                            lladd.setVisibility(View.VISIBLE);
                            notadd.setVisibility(View.GONE);
                            dataEntity = response.getData().get(0);
                            showAdress();

                        } else {
                            lladd.setVisibility(View.GONE);
                            notadd.setVisibility(View.VISIBLE);

                        }


                    }
                }

            }
        });

    }


    private void showAdress() {

        ((TextView) view.findViewById(R.id.consignee_name)).setText(dataEntity.getRecipients());
        ((TextView) view.findViewById(R.id.consignee_phone)).setText(dataEntity.getPhone());
        ((TextView) view.findViewById(R.id.consignee_address)).setText(dataEntity.getProvince() + dataEntity.getCity() + dataEntity.getArea() + dataEntity.getAddress());


    }


}
