package com.jimmysun.wanandroid.base.recyclerview;

import androidx.annotation.NonNull;

/**
 * TODO
 *
 * @author SunQiang
 * @since 2020-01-19
 */
public class ViewHolderEntry {
    private Class<? extends SuperViewHolder> mClass;
    private OnHolderCreateListener mOnCreateListener;

    public ViewHolderEntry(Class<? extends SuperViewHolder> clazz,
                           OnHolderCreateListener onCreateListener) {
        mClass = clazz;
        mOnCreateListener = onCreateListener;
    }

    @NonNull
    public Class<? extends SuperViewHolder> getHolderClass() {
        return mClass;
    }

    public OnHolderCreateListener getOnCreateListener() {
        return mOnCreateListener;
    }
}
