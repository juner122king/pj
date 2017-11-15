package com.weisj.pj.manager;

import com.weisj.pj.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public interface IHomeManager {

    void getHomeInfo(String pronvin, IOnManagerListener onHomeListener);

    void getHomeBanner(IOnManagerListener onHomeListener);

}
