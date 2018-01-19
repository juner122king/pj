package com.weisj.pj.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.weisj.pj.R;
import com.weisj.pj.bean.CardTypeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 2018/1/19.
 */

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardTypeBean.DataEntity> mData;
    private float mBaseElevation;

    public CardPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void setCards(List<CardTypeBean.DataEntity> mData) {

        this.mData = mData;

        for (int i = 0; i < mData.size(); i++) {
            mViews.add(null);
        }

    }

    public void addCardItem(CardTypeBean.DataEntity item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.card_adapter, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(CardTypeBean.DataEntity item, View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.iv);
        String cardTypeId = item.getCardTypeId();
        Integer resourceId = 0;
        if (cardTypeId.equals("1")) {

            resourceId = R.drawable.cardid1;
        } else if (cardTypeId.equals("2")) {
            resourceId = R.drawable.cardid2;
        } else if (cardTypeId.equals("3")) {
            resourceId = R.drawable.cardid3;
        } else {
            resourceId = R.drawable.cardid44;
        }
        Glide.with(imageView.getContext())
                .load(resourceId)

                .crossFade()
                .fitCenter()
//                .transform(new GlideRoundTransform(getActivity(), 3))
                .into(imageView);




    }

}