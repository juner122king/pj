package com.weisj.pj.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.weisj.pj.Constant;
import com.weisj.pj.MyApplication;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;
    private MyApplication app;


    static  int code;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_splash);
        api = WXAPIFactory.createWXAPI(this, Constant.app_wx_appid);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {


        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            code = resp.errCode;
            if (resp.errCode == 0) {
                Toast.makeText(this, "支付成功！", Toast.LENGTH_SHORT).show();

                this.finish();
            } else {
                Toast.makeText(this, "支付失败！", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
    }


    public static int GetBaseResp(){
        return code;
    }
}