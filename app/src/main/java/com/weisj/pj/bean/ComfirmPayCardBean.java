package com.weisj.pj.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class ComfirmPayCardBean implements Serializable {


    /**
     * {
     * "code": "1",
     * "msg": "确认支付虚拟卡成功！",
     * "data": {
     * "amount": 0.1,
     * "noncestr": "d042be1b4b72c110d21287b3dad13867",
     * "order_sn": "2018011712474141440",
     * "order_title": "菲尔南多会员卡",
     * "result_code": 0,
     * "result_msg": "获取prepay_id成功!",
     * "partnerid": "1493354472",
     * "prepayid": "wx20180117124741a49084bbfc0879778341",
     * "package_value": "Sign=WXPay",
     * "timestamp": "1516164461",
     * "sign": "E6E6E5F610EE66AE27B29AC4B88886FC",
     * "is_xcx": 0
     * "is_use_balace":1   后台是否用了余额支付
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


    public static class DataEntity implements Serializable {
        private String amount;
        private String noncestr;
        private String order_title;
        private String partnerid;
        private String prepayid;
        private String package_value;
        private String result_msg;
        private String sign;
        private String timestamp;
        private String is_use_balance;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        private int result_code;
        private int is_xcx;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getOrder_title() {
            return order_title;
        }

        public void setOrder_title(String order_title) {
            this.order_title = order_title;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackage_value() {
            return package_value;
        }

        public void setPackage_value(String package_value) {
            this.package_value = package_value;
        }

        public String getResult_msg() {
            return result_msg;
        }

        public void setResult_msg(String result_msg) {
            this.result_msg = result_msg;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getResult_code() {
            return result_code;
        }

        public void setResult_code(int result_code) {
            this.result_code = result_code;
        }

        public int getIs_xcx() {
            return is_xcx;
        }

        public void setIs_xcx(int is_xcx) {
            this.is_xcx = is_xcx;
        }

        public String getIs_use_balace() {
            return is_use_balance;
        }

        public void setIs_use_balace(String is_use_balace) {
            this.is_use_balance = is_use_balace;
        }
    }
}
