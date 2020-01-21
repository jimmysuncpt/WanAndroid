package com.jimmysun.wanandroid.base.recyclerview;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * The base view holder for all kinds of data.
 *
 * @author SunQiang
 * @since 2020-01-16
 */
public class SuperViewHolder<T> extends RecyclerView.ViewHolder {
    private OnItemClickListener<T> mOnItemClickListener;

    public SuperViewHolder(View itemView) {
        super(itemView);
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void onCreate(@NonNull ViewGroup parent) {
    }

    @CallSuper
    public void onBind(@NonNull T data, int position) {
        if (mOnItemClickListener != null) {
            itemView.setOnClickListener(v -> mOnItemClickListener.onClick(data, position));
        }
    }

    public void onViewRecycled() {
    }

    public boolean onFailedToRecycleView() {
        return false;
    }

    public void onViewAttachedToWindow() {
    }

    public void onViewDetachedFromWindow() {
    }
}
