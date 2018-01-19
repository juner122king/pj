package com.weisj.pj.wxapi;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Toast;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.weisj.pj.Constant;
import com.weisj.pj.bean.ComfirmPayCardBean;
import com.weisj.pj.view.choosearea.IDataCallback;

public class WxPayUtils {

    private Activity activity;
    private IWXAPI api;
    private Dialog Notifdialog = null;

    private IDataCallback callback;

    public WxPayUtils(Activity activity, IDataCallback callback) {
        this.activity = activity;
        this.callback = callback;

        api = WXAPIFactory.createWXAPI(activity, Constant.app_wx_appid);

    }


    public WxPayUtils(Activity activity) {
        this.activity = activity;

        api = WXAPIFactory.createWXAPI(activity, Constant.app_wx_appid);

    }

    public void pay(ComfirmPayCardBean.DataEntity wxPayModel) {

        if (!api.isWXAppInstalled()) {
            //提醒用户没有按照微信
            Toast.makeText(activity, "没有安装微信,请先安装微信!", Toast.LENGTH_SHORT).show();
            return;
        }
        sendPayReq(wxPayModel);
    }


//    public void pay(PayBean.DataEntity wxPayModel) {
//        sendPayReq(wxPayModel);
//    }

    //
//
    private void sendPayReq(ComfirmPayCardBean.DataEntity result) {

        PayReq req = new PayReq();
        req.appId = Constant.app_wx_appid;
        req.partnerId = result.getPartnerid();
        req.prepayId = result.getPrepayid();
        req.nonceStr = result.getNoncestr();
        req.timeStamp = result.getTimestamp();
        req.packageValue = result.getPackage_value();
        req.sign = result.getSign();

        api.registerApp(Constant.app_wx_appid);
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        Boolean sendReq = api.sendReq(req);
        if (!sendReq) {
            if (callback != null) {
                callback.callback("Failue");
                Toast.makeText(activity, "支付失败", Toast.LENGTH_LONG).show();
            }
        } else {
            if (callback != null) {
                callback.callback("Success");
            }
        }
    }


//    private void sendPayReq(PayBean.DataEntity result) {
//
//        PayReq req = new PayReq();
//        req.appId = Constant.app_wx_appid;
//        req.partnerId = result.getPartnerid();
//        req.prepayId = result.getPrepayid();
//        req.nonceStr = result.getNoncestr();
//        req.timeStamp = result.getTimestamp();
//        req.packageValue = result.getPackage_value();
//        req.sign = result.getSign();
//
//        api.registerApp(Constant.app_wx_appid);
//        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//        Boolean sendReq = api.sendReq(req);
//        if (!sendReq) {
//            callback.callback("Failue");
//            Toast.makeText(activity, "支付失败", Toast.LENGTH_LONG).show();
//        } else {
//            callback.callback("Success");
//        }
//    }

}
