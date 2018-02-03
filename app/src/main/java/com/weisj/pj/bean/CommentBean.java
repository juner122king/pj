package com.weisj.pj.bean;

/**
 * Created by jun on 2018/2/3.
 */

public class CommentBean {


    private String code;
    private String msg;


    private CommentBean.DataEntity data;


    public class DataEntity {
        String comment_id;

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }
    }

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

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }
}
