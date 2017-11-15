package com.weisj.pj.viewinterface;

import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.bean.VerCodeBean;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public interface IRegisterTwoView {
    // 获取验证码
    String getVerCode();

    String getPhoneNumber();

    void successAgainGetCode(BaseBean baseBean);

    void successVerCode(VerCodeBean baseBean);


}
