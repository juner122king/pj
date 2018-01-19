package com.weisj.pj.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.weisj.pj.R;
import com.weisj.pj.bean.AdressBean;

import java.util.List;

/**
 * Created by jun on 2018/1/15.
 */

public class ItemConsigneeAddress2 extends BaseQuickAdapter<AdressBean.DataEntity, BaseViewHolder> {


    public ItemConsigneeAddress2( @Nullable List<AdressBean.DataEntity> data) {

        super(R.layout.item_consignee_address, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdressBean.DataEntity item) {
        helper.setText(R.id.consignee_name, item.getRecipients());
        helper.setText(R.id.consignee_address, item.getProvince() + item.getCity() + item.getArea() + item.getAddress());
        helper.setText(R.id.consignee_phone, item.getPhone());
    }
}
