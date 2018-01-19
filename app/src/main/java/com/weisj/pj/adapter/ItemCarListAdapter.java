package com.weisj.pj.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.weisj.pj.R;
import com.weisj.pj.bean.CartGoodBean;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.view.photocheck.GlideRoundTransform;

import java.util.List;

/**
 * Created by jun on 2018/1/15.
 */

public class ItemCarListAdapter extends BaseQuickAdapter<CartGoodBean.DataEntity, BaseViewHolder> {


    public ItemCarListAdapter(@Nullable List<CartGoodBean.DataEntity> data) {

        super(R.layout.item_cart, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CartGoodBean.DataEntity item) {
        helper.setText(R.id.tv_title, item.getUnit());
        helper.setText(R.id.tv_title2, item.getSku());
        helper.setText(R.id.tv_jiage, "￥" + item.getPrice());

        Glide.with(mContext)
                .load(Urls.imageUrl + item.getImg1())
                .crossFade()
                .transform(new GlideRoundTransform(mContext, 3)).
                into((ImageView) helper.getView(R.id.iv));


    }
}
