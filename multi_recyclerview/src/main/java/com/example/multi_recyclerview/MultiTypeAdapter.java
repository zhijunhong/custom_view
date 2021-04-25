package com.example.multi_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * author: zhijunhong
 * created on: 2021/4/23 2:00 PM
 * version: 1.0
 * description:
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<Vistable> modules;
    private TypeFactory factory;
    protected Context mContext;

    public MultiTypeAdapter(List<Vistable> modules, Context context) {
        this.modules = modules;
        mContext = context;
        factory = new TypeFactoryList();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(viewType, parent, false);
        return factory.createViewHolder(viewType, view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindViewData(modules.get(position), position, mContext, this);
    }

    @Override
    public int getItemCount() {
        return modules==null?0:modules.size();
    }

    @Override
    public int getItemViewType(int position) {
        return modules.get(position).type(factory);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager==null || !(layoutManager instanceof GridLayoutManager)) return;

        ((GridLayoutManager)layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (getItemViewType(position) == R.layout.android_layout_item) {
                    return 2;                                                                       //占用2列
                } else if (getItemViewType(position) == R.layout.ios_layout_item) {
                    return 2;                                                                       //占用2列
                }
                return 1;
            }
        });
    }
}
