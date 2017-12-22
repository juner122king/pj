package com.weisj.pj.base.activity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.weisj.pj.R;
import com.weisj.pj.base.BaseFragment;
import com.weisj.pj.base.activity.RegisterActivity;
import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.bean.VerCodeBean;
import com.weisj.pj.presenter.RegisterTwoPresenter;
import com.weisj.pj.utils.KeyboardUtil;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.viewinterface.IRegisterTwoView;


/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class TwoRegisterFragment extends BaseFragment implements View.OnClickListener, IRegisterTwoView {
    private EditText psw, psw2;
    private RegisterTwoPresenter presenter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView.isHintHeadBar(true);
        View view = inflater.inflate(R.layout.fragment_two_register, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.register).setOnClickListener(this);
        psw = (EditText) view.findViewById(R.id.psw);
        psw2 = (EditText) view.findViewById(R.id.psw2);
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
            case R.id.register:
                //注册


                break;
        }
    }


    @Override
    public String getPhoneNumber() {
        return ((RegisterActivity) getActivity()).phone;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
