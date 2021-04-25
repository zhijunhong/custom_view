package com.example.multi_recyclerview;

import android.view.View;

/**
 * author: zhijunhong
 * created on: 2021/4/25 10:49 AM
 * version: 1.0
 * description:
 */
public class TypeFactoryList implements TypeFactory{
    public static final int ANDROID_LAYOUT = R.layout.android_layout_item;
    public static final int WIN_PHONE_LAYOUT = R.layout.win_phone_layout;
    public static final int IOS_LAYOUT = R.layout.ios_layout_item;

    @Override
    public int type(AndroidBean androidData) {
        return ANDROID_LAYOUT;
    }

    @Override
    public int type(WinPhoneBean beautifulGirlBean) {
        return WIN_PHONE_LAYOUT;
    }

    @Override
    public int type(IOSBean iosBean) {
        return IOS_LAYOUT;
    }

    @Override
    public BaseViewHolder createViewHolder(int type, View itemView) {
        switch (type) {
            case ANDROID_LAYOUT:
                return new AndroidViewHolder(itemView);
            case WIN_PHONE_LAYOUT:
                return new WinPhoneViewHolder(itemView);
            case IOS_LAYOUT:
                return new IOSViewHolder(itemView);
        }
        return null;
    }
}
