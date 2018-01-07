package com.weisj.pj.bean;

import java.util.List;

/**
 * Created by jun on 2018/1/7.
 */

public class GoodsParams {

    String categoryCode;
    List<String> propertyCodeList;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public List<String> getPropertyCodeList() {
        return propertyCodeList;
    }

    public void setPropertyCodeList(List<String> propertyCodeList) {
        this.propertyCodeList = propertyCodeList;
    }



}
