package com.weisj.pj.adapter;

import android.support.v7.widget.CardView;

/**
 * Created by jun on 2018/1/19.
 */

public interface CardAdapter {
    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
