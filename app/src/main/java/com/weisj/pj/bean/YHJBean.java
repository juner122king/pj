package com.weisj.pj.bean;

/**
 * Created by jun on 2017/12/10.
 * <p>
 * 优惠劵
 */


public class YHJBean {

    String title;
    String data;
    int value;

    public YHJBean(String title,
                   String data,
                   int value) {
        this.title = title;
        this.data = data;
        this.value = value;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
