package com.weisj.pj.adapter;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.weisj.pj.R;
import com.weisj.pj.bean.HomeBean;
import com.weisj.pj.utils.Urls;

import java.util.List;

/**
 * 品类页的商品Item
 */
public class ItemCategoryCommodityAdapter extends BaseQuickAdapter<HomeBean.DataEntity.DistrictGoodsListEntity, BaseViewHolder> {


    public ItemCategoryCommodityAdapter(List data) {
        super(R.layout.item_category, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataEntity.DistrictGoodsListEntity item) {

        helper.setText(R.id.tv_title, item.getGoodsName());
        helper.setText(R.id.tv_unit, item.getUnit());
        // 加载网络图片
        Glide.with(mContext).load(Urls.imageUrl + item.getImg1()).crossFade().into((ImageView) helper.getView(R.id.iv));
        String sellNum;

        if (null == item.getSellNum())
            sellNum = "0";
        else
            sellNum = item.getSellNum();
        helper.setText(R.id.tv_use_number, sellNum);

    }
}
