package com.weisj.pj.bean;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class CardBean {


    /**
     * {
     * "code": "1",
     * "msg": "分配一张该类型的卡成功",
     * "data": {
     * "cardId": 61,
     * "cardTypeId": 1,
     * "cardIssueId": 1,
     * "cardNo": "1049",
     * "cardPassword": "123456",
     * "status": 0,
     * "createTime": 1515048282000,
     * "viewTime": null,
     * "activeTime": null,
     * "enableTime": null,
     * "cardMoney": 299.00,
     * "couponMoney": 100.00,
     * "actualMoney": 0.10,
     * "days": 30
     * },
     * "desc": null
     * }
     */

    private String code;
    private String msg;


    private DataEntity data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }



    public static class DataEntity {
        private int cardId;
        private int cardTypeId;
        private int cardIssueId;
        private String cardNo;
        private String cardPassword;
        private String status;
        private String cardMoney;
        private String couponMoney;
        private String actualMoney;
        private int days;

        public int getCardId() {
            return cardId;
        }

        public void setCardId(int cardId) {
            this.cardId = cardId;
        }

        public int getCardIssueId() {
            return cardIssueId;
        }

        public void setCardIssueId(int cardIssueId) {
            this.cardIssueId = cardIssueId;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getCardPassword() {
            return cardPassword;
        }

        public void setCardPassword(String cardPassword) {
            this.cardPassword = cardPassword;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCouponMoney() {
            return couponMoney;
        }

        public void setCouponMoney(String couponMoney) {
            this.couponMoney = couponMoney;
        }

        public String getActualMoney() {
            return actualMoney;
        }

        public void setActualMoney(String actualMoney) {
            this.actualMoney = actualMoney;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public int getCardTypeId() {
            return cardTypeId;
        }

        public void setCardTypeId(int cardTypeId) {
            this.cardTypeId = cardTypeId;
        }


        public String getCardMoney() {
            return cardMoney;
        }

        public void setCardMoney(String cardMoney) {
            this.cardMoney = cardMoney;
        }

    }
}
