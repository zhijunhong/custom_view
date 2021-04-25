package com.example.multi_recyclerview;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author: zhijunhong
 * created on: 2021/4/23 2:05 PM
 * version: 1.0
 * description:
 */
public abstract class BaseViewHolder<T extends Vistable> extends RecyclerView.ViewHolder {
    private SparseArray<View> mSparseArray;
    private View mItemView;
    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        mSparseArray = new SparseArray();
        mItemView = itemView;
    }

    public <T extends View> T getView(int resId) {
        View view = mSparseArray.get(resId);
        if (view == null) {
            view = mItemView.findViewById(resId);
        }
        return (T) view;
    }

    public abstract void bindViewData(T data, int position, Context context, MultiTypeAdapter adapter);
}
