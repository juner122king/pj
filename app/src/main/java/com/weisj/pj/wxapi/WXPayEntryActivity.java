package com.weisj.pj.wxapi;


import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.weisj.pj.Constants;
import com.weisj.pj.bean.ComfirmPayCardBean;
import com.weisj.pj.utils.WXPayUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        testWxPay((ComfirmPayCardBean) getIntent().getSerializableExtra("ComfirmPayCardBean"));

    }


    public void testWxPay(final ComfirmPayCardBean response) {

        if (null == response)
            return;

        WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
        builder.setAppId(Constants.APP_ID)
                .setPartnerId(response.getData().getPartnerid())
                .setPrepayId(response.getData().getPrepayid())
                .setPackageValue(response.getData().getPackage_value())
                .setNonceStr(response.getData().getNoncestr())
                .setTimeStamp(response.getData().getTimestamp())
                .setSign(response.getData().getSign())
                .build().toWXPayNotSign(this, Constants.APP_ID);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onReq(BaseReq req) {
        Log.d("微信支付回调》》req》", "req, req = " + req.toString());
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("微信支付回调》》resp》", "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                Toast.makeText(getApplicationContext(), "支付成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "支付失败,请重试", Toast.LENGTH_SHORT).show();
            }

            finish();

        }
    }
}