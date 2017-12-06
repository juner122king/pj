package com.weisj.pj.bean;

import java.util.List;

/**
 * Created by jun on 2017/11/21.
 * <p>
 * HOME页面用户分享的图片和评论
 */

public class UsershareBean {

    private String user_name;
    private String user_head;//用户头像
    private String user_comment;//评论
    private List<String> pics;//图片

    public UsershareBean(String user_name, String user_head, String user_comment, List<String> pics) {
        this.user_comment = user_comment;
        this.user_head = user_head;
        this.user_name = user_name;
        this.pics = pics;

    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }
}
