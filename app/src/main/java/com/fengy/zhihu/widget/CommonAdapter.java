package com.fengy.zhihu.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by fengyun on 2016/10/28.
 * 公共adapter
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private int mLayoutId;
    private List<T> mDatas;
    private LayoutInflater mInflater;

    public CommonAdapter(Context context, int layoutId, List<T> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    public void refreshData(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<T> datas) {
        if (mDatas == null) {
            refreshData(datas);
        } else {
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.createViewHolder(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mDatas.get(position),position);
    }

    public abstract void convert(ViewHolder holder, T t, int i);

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }
}
