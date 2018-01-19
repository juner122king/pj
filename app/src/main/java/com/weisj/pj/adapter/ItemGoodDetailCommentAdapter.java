package com.weisj.pj.adapter;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.weisj.pj.R;
import com.weisj.pj.bean.GoodPoint;

import java.util.List;

/**
 * 商品留言Item
 */
public class ItemGoodDetailCommentAdapter extends BaseQuickAdapter<GoodPoint.DataBean, BaseViewHolder> {


    public ItemGoodDetailCommentAdapter(List data) {
        super(R.layout.head_good_detail_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodPoint.DataBean item) {

        helper.setText(R.id.tv1, item.getUser_name());
        helper.setText(R.id.tv2, item.getContent());

        // 加载网络图片
        Glide.with(mContext).load("http://upload.jianshu.io/users/upload_avatars/972352/8432d981-ac19-450c-bb25-e134d7f26385.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/96/h/96").crossFade().into((ImageView) helper.getView(R.id.civ));


    }
}
