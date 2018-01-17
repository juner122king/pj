package com.weisj.pj.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class CardTypeBean {


    /**
     * code : 1
     * msg : 操作成功!
     * data : {
     * "code": "1",
     * "msg": "返回所有类型的卡",
     * "data": [{
     * "cardTypeId": 1,
     * "typeName": "199",
     * "typePic": null,
     * "cardType": 0,
     * "cardMoney": 0.10,
     * "days": 30,
     * "createTime": 1515048038000,
     * "rentNum": 3
     * }, {
     * "cardTypeId": 2,
     * "typeName": "499",x
     * "typePic": null,
     * "cardType": 0,
     * "cardMoney": 0.50,
     * "days": 360,
     * "createTime": 1515048062000,
     * "rentNum": 8
     * }],
     * "desc": null
     * }
     */

    private String code;
    private String msg;


    private List<DataEntity> data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String cardTypeId;
        private int typeName;
        private int typePic;
        private String cardType;
        private String cardMoney;
        private String days;
        private String rentNum;

        public String getCardTypeId() {
            return cardTypeId;
        }

        public void setCardTypeId(String cardTypeId) {
            this.cardTypeId = cardTypeId;
        }

        public int getTypeName() {
            return typeName;
        }

        public void setTypeName(int typeName) {
            this.typeName = typeName;
        }

        public int getTypePic() {
            return typePic;
        }

        public void setTypePic(int typePic) {
            this.typePic = typePic;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getCardMoney() {
            return cardMoney;
        }

        public void setCardMoney(String cardMoney) {
            this.cardMoney = cardMoney;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getRentNum() {
            return rentNum;
        }

        public void setRentNum(String rentNum) {
            this.rentNum = rentNum;
        }
    }
}
