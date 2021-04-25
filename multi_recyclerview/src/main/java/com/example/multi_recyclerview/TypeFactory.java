package com.example.multi_recyclerview;

import android.view.View;

/**
 * author: zhijunhong
 * created on: 2021/4/23 2:13 PM
 * version: 1.0
 * description:
 */
public interface TypeFactory {
    int type(AndroidBean androidData);

    int type(WinPhoneBean winPhoneBean);

    int type(IOSBean iosBean);

    BaseViewHolder createViewHolder(int type, View itemView);

}
