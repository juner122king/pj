package com.weisj.pj.adapter;

import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.weisj.pj.R;
import com.weisj.pj.bean.CartGoodBean;
import com.weisj.pj.main.fragment.order.DistributionCommissionView;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.view.photocheck.GlideRoundTransform;

import java.util.List;

/**
 * Created by jun on 2018/1/15.
 */

public class ItemCarListAdapter extends BaseQuickAdapter<CartGoodBean.DataEntity, BaseViewHolder> {


    List<CartGoodBean.DataEntity> data;
    public SparseBooleanArray mCheckStates;
    boolean isdelete;

    public ItemCarListAdapter(CartGoodBean cartGoodBean) {

        super(R.layout.item_cart, cartGoodBean.getData());


        this.isdelete = cartGoodBean.isIsdelet();
        this.data = cartGoodBean.getData();
        mCheckStates = new SparseBooleanArray(data.size());
        for (int i = 0; i < data.size(); i++) {
            mCheckStates.put(i, false);

        }

    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    @Override
    protected void convert(BaseViewHolder helper, CartGoodBean.DataEntity item) {


    }

    @Override
    public void onBindViewHolder(BaseViewHolder helper, final int position) {


        helper.setText(R.id.tv_title, data.get(position).getGoodsName());
        helper.setText(R.id.tv_title2, data.get(position).getUnit());
        helper.setText(R.id.tv_jiage, "￥" + data.get(position).getPrice());

        Glide.with(mContext)
                .load(Urls.imageUrl + data.get(position).getImg1())
                .crossFade()
                .transform(new GlideRoundTransform(mContext, 3)).
                into((ImageView) helper.getView(R.id.iv));


        CheckBox checkBox = helper.getView(R.id.cb);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    mCheckStates.put(position, true);
                    //do something
                } else {
                    mCheckStates.put(position, false);
                    //do something else
                }
            }
        });
        checkBox.setChecked(mCheckStates.get(position, false));


        if (!isdelete)
            checkBox.setVisibility(View.VISIBLE);
        else
            checkBox.setVisibility(View.INVISIBLE);
    }
}
