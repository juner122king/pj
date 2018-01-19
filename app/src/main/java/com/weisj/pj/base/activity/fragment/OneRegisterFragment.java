package com.weisj.pj.base.activity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.weisj.pj.R;
import com.weisj.pj.base.BaseFragment;
import com.weisj.pj.base.activity.RegisterActivity;
import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.bean.VerCodeBean;
import com.weisj.pj.presenter.RegisterOnePresenter;
import com.weisj.pj.utils.KeyboardUtil;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.viewinterface.IRegisterOneView;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class OneRegisterFragment extends BaseFragment implements View.OnClickListener, IRegisterOneView {
    private EditText phone, staff_number;
    private RegisterOnePresenter presenter;
    private TextView tv_reset_time;//发送验证码

    private int time = 120;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tv_reset_time.setText("重新发送" + "(" + time + "秒)");
                    if (time == 0) {
                        tv_reset_time.setClickable(true);
                    } else {
                        time--;
                        if (handler != null)
                            handler.sendEmptyMessageDelayed(1, 1000);
                    }
                    break;
            }
        }
    };

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView.isHintHeadBar(true);
        View view = inflater.inflate(R.layout.fragment_one_register, null);

        initView(view);

        return view;
    }

    private void initView(View view) {
        phone = (EditText) view.findViewById(R.id.phone);
        staff_number = (EditText) view.findViewById(R.id.staff_number);
        tv_reset_time = (TextView) view.findViewById(R.id.tv_reset_time);

        view.findViewById(R.id.nextBt).setOnClickListener(this);
        view.findViewById(R.id.tv_reset_time).setOnClickListener(this);


    }

    @Override
    public String setTitleStr() {
        return "";
    }

    @Override
    public void getRefreshData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextBt:
                if (getActivity() instanceof RegisterActivity) {
                    KeyboardUtil.closeKeyBoard(getActivity());
                    if (presenter == null) {
                        presenter = new RegisterOnePresenter(this, this);
                    }
                    presenter.verCode();
                }
                break;


            case R.id.tv_reset_time:

                if (presenter == null) {
                    presenter = new RegisterOnePresenter(this, this);
                }
                presenter.againGetCode();
                break;
        }
    }

    @Override
    public String getPhoneNumber() {
        return phone.getText().toString();
    }

    @Override
    public String getVerCode() {
        return getCode();
    }

    public String getCode() {
        return staff_number.getText().toString();
    }

    @Override
    public void successAgainGetCode(BaseBean baseBean) {
        if (SystemConfig.isGetNetSuccess(baseBean)) {
            SystemConfig.showToast("成功发送到手机");
            clickGetCode();
        }
    }

    // 获取验证码前的电话号码非空判断和计时器的启动
    private void clickGetCode() {
        time = 120;
        tv_reset_time.setClickable(false);
        tv_reset_time.setText("发送验证码");
        if (handler != null)
            handler.sendEmptyMessage(1);
    }

    @Override
    public void successVerCode(VerCodeBean baseBean) {
        if ("1".equals(baseBean.getCode())) {
            ((RegisterActivity) getActivity()).vCode = baseBean.getData().getTicket_code();
            ((RegisterActivity) getActivity()).phone = phone.getText().toString();
            ((RegisterActivity) getActivity()).changeFragment(2);

        } else {
            SystemConfig.showToast(baseBean.getMsg());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
        handler = null;
    }
}
