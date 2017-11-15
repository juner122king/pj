package com.weisj.pj.adapter;

import android.content.Context;
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
import com.weisj.pj.bean.HomeBean;
import com.weisj.pj.bean.ShareData;
import com.weisj.pj.utils.ImageLoaderUtils;
import com.weisj.pj.utils.TextViewUtils;
import com.weisj.pj.view.dialog.ShareViewDialog;

import java.util.ArrayList;
import java.util.List;


public class ItemHomeGoodAdapter<T> extends BaseAdapter implements View.OnClickListener {

    private List<T> objects;
    private Context context;
    private LayoutInflater layoutInflater;

    public ItemHomeGoodAdapter(Context context, List<T> list) {
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
            convertView = layoutInflater.inflate(R.layout.item_home_good_, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.goodImage = (ImageView) convertView.findViewById(R.id.good_image);
            viewHolder.goodShare = convertView.findViewById(R.id.good_share);
            viewHolder.goodName = (TextView) convertView.findViewById(R.id.good_name);
            viewHolder.goodDel = (TextView) convertView.findViewById(R.id.good_del);
            viewHolder.goodDesc = (TextView) convertView.findViewById(R.id.good_desc);
            viewHolder.goodPrice = (TextView) convertView.findViewById(R.id.good_price);
            viewHolder.goodNumber = (TextView) convertView.findViewById(R.id.good_number);
            viewHolder.commission = (TextView) convertView.findViewById(R.id.commission);
            convertView.setTag(viewHolder);
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        if (object instanceof HomeBean.DataEntity.DistrictGoodsListEntity) {
            HomeBean.DataEntity.DistrictGoodsListEntity data = (HomeBean.DataEntity.DistrictGoodsListEntity) object;
            ImageLoaderUtils.getInstance().display(holder.goodImage, data.getImg1());
            TextViewUtils.setText(holder.goodName, data.getGoodsName());
            TextViewUtils.setText(holder.goodDesc, data.getDescribe());
            TextViewUtils.setText(holder.commission, TextViewUtils.sprStringMoney(Double.valueOf(data.getCommission())*50+"", 2) );
            TextViewUtils.setTextAndallOtherIsZero(holder.goodDel, TextViewUtils.sprStringMoney(data.getDelMoney()), "立减", "元");
            TextViewUtils.setText(holder.goodPrice, "￥"+TextViewUtils.sprStringMoney(data.getPrice())+"元");

            if (data.getMaxPrice()!="null" && data.getMaxPrice()!="" && data.getMaxPrice()!=null && !(data.getMaxPrice().contains(data.getPrice()))){
                holder.goodNumber.setVisibility(View.VISIBLE);
                TextViewUtils.setText(holder.goodNumber, "~~"+data.getMaxPrice()+"元");
            }else {
                holder.goodNumber.setVisibility(View.GONE);
            }

            holder.goodShare.setOnClickListener(this);
            holder.goodShare.setTag(data);
        }

    }

    @Override
    public void onClick(View v) {
        final HomeBean.DataEntity.DistrictGoodsListEntity data = (HomeBean.DataEntity.DistrictGoodsListEntity) v.getTag();

        ImageLoaderUtils.getInstance().display(data.getImg1(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                List<String> list = new ArrayList<String>();
                addString(list, data.getImg1());
                addString(list, data.getImg2());
                addString(list, data.getImg3());
                addString(list, data.getImg4());
                addString(list, data.getImg5());
                addString(list, data.getImg6());
                addString(list, data.getImg7());
                ShareData shareData = new ShareData(loadedImage, list, data.getGoodsName(), data.getGoodsName(), data.getWebsite(), data.getGoodsId(), 0,data.getPrice(),TextViewUtils.sprStringMoney(data.getDelMoney()));
                new ShareViewDialog(context, shareData).show();
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                ShareData shareData = new ShareData(BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_share_sf), data.getGoodsName(), data.getGoodsName(), data.getWebsite(), data.getGoodsId(), 0,data.getPrice(),TextViewUtils.sprStringMoney(data.getDelMoney()));
                new ShareViewDialog(context, shareData).show();
            }
        });
    }

    private void addString(List<String> list, String str) {
        if (str != null) {
            list.add(str);
        }
    }

    protected class ViewHolder {
        ImageView goodImage;
        View goodShare;
        TextView goodName;
        TextView goodDesc;
        TextView goodDel;
        TextView goodPrice;
        TextView goodNumber;
        TextView commission;
    }
}
