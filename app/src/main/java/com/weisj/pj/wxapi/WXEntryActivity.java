
package com.weisj.pj.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.weisj.pj.Constant;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {


    private IWXAPI wxAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wxAPI = WXAPIFactory.createWXAPI(this,Constant.app_wx_appid,true);
        wxAPI.registerApp(Constant.app_wx_appid);
        wxAPI.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        wxAPI.handleIntent(getIntent(),this);
        Log.i("ansen","WXEntryActivity onNewIntent");
    }

    @Override
    public void onReq(BaseReq arg0) {
        Log.i("ansen","WXEntryActivity onReq:"+arg0);
    }

    @Override
    public void onResp(BaseResp resp){
        if(resp.getType()== ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX){//·ÖÏí
            Log.i("ansen","Î¢ÐÅ·ÖÏí²Ù×÷.....");

        }else if(resp.getType()==ConstantsAPI.COMMAND_SENDAUTH){//µÇÂ½
            Log.i("ansen", "Î¢ÐÅµÇÂ¼²Ù×÷.....");
            SendAuth.Resp authResp = (SendAuth.Resp) resp;
//            getAccessToken(authResp.code);

        }
        finish();
    }
    public void getAccessToken(String code){
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid="+Constant.app_wx_appid+"&secret="+Constant.app_wx_secret_key+
                "&code="+code+"&grant_type=authorization_code";


    }
}
