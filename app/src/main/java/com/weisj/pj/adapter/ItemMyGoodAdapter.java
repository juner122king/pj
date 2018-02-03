package com.weisj.pj.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.weisj.pj.R;
import com.weisj.pj.bean.CartGoodBean;
import com.weisj.pj.bean.OrderBean;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.view.photocheck.GlideRoundTransform;

import java.util.List;

/**
 * Created by jun on 2018/2/3.
 */

public class ItemMyGoodAdapter extends BaseQuickAdapter<OrderBean.DataEntity.OrderInfoDomainListEntity, BaseViewHolder> {


    public ItemMyGoodAdapter(@Nullable List<OrderBean.DataEntity.OrderInfoDomainListEntity> data) {

        super(R.layout.activity_mygood, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean.DataEntity.OrderInfoDomainListEntity item) {


        helper.setText(R.id.tv1, item.getGoods_name());

        if (item.getSpec_name().equals("") || null == item.getSpec_name())
            helper.setVisible(R.id.tv2, false);
        else
            helper.setText(R.id.tv2, item.getSpec_name());


        Glide.with(mContext)
                .load(item.getSpec_pic())
                .crossFade()
                .transform(new GlideRoundTransform(mContext, 3)).
                into((ImageView) helper.getView(R.id.iv));



    }
}