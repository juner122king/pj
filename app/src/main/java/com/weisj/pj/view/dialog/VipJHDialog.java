package com.weisj.pj.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.weisj.pj.R;
import com.weisj.pj.base.activity.VipActivity;
import com.weisj.pj.bean.CardBean;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jun on 2017/12/9.
 */

public class VipJHDialog extends AlertDialog implements View.OnClickListener {
    private Activity context;

    private TextView tv_miss;
    private EditText et_mima, tv_kahao;
    private Handler uiHandler;

    public VipJHDialog(Activity context, Handler uiHandler) {
        super(context, R.style.dialog);
        this.context = context;
        this.uiHandler = uiHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        layout();
    }


    private void init() {
        setContentView(R.layout.dialog_vip_jh);
        findViewById(R.id.tv_caler).setOnClickListener(this);
        findViewById(R.id.tv_ent).setOnClickListener(this);
        tv_kahao = (EditText) findViewById(R.id.text_kahao);
        tv_miss = (TextView) findViewById(R.id.tv_miss);
        et_mima = (EditText) findViewById(R.id.et_mima);


    }

    private void layout() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (SystemConfig.getScreenWidth() * 0.8);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//弹出键盘
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.tv_caler:
                this.cancel();
                break;
            case R.id.tv_ent:


                entityCard();

                break;
        }
    }


    //判断实体卡
    private void entityCard() {

        Map<String, String> params = new HashMap<>();


        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("card_no", tv_kahao.getText().toString());


        OkHttpClientManager.postAsyn(Urls.getEntityCardInfo, params, new OkHttpClientManager.ResultCallback<CardBean>() {
            @Override
            public void onError(Request request, Exception e) {
//                listener.onFail(e, Urls.membercenter);
            }

            @Override
            public void onResponse(CardBean response) {
                if (response != null) {
//                   listener.onSuccess(response, Urls.membercenter);
                    String code = response.getCode();
                    if (code.equals("0")) {
                        tv_miss.setVisibility(View.VISIBLE);
                        tv_miss.setText(response.getMsg());
                    } else {


                        Message msg = new Message();
                        msg.what = VipActivity.GET_Success;
                        msg.obj = response.getData();
                        uiHandler.sendMessage(msg);
                        cancel();

                    }
                } else {

//                    listener.onFail(new RuntimeException("null"), Urls.membercenter);
                }
            }
        });


    }

}
