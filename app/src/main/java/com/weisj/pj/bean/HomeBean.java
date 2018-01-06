package com.weisj.pj.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class HomeBean {


    private String code;
    private String msg;
    private DataEntity data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
//
//        private List<DistrictGoodsListEntity> districtGoodsList;
//
//        private List<HomeCouponbean.DataEntity.SingleCouponListEntity> districtCouponList;
//
//        private List<DistrictGoodsListEntity> countryGoodsList;
//
//        private List<PositionAreaGoodsListEntity> positionAreaGoodsList;
//        private String memberName;
        private String guanName;
//        private String highCommissionPicUrl;


        private List<Ad> topAdList;
        private List<Ad> insertAdList;
        private List<Ad> rotateAdList;
        private List<HomeBean.DataEntity.DistrictGoodsListEntity> recommendGoodsList;
        private List<Comment> commentList;

        public List<Ad> getTopAdList() {
            return topAdList;
        }

        public void setTopAdList(List<Ad> topAdList) {
            this.topAdList = topAdList;
        }

        public List<Ad> getInsertAdList() {
            return insertAdList;
        }

        public void setInsertAdList(List<Ad> insertAdList) {
            this.insertAdList = insertAdList;
        }

        public List<Ad> getRotateAdList() {
            return rotateAdList;
        }

        public void setRotateAdList(List<Ad> rotateAdList) {
            this.rotateAdList = rotateAdList;
        }

        public List<DistrictGoodsListEntity> getRecommendGoodsList() {
            return recommendGoodsList;
        }

        public void setRecommendGoodsList(List<DistrictGoodsListEntity> recommendGoodsList) {
            this.recommendGoodsList = recommendGoodsList;
        }

        public List<Comment> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<Comment> commentList) {
            this.commentList = commentList;
        }

//        public String getHighCommissionPicUrl() {
//            return highCommissionPicUrl;
//        }
//
//        public void setHighCommissionPicUrl(String highCommissionPicUrl) {
//            this.highCommissionPicUrl = highCommissionPicUrl;
//        }
//
        public String getGuanName() {
            return guanName;
        }

        public void setGuanName(String guanName) {
            this.guanName = guanName;
        }

