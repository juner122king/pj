package com.weisj.pj.bean;

import android.graphics.Bitmap;

import com.weisj.pj.utils.BitmapUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public class ShareData {
    private String title;
    private String content;
    private String url;
    private Bitmap bitmap;
    private int goodId;
    private int couponId;
    private List<String> listUrl;
    private boolean isShareHome;

    private String price;
    private String delMoney;

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }


    public List<String> getListUrl() {
        return listUrl;
    }

    public void setListUrl(List<String> listUrl) {
        this.listUrl = listUrl;
    }

    public ShareData(Bitmap bitmap,List<String> listUrl, String content, String title, String url, int goodId, int couponId) {
        this.content = content;
        this.goodId = goodId;
        this.couponId = couponId;
        this.title = title;
        this.url = url;
        this.listUrl = listUrl;
        this.bitmap = bitmap;
    }

    public ShareData(Bitmap bitmap,List<String> listUrl, String content, String title, String url, int goodId, int couponId,String price,String delMoney) {
        this.content = content;
        this.goodId = goodId;
        this.couponId = couponId;
        this.title = title;
        this.url = url;
        this.listUrl = listUrl;
        this.bitmap = bitmap;
        this.price = price;
        this.delMoney = delMoney;
    }


    public ShareData(Bitmap bitmap, String content, String title, String url, int goodId, int couponId,String price,String delMoney) {
        this.content = content;
        this.goodId = goodId;
        this.couponId = couponId;
        this.title = title;
        this.url = url;
        this.bitmap = bitmap;
        this.price = price;
        this.delMoney = delMoney;
    }

    public ShareData(Bitmap bitmap, String content, String title, String url, int goodId, int couponId) {
        this.content = content;
        this.goodId = goodId;
        this.couponId = couponId;
        this.title = title;
        this.url = url;
        this.bitmap = bitmap;
    }
    public ShareData(boolean isShareHome, String content, String title, String url,String price,String delMoney) {
        this.content = content;
        this.title = title;
        this.url = url;
        this.isShareHome = isShareHome;
        this.price = price;
        this.delMoney = delMoney;
    }

    public ShareData(boolean isShareHome, String content, String title, String url) {
        this.content = content;
        this.title = title;
        this.url = url;
        this.isShareHome = isShareHome;
    }

    public boolean isShareHome() {
        return isShareHome;
    }

    public void setShareHome(boolean shareHome) {
        isShareHome = shareHome;
    }

    public ShareData() {

    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDelMoney() {
        return delMoney;
    }

    public void setDelMoney(String delMoney) {
        this.delMoney = delMoney;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = BitmapUtil.ImageCrop(bitmap);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
