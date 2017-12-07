package com.weisj.pj.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.weisj.pj.R;

import java.util.List;


public class ItemSearchAdapter extends BaseQuickAdapter<String, BaseViewHolder>{


    public ItemSearchAdapter(List data) {
        super(R.layout.item_search, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv, item);

    }

}

