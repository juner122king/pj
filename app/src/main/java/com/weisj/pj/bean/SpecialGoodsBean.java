package com.weisj.pj.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */

public class SpecialGoodsBean {

    /**
     * code : 1
     * msg : 获取验证码成功!
     */

    private String code;
    private String msg;
    private List<HomeBean.DataEntity.AreaGoodsListListEntity> data;

    public  List<HomeBean.DataEntity.AreaGoodsListListEntity> getData() {
        return data;
    }

    public void setData(List<HomeBean.DataEntity.AreaGoodsListListEntity> data) {
        this.data = data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
