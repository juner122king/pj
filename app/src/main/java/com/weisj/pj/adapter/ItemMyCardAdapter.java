package com.weisj.pj.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.weisj.pj.R;
import com.weisj.pj.base.activity.VipSRActivity;
import com.weisj.pj.bean.CardTypeBean;
import com.weisj.pj.view.photocheck.GlideRoundTransform;

import java.util.List;

/**
 * Created by jun on 2018/2/2.
 */

public class ItemMyCardAdapter extends BaseQuickAdapter<CardTypeBean.DataEntity, BaseViewHolder> {

    public ItemMyCardAdapter(@Nullable List<CardTypeBean.DataEntity> data) {
        super(R.layout.activity_mycard_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CardTypeBean.DataEntity item) {
//        Glide.with(mContext).load(Urls.IP).crossFade().into((ImageView) helper.getView(R.id.iv_head_pic));
        helper.setText(R.id.tv1, String.format("类别：%s", item.getTypeName()));
        helper.setText(R.id.tv2, String.format("总数：%s | 送出：%s", item.getAllCardCount(), item.getSendCount()));

        String cardTypeId = item.getCardTypeId();
        Integer resourceId = 0;
        if (cardTypeId.equals("1")) {

            resourceId = R.drawable.cardid1;
        } else if (cardTypeId.equals("2")) {
            resourceId = R.drawable.cardid2;
        } else if (cardTypeId.equals("3")) {
            resourceId = R.drawable.cardid3;
        } else {
            resourceId = R.drawable.cardid4;
        }
        Glide.with(mContext)
                .load(resourceId)

                .crossFade()
                .fitCenter()
                .transform(new GlideRoundTransform(mContext, 3))
                .into((ImageView) helper.getView(R.id.iv));


        helper.setOnClickListener(R.id.iv1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, VipSRActivity.class));
            }
        });

        helper.setOnClickListener(R.id.iv2, new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
}
