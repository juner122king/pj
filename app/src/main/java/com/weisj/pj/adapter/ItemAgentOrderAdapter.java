package com.weisj.pj.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.weisj.pj.R;
import com.weisj.pj.bean.AgentOrder;
import com.weisj.pj.utils.Urls;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        helper.setText(R.id.tv_couponmoney, "￥" + item.getCouponMoney());


        // 加载网络图片
        Glide.with(mContext).load(Urls.IP + item.getBuyCardHeaderPic()).crossFade().into((ImageView) helper.getView(R.id.iv_head_pic));
        Integer resourceId = 0;
        if (item.getCardNum().equals("1")) {
            resourceId = R.drawable.cardid1;
        } else if (item.getCardNum().equals("1")) {
            resourceId = R.drawable.cardid2;
        } else if (item.getCardNum().equals("1")) {
            resourceId = R.drawable.cardid3;
        } else {
            resourceId = R.drawable.cardid44;
        }

        if (item.getCardStatus().equals("4")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String createTime = sdf.format(new Date(Long.valueOf(item.getCreateTime())));
//            String payTime = sdf.format(new Date(Long.valueOf(item.getPayTime())));


            helper.setText(R.id.tv_time, "时间：" + createTime);
        } else if (item.getCardStatus().equals("3")) {
            helper.setText(R.id.tv_time, "时间：还未使用");
        } else if (item.getCardStatus().equals("5")) {
            helper.setText(R.id.tv_time, "时间：已过期");
        }
        SimpleDateFormat sdf_mon = new SimpleDateFormat("MM");
        String mon = sdf_mon.format(new Date(Long.valueOf(item.getCreateTime())));


        helper.setText(R.id.tv_time_m, "月份：" + mon);

        Glide.with(mContext)
                .load(resourceId)
                .crossFade()
                .fitCenter()
                .into((ImageView) helper.getView(R.id.iv_cardstatus));

    }


}
