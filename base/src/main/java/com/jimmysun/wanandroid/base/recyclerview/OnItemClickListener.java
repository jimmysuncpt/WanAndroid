package com.jimmysun.wanandroid.base.recyclerview;

import androidx.annotation.NonNull;

/**
 * TODO
 *
 * @author SunQiang
 * @since 2020-01-19
 */
public interface OnItemClickListener<T> {
    void onClick(@NonNull T data, int position);
}
