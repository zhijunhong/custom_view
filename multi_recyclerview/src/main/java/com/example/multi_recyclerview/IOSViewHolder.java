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
public class IOSViewHolder extends BaseViewHolder {
    public IOSViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(Vistable data, int position, Context context, MultiTypeAdapter adapter) {
        ImageView iv = (ImageView) getView(R.id.iv_ios);
        iv.setImageResource(R.mipmap.d9067e3cc55bea4592d66a37507526dd);
    }
}
