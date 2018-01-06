package com.weisj.pj.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.weisj.pj.R;
import com.weisj.pj.bean.Comment;
import com.weisj.pj.utils.Urls;

import java.util.List;

/**
 * 用户评论
 * Created by jun on 2017/11/21.
 */

public class ItemHomeUserCommentAdapter extends BaseQuickAdapter<Comment, BaseViewHolder> {


    public ItemHomeUserCommentAdapter(List data) {
        super(R.layout.item_home_user_im, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Comment item) {
        helper.setText(R.id.tv_username, item.getNickname());
        helper.setText(R.id.tv_user_comment, item.getContent());
        // 加载网络图片
        Glide.with(mContext).load(Urls.imageUrl + item.getHeaderPic()).crossFade().into((ImageView) helper.getView(R.id.iv_head_pic));
        Glide.with(mContext).load(Urls.imageUrl + item.getImg1()).crossFade().into((ImageView) helper.getView(R.id.iv0));

    }


}
