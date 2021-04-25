package com.example.multi_recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

/**
 * author: zhijunhong
 * created on: 2021/4/25 10:57 AM
 * version: 1.0
 * description:
 */
public class AndroidViewHolder extends BaseViewHolder {
    public AndroidViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(Vistable data, int position, Context context, MultiTypeAdapter adapter) {
        ImageView iv = (ImageView) getView(R.id.iv_android);
        iv.setImageResource(R.mipmap.android_logo);
    }
}
