package com.weisj.pj.bean;

/**
 * Created by jun on 2017/12/6.
 */

public class CommodityShow {


    private String pic;
    private String title;
    private int nubmer;

    public CommodityShow(String pic, String title, int nubmer) {
        this.pic = pic;
        this.title = title;
        this.nubmer = nubmer;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNubmer() {
        return nubmer;
    }

    public void setNubmer(int nubmer) {
        this.nubmer = nubmer;
    }
}
