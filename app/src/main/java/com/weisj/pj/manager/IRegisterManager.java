package com.weisj.pj.manager;

import com.weisj.pj.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public interface IRegisterManager {
    void registerNumber(String phone, String password, String vCode, String staffCode, String staffCity, IOnManagerListener listener);

    void forgetPass(String phone, String password, String vCode, IOnManagerListener listener);
}
