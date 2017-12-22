package com.weisj.pj.viewinterface;

import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.bean.VerCodeBean;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public interface IRegisterOneView {
    // 获取手机号
    String getPhoneNumber();

    // 获取验证码
    String getVerCode();

    void successAgainGetCode(BaseBean baseBean);

    void successVerCode(VerCodeBean baseBean);


}
