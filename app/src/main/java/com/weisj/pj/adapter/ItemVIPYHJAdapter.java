package com.weisj.pj.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.weisj.pj.R;
import com.weisj.pj.bean.YHJBean;

import java.util.List;

/**
 * Created by jun on 2017/12/10.
 */

public class ItemVIPYHJAdapter extends BaseQuickAdapter<YHJBean, BaseViewHolder> {

    public ItemVIPYHJAdapter(@Nullable List<YHJBean> data) {
        super(R.layout.dialog_vip_yhj_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, YHJBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_data, item.getData());
        helper.setText(R.id.tv_value, String.valueOf(item.getValue()));


    }
}
