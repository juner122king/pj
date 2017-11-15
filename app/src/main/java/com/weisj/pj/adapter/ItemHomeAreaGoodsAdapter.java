package com.weisj.pj.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weisj.pj.R;
import com.weisj.pj.base.activity.GoodDetailActivity;
import com.weisj.pj.base.activity.SpecialGoodsListActivity;
import com.weisj.pj.bean.HomeBean;
import com.weisj.pj.utils.ImageLoaderUtils;
import com.weisj.pj.utils.TextViewUtils;
import com.weisj.pj.utils.Urls;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25.
 */

public class ItemHomeAreaGoodsAdapter<T> extends BaseAdapter {

    private List<T> objects;
    private Context context;
    private LayoutInflater layoutInflater;

    public ItemHomeAreaGoodsAdapter(Context context, List<T> list) {
        this.context = context;
        objects = list;
        this.layoutInflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public T getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_home_areagoods, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.goodImage = (ImageView) convertView.findViewById(R.id.image_high_url);
            viewHolder.areaGoodsListScroll =(HorizontalScrollView) convertView.findViewById(R.id.home_scroll_view);
            viewHolder.areaGoodsLinear = (LinearLayout) convertView.findViewById(R.id.home_boss_good_linear);
            convertView.setTag(viewHolder);
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(final T object, ViewHolder holder) {
        //TODO implement
        if (object instanceof HomeBean.DataEntity.PositionAreaGoodsListEntity) {
            final HomeBean.DataEntity.PositionAreaGoodsListEntity data = (HomeBean.DataEntity.PositionAreaGoodsListEntity) object;
            ImageLoaderUtils.getInstance().display(holder.goodImage, Urls.IP  +data.getPositionPicUrl());

            holder.areaGoodsLinear.removeAllViews();
            if (data.getAreaGoodsList() != null && data.getAreaGoodsList().size()>0) {
                for (int i = 0; i < data.getAreaGoodsList().size(); i++) {
                    View view = getView(data.getAreaGoodsList().get(i));
                    holder.areaGoodsLinear.addView(view);
                }
            }



            holder.goodImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent =  new Intent(context, SpecialGoodsListActivity.class);
                    intent.putExtra("ad_id",data.getAdId());
                    context.startActivity(intent);
                }
            });
        }

    }



    private View getView(final HomeBean.DataEntity.AreaGoodsListListEntity hotGoodsBean) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_areagoods_scrool, null);
        ImageLoaderUtils.getInstance().display((ImageView) view.findViewById(R.id.image), hotGoodsBean.getImg1());
        TextViewUtils.setText((TextView) view.findViewById(R.id.good_name), hotGoodsBean.getAdName());
        TextViewUtils.setText((TextView) view.findViewById(R.id.good_price),"￥"+ TextViewUtils.sprStringMoney(hotGoodsBean.getPrice(), 2) +"/" + hotGoodsBean.getUnit());
        TextViewUtils.setText((TextView) view.findViewById(R.id.commission), TextViewUtils.sprStringMoney(Double.valueOf(hotGoodsBean.getCommission())*50+"", 2) +"");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, GoodDetailActivity.class);
                intent.putExtra("goodId", Integer.valueOf(hotGoodsBean.getGoodsId()));
                context.startActivity(intent);
            }
        });
        return view;
    }


    protected class ViewHolder {
        ImageView goodImage;
        HorizontalScrollView areaGoodsListScroll;
        LinearLayout areaGoodsLinear;
    }

}
