package com.weisj.pj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weisj.pj.R;
import com.weisj.pj.bean.HomeBean;
import com.weisj.pj.utils.ImageLoaderUtils;
import com.weisj.pj.utils.TextViewUtils;

import java.util.List;


public class ItemHomeGoodAdapter<T> extends BaseAdapter {

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
            convertView = layoutInflater.inflate(R.layout.item_category, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.goodImage = (ImageView) convertView.findViewById(R.id.iv);
            viewHolder.goodName = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.goodPrice = (TextView) convertView.findViewById(R.id.tv_use_number);
            convertView.setTag(viewHolder);
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        if (object instanceof HomeBean.DataEntity.DistrictGoodsListEntity) {
            HomeBean.DataEntity.DistrictGoodsListEntity data = (HomeBean.DataEntity.DistrictGoodsListEntity) object;
            ImageLoaderUtils.getInstance().display(holder.goodImage, data.getImg1());
            TextViewUtils.setText(holder.goodName, data.getGoodsName());
            TextViewUtils.setText(holder.goodPrice, "￥" + TextViewUtils.sprStringMoney(data.getPrice()) + "元");
        }

    }


    protected class ViewHolder {
        ImageView goodImage;
        TextView goodName;
        TextView goodPrice;
    }
}
