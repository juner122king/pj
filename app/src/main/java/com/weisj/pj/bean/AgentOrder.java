package com.weisj.pj.bean;

import java.util.List;

/**
 * Created by jun on 2018/3/26.
 */

public class AgentOrder {


    /**
     * "cardOrderId": 367,
     * "orderNo": "2018031916531694970",
     * "memberId": 1237794,
     * "agentId": 4,
     * "cardNum": 1,
     * "totalMoney": 99,
     * "couponMoney": 0,
     * "payMoney": 99,
     * "createTime": 1521449596000,
     * "payTime": 1521449604000,
     * "receiveTime": null,
     * "orderStatus": 1,
     * "cardId": 12,
     * "firstCommission": null,
     * "secondCommission": null,
     * "cardNo": "1008",
     * "cardTypeName": "体验会员卡",
     * "cardStatus": 3,
     * "buyCardNickname": "菲尔南多_023105",
     * "buyCardHeaderPic": "/Public/upload/header_images/2018/02/20180207162903_51454.jpg"
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String msg;
    private List<AgentOrder.DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public class DataBean {


        String cardTypeName;//vip卡名
        String buyCardNickname;//购卡人
        String couponMoney;//佣金
        String payMoney;//价格
        String cardNum;//卡种类
        String buyCardHeaderPic;//头像
        String createTime;//时间
        String payTime;//后时间
        String cardStatus;//状态

        public String getCardStatus() {
            return cardStatus;
        }

        public void setCardStatus(String cardStatus) {
            this.cardStatus = cardStatus;
        }

        public String getCardTypeName() {
            return cardTypeName;
        }

        public void setCardTypeName(String cardTypeName) {
            this.cardTypeName = cardTypeName;
        }

        public String getBuyCardNickname() {
            return buyCardNickname;
        }

        public void setBuyCardNickname(String buyCardNickname) {
            this.buyCardNickname = buyCardNickname;
        }

        public String getCouponMoney() {
            return couponMoney;
        }

        public void setCouponMoney(String couponMoney) {
            this.couponMoney = couponMoney;
        }

        public String getPayMoney() {
            return payMoney;
        }

        public void setPayMoney(String payMoney) {
            this.payMoney = payMoney;
        }

        public String getCardNum() {
            return cardNum;
        }

        public void setCardNum(String cardStatus) {
            this.cardNum = cardStatus;
        }

        public String getBuyCardHeaderPic() {
            return buyCardHeaderPic;
        }

        public void setBuyCardHeaderPic(String buyCardHeaderPic) {
            this.buyCardHeaderPic = buyCardHeaderPic;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }
    }
}
