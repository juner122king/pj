package com.weisj.pj.viewinterface;

import com.weisj.pj.bean.BaseBean;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public interface IRegisterOneView {
    // 获取手机号
    String getPhoneNumber();

    // 获取员工工号
    String getStaffCode();

    void successGetCode(BaseBean baseBean);

}
