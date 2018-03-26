package com.weisj.pj.adapter;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.weisj.pj.MyApplication;
import com.weisj.pj.R;
//import com.weisj.pj.base.activity.CheckLogisticsActivity;
import com.weisj.pj.base.activity.HuanhuoActivity;
import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.bean.OrderBean;
import com.weisj.pj.main.fragment.order.DistributionCommissionView;
import com.weisj.pj.utils.ImageLoaderUtils;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.TextViewUtils;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.view.photocheck.GlideRoundTransform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ItemDistributionCommissionAdapter extends BaseExpandableListAdapter implements View.OnClickListener {
    private LayoutInflater mInflater;
    private List<OrderBean.DataEntity> list;
    private DistributionCommissionView view;


    public ItemDistributionCommissionAdapter(LayoutInflater mInflater, List<OrderBean.DataEntity> list, DistributionCommissionView view) {
        this.mInflater = mInflater;
        this.list = list;
        this.view = view;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        try {
            return list.get(groupPosition).getOrder_info_domain_list().size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        try {
            return list.get(groupPosition).getOrder_info_domain_list().get(childPosition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        BrandViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_distribution_commission, null);
            viewHolder = new BrandViewHolder();
            viewHolder.orderState = (TextView) convertView.findViewById(R.id.order_state);
            viewHolder.tv_but = (TextView) convertView.findViewById(R.id.tv_but);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (BrandViewHolder) convertView.getTag();
        }
        final OrderBean.DataEntity data = (OrderBean.DataEntity) getGroup(groupPosition);
        switch (data.getOrder_brand_state()) {
            case 0:// 0等待买家待付款,1买家已付款,2卖家已发货，待买家收货,3交易成功, 5交易关闭
                viewHolder.orderState.setText("待付款");

                break;
            case 1:
                viewHolder.orderState.setText("待商家发货");
                viewHolder.tv_but.setVisibility(View.VISIBLE);
                viewHolder.tv_but.setText("催发货");
                viewHolder.tv_but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MyApplication.getContext(), "已提醒卖家尽快发货！", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case 2:
                viewHolder.orderState.setText("待收货");
                viewHolder.tv_but.setText("签收");
                viewHolder.tv_but.setVisibility(View.VISIBLE);
                viewHolder.tv_but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Map<String, String> params = new HashMap<>();
                        params.put("member_id", PersonMessagePreferencesUtils.getUid());
                        params.put("order_brand_id", String.valueOf(data.getOrder_brand_id()));//订单号
                        OkHttpClientManager.postAsyn(Urls.confirmreceive, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
                            @Override
                            public void onError(Request request, Exception e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(BaseBean response) {
                                if (response != null) {
                                    Toast.makeText(MyApplication.getContext(), "签收成功！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    }
                });
                break;
            case 3:
                viewHolder.orderState.setText("待还货");
                viewHolder.tv_but.setVisibility(View.VISIBLE);
                viewHolder.tv_but.setText("还货");
                viewHolder.tv_but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MyApplication.getContext(), HuanhuoActivity.class);
                        intent.putExtra("order_id", data.getOrder_id());
                        intent.putExtra("consignee_id", "195530");


                        MyApplication.getContext().startActivity(intent);

                    }
                });
                break;
            case 4:
                viewHolder.orderState.setText("交易取消");

                break;
            case 5:
                viewHolder.orderState.setText("交易取消");

                break;

            case 14:
                viewHolder.orderState.setText("已完成");
                viewHolder.tv_but.setText("已完成");
                viewHolder.tv_but.setVisibility(View.INVISIBLE);
                break;
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        GoodViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_distribution_commission_good, null);
            viewHolder = new GoodViewHolder();
            viewHolder.goodImage = (ImageView) convertView.findViewById(R.id.iv);
            viewHolder.goodName = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.goodPrice = (TextView) convertView.findViewById(R.id.tv_jiage);
            viewHolder.spec_name = (TextView) convertView.findViewById(R.id.tv_title2);
            viewHolder.tv_but = (TextView) convertView.findViewById(R.id.tv_but);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GoodViewHolder) convertView.getTag();
        }
        final OrderBean.DataEntity.OrderInfoDomainListEntity childData = (OrderBean.DataEntity.OrderInfoDomainListEntity) getChild(groupPosition, childPosition);
        if (childData != null) {
//            OrderBean.DataEntity dataEntity = (OrderBean.DataEntity) getGroup(groupPosition);
            ImageLoaderUtils.getInstance().display(viewHolder.goodImage, childData.getSpec_pic());


            TextViewUtils.setText(viewHolder.goodName, childData.getGoods_name());
            TextViewUtils.setText(viewHolder.spec_name, childData.getSpec_name());
            TextViewUtils.setTextAndleftOther(viewHolder.goodPrice, childData.getPrice(), "￥");


        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_linear:

                break;

        }
    }


    private class BrandViewHolder {
        TextView orderState;
        TextView tv_but;
    }

    private class GoodViewHolder {
        ImageView goodImage;
        TextView goodName;
        TextView goodPrice;
        //        TextView goodBuyName;
        TextView spec_name;
        TextView tv_but;
//        TextView orderNum;
//        TextView orderCommission;
//        TextView goodNum;
//        LinearLayout bottomLinear;
//        LinearLayout goodNormal;
//        RelativeLayout goodDelete;
//        ImageView goodDeleteBt;
    }

}
