package com.weisj.pj.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.weisj.pj.R;
import com.weisj.pj.bean.CenterBean;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.utils.Utility;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jun on 2017/12/9.
 */

public class ApplyAgentDialog extends AlertDialog implements View.OnClickListener {
    private Context context;

    private EditText tv_kahao;
    private Handler uiHandler;

    public ApplyAgentDialog(Activity context, Handler uiHandler) {
        super(context, R.style.dialog);
        this.context = context;
        this.uiHandler = uiHandler;
    }

    public ApplyAgentDialog(Context context) {
        super(context, R.style.dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        layout();
    }


    private void init() {
        setContentView(R.layout.dialog_applyagent);
        findViewById(R.id.tv_caler).setOnClickListener(this);
        findViewById(R.id.tv_ent).setOnClickListener(this);
        tv_kahao = (EditText) findViewById(R.id.text_kahao);


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
                String phone = tv_kahao.getText().toString();
                if (Utility.isMobile(phone))
                    applyAgent(phone);
                else
                    SystemConfig.showToast("请输入正确的手机号码");


                break;
        }
    }

    public void applyAgent(String cellphone) {
        cancel();
        Map<String, String> params = new HashMap<>();
        if (PersonMessagePreferencesUtils.getUid() == null) {
            return;
        }
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("cellphone", cellphone);
        OkHttpClientManager.postAsyn(Urls.applyAgent, params, new OkHttpClientManager.ResultCallback<CenterBean>() {
            @Override
            public void onError(Request request, Exception e) {
                Toast.makeText(context, "申请失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(CenterBean response) {

                Toast.makeText(context, response.getMsg(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}