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
public class WinPhoneViewHolder extends BaseViewHolder {
    public WinPhoneViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(Vistable data, int position, Context context, MultiTypeAdapter adapter) {
        ImageView ivWinPhone = (ImageView) getView(R.id.iv_win_phone);
        ivWinPhone.setImageResource(R.mipmap.images);
    }
}
