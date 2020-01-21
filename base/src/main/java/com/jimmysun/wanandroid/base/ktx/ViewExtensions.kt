package com.jimmysun.wanandroid.base.ktx

import android.widget.ImageView
import com.jimmysun.wanandroid.base.glide.GlideApp

/**
 * View 扩展
 * @author SunQiang
 * @since 2020-01-15
 */

fun ImageView.load(string: String?) {
    string ?: return
    GlideApp.with(this)
        .load(string)
        .into(this)
}