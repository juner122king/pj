package com.weisj.pj.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.weisj.pj.R;
import com.weisj.pj.bean.UsershareBean;
import com.weisj.pj.utils.ImageLoaderUtils;

import java.util.List;

/**
 * Created by jun on 2017/11/21.
 */

public class ItemHomeUserCommentAdapter extends BaseAdapter {

    private List<UsershareBean> objects;
    private Context context;
    private LayoutInflater layoutInflater;

    public ItemHomeUserCommentAdapter(List<UsershareBean> objects, Context context) {
        this.objects = objects;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public UsershareBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ItemHomeUserCommentAdapter.ViewHolder viewHolder = new ItemHomeUserCommentAdapter.ViewHolder();
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_home_user_im, null);
            viewHolder.user_head = (ImageView) convertView.findViewById(R.id.iv_head_pic);
            viewHolder.tv_username = (TextView) convertView.findViewById(R.id.tv_username);
            viewHolder.tv_usercomment = (TextView) convertView.findViewById(R.id.tv_user_comment);

            convertView.setTag(viewHolder);
        }


        ImageLoaderUtils.getInstance().display(viewHolder.user_head, objects.get(position).getUser_head());


        return convertView;
    }


    protected class ViewHolder {

        ImageView user_head;
        TextView tv_username;
        TextView tv_usercomment;
        ListView listView;
    }

}