//        public String getMemberName() {
//            return memberName;
//        }
//
//        public void setMemberName(String memberName) {
//            this.memberName = memberName;
//        }
//
//        public void setDistrictGoodsList(List<DistrictGoodsListEntity> districtGoodsList) {
//            this.districtGoodsList = districtGoodsList;
//        }
//
//        public void setDistrictCouponList(List<HomeCouponbean.DataEntity.SingleCouponListEntity> districtCouponList) {
//            this.districtCouponList = districtCouponList;
//        }
//
//        public void setCountryGoodsList(List<DistrictGoodsListEntity> countryGoodsList) {
//            this.countryGoodsList = countryGoodsList;
//        }
//
//        public List<DistrictGoodsListEntity> getDistrictGoodsList() {
//            return districtGoodsList;
//        }
//
//        public List<HomeCouponbean.DataEntity.SingleCouponListEntity> getDistrictCouponList() {
//            return districtCouponList;
//        }
//
//        public List<DistrictGoodsListEntity> getCountryGoodsList() {
//            return countryGoodsList;
//        }
//
//        public List<PositionAreaGoodsListEntity> getPositionAreaGoodsList() {
//            return positionAreaGoodsList;
//        }
//
//        public void setPositionAreaGoodsList(List<PositionAreaGoodsListEntity> positionAreaGoodsList) {
//            this.positionAreaGoodsList = positionAreaGoodsList;
//        }

        public static class DistrictGoodsListEntity {
            private int goodsId;
            private String goodsName;
            private String img1;
            private String img2;
            private String img3;
            private String img4;
            private String img5;
            private String img6;
            private String img7;
            private String delMoney;
            private String price;
            private String describe;
            private String website;
            private String unit;
            private String delMoneyFinish;
            private String commission;
            private String sellNum;
            private String maxPrice;

            public String getDelMoneyFinish() {
                if (delMoney != null && price != null) {
                    BigDecimal bDelMoney = BigDecimal.valueOf(Double.valueOf(delMoney));
                    BigDecimal bMoney = new BigDecimal(price);
                    BigDecimal newMoney = bMoney.subtract(bDelMoney);
                    return newMoney.setScale(2).toString();
                } else {
                    return "0";
                }
            }

            public String getSellNum() {
                return sellNum;
            }

            public void setSellNum(String sellNum) {
                this.sellNum = sellNum;
            }

            public String getMaxPrice() {
                return maxPrice;
            }

            public void setMaxPrice(String maxPrice) {
                this.maxPrice = maxPrice;
            }

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getImg4() {
                return img4;
            }

            public void setImg4(String img4) {
                this.img4 = img4;
            }

            public String getImg5() {
                return img5;
            }

            public void setImg5(String img5) {
                this.img5 = img5;
            }

            public String getImg6() {
                return img6;
            }

            public void setImg6(String img6) {
                this.img6 = img6;
            }

            public String getImg7() {
                return img7;
            }

            public void setImg7(String img7) {
                this.img7 = img7;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }


            public void setImg1(String img1) {
                this.img1 = img1;
            }


            public void setPrice(String price) {
                this.price = price;
            }


            public void setDelMoney(String delMoney) {
                this.delMoney = delMoney;
            }


            public int getGoodsId() {
                return goodsId;
            }

            public String getGoodsName() {
                return goodsName;
            }


            public String getImg1() {
                return img1;
            }


            public String getPrice() {
                return price;
            }

            public String getDelMoney() {
                return delMoney;
            }

            public String getDescribe() {
                return describe;
            }

        }


        public static class PositionAreaGoodsListEntity {

            private String adId;
            private String positionId;
            private String positionName;
            private String recommendCategoryId;
            private String positionPicUrl;
            private ArrayList<AreaGoodsListListEntity> areaGoodsList;

            public String getAdId() {
                return adId;
            }

            public void setAdId(String adId) {
                this.adId = adId;
            }

            public String getPositionId() {
                return positionId;
            }

            public void setPositionId(String positionId) {
                this.positionId = positionId;
            }

            public String getPositionName() {
                return positionName;
            }

            public void setPositionName(String positionName) {
                this.positionName = positionName;
            }

            public String getRecommendCategoryId() {
                return recommendCategoryId;
            }

            public void setRecommendCategoryId(String recommendCategoryId) {
                this.recommendCategoryId = recommendCategoryId;
            }

            public String getPositionPicUrl() {
                return positionPicUrl;
            }

            public void setPositionPicUrl(String positionPicUrl) {
                this.positionPicUrl = positionPicUrl;
            }

            public ArrayList<AreaGoodsListListEntity> getAreaGoodsList() {
                return areaGoodsList;
            }

            public void setAreaGoodsList(ArrayList<AreaGoodsListListEntity> areaGoodsList) {
                this.areaGoodsList = areaGoodsList;
            }
        }


        public static class AreaGoodsListListEntity {

            private String adPositionId;
            private String adName;
            private String adPic;
            private String img1;
            private String goodsName;
            private String price;
            private String commission;
            private String unit;
            private String districtName;
            private String districtId;
            private String recommendCategoryId;
            private String createTime;
            private String sellNum;
            private String sortOrder;
            private String website;
            private String goodsId;
            private String adId;
            private String areaId;

            public String getAdPositionId() {
                return adPositionId;
            }

            public void setAdPositionId(String adPositionId) {
                this.adPositionId = adPositionId;
            }

            public String getAdName() {
                return adName;
            }

            public void setAdName(String adName) {
                this.adName = adName;
            }

            public String getAdPic() {
                return adPic;
            }

            public void setAdPic(String adPic) {
                this.adPic = adPic;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getDistrictName() {
                return districtName;
            }

            public void setDistrictName(String districtName) {
                this.districtName = districtName;
            }

            public String getDistrictId() {
                return districtId;
            }

            public void setDistrictId(String districtId) {
                this.districtId = districtId;
            }

            public String getRecommendCategoryId() {
                return recommendCategoryId;
            }

            public void setRecommendCategoryId(String recommendCategoryId) {
                this.recommendCategoryId = recommendCategoryId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getSellNum() {
                return sellNum;
            }

            public void setSellNum(String sellNum) {
                this.sellNum = sellNum;
            }

            public String getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(String sortOrder) {
                this.sortOrder = sortOrder;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getAdId() {
                return adId;
            }

            public void setAdId(String adId) {
                this.adId = adId;
            }

            public String getAreaId() {
                return areaId;
            }

            public void setAreaId(String areaId) {
                this.areaId = areaId;
            }
        }

    }
}
