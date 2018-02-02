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

public class ItemHomeUserCommentAdapter extends BaseQuickAdapter<Comment.DataBean, BaseViewHolder> {


    public ItemHomeUserCommentAdapter(List data) {
        super(R.layout.item_home_user_im, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Comment.DataBean item) {
        helper.setText(R.id.tv_username, item.getUserName());
        helper.setText(R.id.tv_user_comment, item.getContent());
        // 加载网络图片
//        Glide.with(mContext).load(Urls.imageUrl + item.getHeaderPic()).crossFade().into((ImageView) helper.getView(R.id.iv_head_pic));
        Glide.with(mContext).load(Urls.IP + item.getHeaderPic()).crossFade().into((ImageView) helper.getView(R.id.iv_head_pic));


        switch (item.getFileList().size()) {

            case 1:
                Glide.with(mContext).load(item.getFileList().get(0).getPicUrl()).crossFade().into((ImageView) helper.getView(R.id.iv0));
                break;
            case 2:
                Glide.with(mContext).load(item.getFileList().get(0).getPicUrl()).crossFade().into((ImageView) helper.getView(R.id.iv0));
                Glide.with(mContext).load(item.getFileList().get(1).getPicUrl()).crossFade().into((ImageView) helper.getView(R.id.iv1));
                break;
            case 3:
                Glide.with(mContext).load(item.getFileList().get(0).getPicUrl()).crossFade().into((ImageView) helper.getView(R.id.iv0));
                Glide.with(mContext).load(item.getFileList().get(1).getPicUrl()).crossFade().into((ImageView) helper.getView(R.id.iv1));
                Glide.with(mContext).load(item.getFileList().get(2).getPicUrl()).crossFade().into((ImageView) helper.getView(R.id.iv2));
                break;

        }


        String status_v = "";

        if (item.getStatus() == "1")
            status_v = "会员";
        else
            helper.setVisible(R.id.tv_vip_lvl, false);


        helper.setText(R.id.tv_vip_lvl, status_v);
    }


}
