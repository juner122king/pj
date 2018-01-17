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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class
WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;
    PayReq req;
    ComfirmPayCardBean bean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, null);
        api.registerApp(Constants.APP_ID);
        if (!api.isWXAppInstalled()) {
            //提醒用户没有按照微信
            Toast.makeText(getApplicationContext(), "没有安装微信,请先安装微信!", Toast.LENGTH_SHORT).show();
            return;
        }

        bean = (ComfirmPayCardBean) getIntent().getSerializableExtra("ComfirmPayCardBean");
        testWxPay();

    }

    public void testWxPay() {

        Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
                req = new PayReq();
                req.appId = Constants.APP_ID;
                req.partnerId = bean.getData().getPartnerid();
                req.prepayId = bean.getData().getPrepayid();
                req.nonceStr = bean.getData().getNoncestr();
                req.timeStamp = bean.getData().getTimestamp();
                req.packageValue = bean.getData().getPackage_value();
                req.sign = bean.getData().getSign();
                req.extData = "app data"; // optional

                api.sendReq(req);

            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);

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