package com.weisj.pj.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.weisj.pj.R;
import com.weisj.pj.base.activity.GoodDetailActivity;
import com.weisj.pj.bean.HomeBean;
import com.weisj.pj.bean.ShareData;
import com.weisj.pj.utils.ImageLoaderUtils;
import com.weisj.pj.utils.TextViewUtils;
import com.weisj.pj.view.dialog.ShareViewDialog;

import java.util.ArrayList;
import java.util.List;


public class ItemSpecialGoodAdapter<T> extends BaseAdapter implements View.OnClickListener {

    private List<T> objects;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemSpecialGoodAdapter(Context context, List<T> list) {
        this.context = context;
        objects = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int count = objects.size();
        if (count % 2 == 0) {
            return count / 2;
        } else {
            return count / 2 + 1;
        }
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_search_high_list, null);
            viewHolder = new ViewHolder();
            viewHolder.goodImage1 = (ImageView) convertView.findViewById(R.id.image1);
            viewHolder.goodShare1 = convertView.findViewById(R.id.good_share1);
            viewHolder.goodName1 = (TextView) convertView.findViewById(R.id.name1);
            viewHolder.goodDesc1 = (TextView) convertView.findViewById(R.id.content1);
            viewHolder.goodPrice1 = (TextView) convertView.findViewById(R.id.good_price1);
//            viewHolder.goodNumber1 = (TextView) convertView.findViewById(R.id.good_number1);
            viewHolder.goodImage2 = (ImageView) convertView.findViewById(R.id.image2);
            viewHolder.goodShare2 = convertView.findViewById(R.id.good_share2);
            viewHolder.goodName2 = (TextView) convertView.findViewById(R.id.name2);
            viewHolder.goodDesc2 = (TextView) convertView.findViewById(R.id.content2);
            viewHolder.goodPrice2 = (TextView) convertView.findViewById(R.id.good_price2);
//            viewHolder.goodNumber2 = (TextView) convertView.findViewById(R.id.good_number2);
            viewHolder.linear1 = convertView.findViewById(R.id.linear1);
            viewHolder.linear2 = convertView.findViewById(R.id.linear2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        initializeViews((T) getItem(position * 2), (ViewHolder) convertView.getTag());
        viewHolder.linear1.setOnClickListener(this);
        viewHolder.linear1.setTag(getItem(position * 2));
        if (position * 2 + 1 < objects.size()) {
            initializeViews2((T) getItem(position * 2 + 1), (ViewHolder) convertView.getTag());
            viewHolder.linear2.setVisibility(View.VISIBLE);
            viewHolder.linear2.setOnClickListener(this);
            viewHolder.linear2.setTag(getItem(position * 2 + 1));
        } else {
            viewHolder.linear2.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    private void initializeViews2(T object, ViewHolder holder) {
        if (object instanceof HomeBean.DataEntity.AreaGoodsListListEntity) {
            HomeBean.DataEntity.AreaGoodsListListEntity data = (HomeBean.DataEntity.AreaGoodsListListEntity) object;
            ImageLoaderUtils.getInstance().display(holder.goodImage2, data.getImg1());
            TextViewUtils.setText(holder.goodName2, data.getGoodsName());
//            TextViewUtils.setText(holder.goodDesc2, data.get());
            TextViewUtils.setText(holder.goodDesc2,"￥"+ TextViewUtils.sprStringMoney(data.getPrice(), 2) +"/" + data.getUnit());
            TextViewUtils.setText(holder.goodPrice2,  TextViewUtils.sprStringMoney(Double.valueOf(data.getCommission())*50+"", 2) +"");
//            TextViewUtils.setTextAndleftOther(holder.goodNumber2, data.getUnit(), "元/");
            holder.goodShare2.setOnClickListener(this);
            holder.goodShare2.setTag(data);
        }
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        if (object instanceof HomeBean.DataEntity.AreaGoodsListListEntity) {
            HomeBean.DataEntity.AreaGoodsListListEntity data = (HomeBean.DataEntity.AreaGoodsListListEntity) object;
            ImageLoaderUtils.getInstance().display(holder.goodImage1, data.getImg1());
            TextViewUtils.setText(holder.goodName1, data.getGoodsName());
//            TextViewUtils.setText(holder.goodDesc1, data.getDescribe());
            TextViewUtils.setText(holder.goodDesc1,"￥"+ TextViewUtils.sprStringMoney(data.getPrice(), 2) +"/" + data.getUnit());
            TextViewUtils.setText(holder.goodPrice1,  TextViewUtils.sprStringMoney(Double.valueOf(data.getCommission())*50+"", 2) +"");
            holder.goodShare1.setOnClickListener(this);
            holder.goodShare1.setTag(data);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.linear1) {
            HomeBean.DataEntity.AreaGoodsListListEntity data = (HomeBean.DataEntity.AreaGoodsListListEntity) v.getTag();
            Intent intent = new Intent(context, GoodDetailActivity.class);
            intent.putExtra("goodId", Integer.valueOf(data.getGoodsId()));
            context.startActivity(intent);
        } else if (v.getId() == R.id.linear2) {
            HomeBean.DataEntity.AreaGoodsListListEntity data = (HomeBean.DataEntity.AreaGoodsListListEntity) v.getTag();
            Intent intent = new Intent(context, GoodDetailActivity.class);
            intent.putExtra("goodId", Integer.valueOf(data.getGoodsId()));
            context.startActivity(intent);
        } else {
            final HomeBean.DataEntity.AreaGoodsListListEntity data = (HomeBean.DataEntity.AreaGoodsListListEntity) v.getTag();
            ImageLoaderUtils.getInstance().display(data.getImg1(), new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    List<String> list = new ArrayList<String>();
                    addString(list, data.getImg1());
                    ShareData shareData = new ShareData(loadedImage, list, data.getGoodsName(), data.getGoodsName(), data.getWebsite(), Integer.valueOf(data.getGoodsId()), 0,data.getPrice(),"0");
                    new ShareViewDialog(context, shareData).show();
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    ShareData shareData = new ShareData(BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_share_sf), data.getGoodsName(), data.getGoodsName(), data.getWebsite(), Integer.valueOf(data.getGoodsId()), 0,data.getPrice(),"0");
                    new ShareViewDialog(context, shareData).show();
                }
            });
        }
    }

    private void addString(List<String> list, String str) {
        if (str != null) {
            list.add(str);
        }
    }

    protected class ViewHolder {
        ImageView goodImage1, goodImage2;
        View goodShare1, goodShare2;
        TextView goodName1, goodName2;
        TextView goodDesc1, goodDesc2;
        TextView goodPrice1, goodPrice2;
        TextView goodNumber1, goodNumber2;
        View linear1, linear2;
    }
}
