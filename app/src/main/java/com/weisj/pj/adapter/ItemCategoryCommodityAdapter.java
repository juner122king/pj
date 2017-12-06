package com.weisj.pj.adapter;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.weisj.pj.R;
import com.weisj.pj.bean.CommodityShow;

import java.util.List;

/**
 * 品类页的商品Item
 */
public class ItemCategoryCommodityAdapter extends BaseQuickAdapter<CommodityShow, BaseViewHolder> {


    public ItemCategoryCommodityAdapter(List data) {
        super(R.layout.item_category, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityShow item) {

        helper.setText(R.id.tv_title, item.getTitle());

        // 加载网络图片
        Glide.with(mContext).load(item.getPic()).crossFade().into((ImageView) helper.getView(R.id.iv));
        helper.setText(R.id.tv_use_number, String.valueOf(item.getNubmer()));


    }
}
