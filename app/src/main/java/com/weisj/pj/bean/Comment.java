package com.weisj.pj.bean;

import java.util.List;

/**
 * Created by jun on 2018/1/6.
 */

public class Comment {


    /**
     * {
     * "code": "1",
     * "msg": "操作成功!",
     * "data": [{
     * "commentId": 7224,
     * "commentType": 0,
     * "idValue": 11070,
     * "memberId": 1237790,
     * "userName": "白色的沙滩椅",
     * "commentRank": 5,
     * "createTime": 1511252877000,
     * "ipAddress": "127.0.0.1",
     * "status": 1,
     * "parentId": 0,
     * "content": "很漂亮",
     * "fileList": [{
     * "commentPicId": 224,
     * "commentId": 7224,
     * "picUrl": "/Public/backend/upload/images/2018/01/20180104123345_18620____375x375.jpg",
     * "sortOrder": 1
     * }, {
     * "commentPicId": 225,
     * "commentId": 7224,
     * "picUrl": "http://shop.party-queen.com/Public/backend/upload/images/2018/01/20180111103250_33898____500x500.jpg",
     * "sortOrder": 2
     * }, {
     * "commentPicId": 226,
     * "commentId": 7224,
     * "picUrl": "http://shop.party-queen.com/Public/backend/upload/images/2018/01/20180111110028_09066____500x500.jpg",
     * "sortOrder": 3
     * }],
     * "orderInfoId": 0,
     * "picUrl": null,
     * "nickname": null,
     * "headerPic": null,
     * "goodsName": null,
     * "img1": "/Public/backend/upload/images/2018/01/20180111150922_55470.jpg",
     * "unit": null
     * }, {
     * "commentId": 7223,
     * "commentType": 0,
     * "idValue": 11069,
     * "memberId": 1237790,
     * "userName": "白色的沙滩椅",
     * "commentRank": 5,
     * "createTime": 1511165924000,
     * "ipAddress": "127.0.0.1",
     * "status": 1,
     * "parentId": 0,
     * "content": "发错颜色了，应该是白粉蓝，发的是白灰粉，不过不碍事。没戴呢，带了再追评",
     * "fileList": [{
     * "commentPicId": 223,
     * "commentId": 7223,
     * "picUrl": "/Public/backend/upload/images/2018/01/20180104123345_18620____375x375.jpg",
     * "sortOrder": 1
     * }, {
     * "commentPicId": 227,
     * "commentId": 7223,
     * "picUrl": "http://shop.party-queen.com/Public/backend/upload/images/2018/01/20180111144329_30400____500x500.jpg",
     * "sortOrder": 2
     * }, {
     * "commentPicId": 228,
     * "commentId": 7223,
     * "picUrl": "http://shop.party-queen.com/Public/backend/upload/images/2018/01/20180111104505_81240____500x500.jpg",
     * "sortOrder": 3
     * }],
     * "orderInfoId": 0,
     * "picUrl": null,
     * "nickname": null,
     * "headerPic": null,
     * "goodsName": null,
     * "img1": "/Public/backend/upload/images/2018/01/20180111103734_77958.jpg",
     * "unit": null
     * }],
     * "desc": null
     * }
     */

    private String code;
    private String msg;
    private List<Comment.DataBean> data;


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

    public List<Comment.DataBean> getData() {
        return data;
    }

    public void setData(List<Comment.DataBean> data) {
        this.data = data;
    }

    public class DataBean {


        String commentId;
        String commentType;
        String content;
        String nickname;
        String userName;
        String goodsName;
        String img1;
        String headerPic;
        String status;
        List<FileList> fileList;


        public String getCommentType() {
            return commentType;
        }

        public void setCommentType(String commentType) {
            this.commentType = commentType;
        }

        public List<FileList> getFileList() {
            return fileList;
        }

        public void setFileList(List<FileList> fileList) {
            this.fileList = fileList;
        }

        public class FileList {

            String sortOrder;
            String picUrl;
            String commentPicId;
            String commentId;

            public String getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(String sortOrder) {
                this.sortOrder = sortOrder;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getCommentPicId() {
                return commentPicId;
            }

            public void setCommentPicId(String commentPicId) {
                this.commentPicId = commentPicId;
            }

            public String getCommentId() {
                return commentId;
            }

            public void setCommentId(String commentId) {
                this.commentId = commentId;
            }
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getHeaderPic() {
            return headerPic;
        }

        public void setHeaderPic(String headerPic) {
            this.headerPic = headerPic;
        }

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

    }
}
