package com.weisj.pj.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.weisj.pj.R;
import com.weisj.pj.bean.AgentOrder;
import com.weisj.pj.utils.Urls;

import java.util.List;

/**
 * Created by jun on 2018/1/22.
 */

public class ItemAgentOrderAdapter extends BaseQuickAdapter<AgentOrder.DataBean, BaseViewHolder> {


    public ItemAgentOrderAdapter(@Nullable List<AgentOrder.DataBean> data) {
        super(R.layout.item_agentorder, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AgentOrder.DataBean item) {


        helper.setText(R.id.tv_username, item.getBuyCardNickname());

        helper.setText(R.id.tv_card_name, item.getCardTypeName());
        helper.setText(R.id.tv_pay_money, "价格：￥" + item.getPayMoney());
        helper.setText(R.id.tv_couponmoney, item.getCouponMoney());

        String createTime = item.getCreateTime();
        String payTime = item.getPayTime();




        helper.setText(R.id.tv_time_m, "月份：" + item.getCreateTime());
        helper.setText(R.id.tv_time, "时间：" + item.getPayTime());


        // 加载网络图片
        Glide.with(mContext).load(Urls.IP + item.getBuyCardHeaderPic()).crossFade().into((ImageView) helper.getView(R.id.iv_head_pic));


//        Glide.with(mContext).load().crossFade().into((ImageView) helper.getView(R.id.iv_cardstatus));


    }


}
