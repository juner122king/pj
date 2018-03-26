package com.weisj.pj.base.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.weisj.pj.R;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.Urls;

import java.util.HashMap;
import java.util.Map;

public class HuanhuoActivity extends BaseActivity implements View.OnClickListener {
    View view;
    EditText tv_2_2;
    CheckBox checkBox;
    String order_id, consignee_id;

    @Override
    public View initView(Bundle savedInstanceState) {
        view = mLayoutInflater.inflate(R.layout.activity_huanhuo, null);

        initView();


        return view;
    }


    private void initView() {

        view.findViewById(R.id.tv_zf).setOnClickListener(this);


        tv_2_2 = (EditText) view.findViewById(R.id.tv_2_2);
        checkBox = (CheckBox) view.findViewById(R.id.cb);


        order_id = getIntent().getStringExtra("order_id");
        consignee_id = getIntent().getStringExtra("consignee_id");


    }

    @Override
    public String setTitleStr() {
        return "还货";
    }

    @Override
    public void getRefreshData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_zf:

                if (tv_2_2.getText().toString().isEmpty()) {
                    Toast.makeText(HuanhuoActivity.this, "请填写快递单号", Toast.LENGTH_SHORT).show();
                    return;
                }

                String express_no = tv_2_2.getText().toString();

                String is_balance;

                if (checkBox.isChecked())
                    is_balance = "1";
                else
                    is_balance = "0";


                returnJewel(express_no, is_balance);


                break;
        }
    }


    private void returnJewel(String express_no, String is_balance) {

        Map<String, String> params = new HashMap<>();
        params.put("order_id", order_id);//订单号
        params.put("express_company_code", "shunfeng");//写死顺丰
        params.put("express_no", express_no);//快递单号
        params.put("consignee_id", consignee_id);//上级页面id
        params.put("is_balance", is_balance);//还货后押金是否退至用户余额，1是，0不是）

        OkHttpClientManager.postAsyn(Urls.returnJewel, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(BaseBean response) {
                if (response != null && response.getCode().equals("1")) {

                    Toast.makeText(HuanhuoActivity.this, "提交还货单成功！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
