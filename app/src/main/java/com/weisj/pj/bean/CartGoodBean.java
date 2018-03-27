package com.weisj.pj.bean;

import java.util.List;

/**
 * Created by jun on 2018/1/17.
 */

public class CartGoodBean {


    /**
     * {
     * "code": "1",
     * "msg": "操作成功!",
     * "data": [{
     * "cartId": 79,
     * "brandId": 0,
     * "brandName": null,
     * "sku": "1112EHT176-03",
     * "unit": "1112EHT176-03耳环",
     * "goodsNum": 6,
     * "buyNumber": 1,
     * "goodsId": "11111",
     * "goodsName": "1112EHT176-03耳环",
     * "img1": "/Public/backend/upload/images/2018/01/20180111144447_29847.jpg",
     * "price": 888.00,
     * "goodsSn": "FX3360437223",
     * "memberId": 0,
     * "cardTypeId": 0,
     * "rentNum": 3
     * }],
     * "desc": null
     * }
     */
    private String code;
    private String msg;

    boolean isdelet;

    public boolean isIsdelet() {
        return isdelet;
    }

    public void setIsdelet(boolean isdelet) {
        this.isdelet = isdelet;
    }

    private List<DataEntity> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        String cartId;
        String brandId;
        String brandName;
        String sku;
        String unit;
        String goodsNum;
        String goodsId;
        String goodsName;
        String img1;

        public String getCartId() {
            return cartId;
        }

        public void setCartId(String cartId) {
            this.cartId = cartId;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getGoodsSn() {
            return goodsSn;
        }

        public void setGoodsSn(String goodsSn) {
            this.goodsSn = goodsSn;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getCardTypeId() {
            return cardTypeId;
        }

        public void setCardTypeId(String cardTypeId) {
            this.cardTypeId = cardTypeId;
        }

        public String getRentNum() {
            return rentNum;
        }

        public void setRentNum(String rentNum) {
            this.rentNum = rentNum;
        }

        String price;
        String goodsSn;
        String memberId;
        String cardTypeId;
        String rentNum;
    }
}
