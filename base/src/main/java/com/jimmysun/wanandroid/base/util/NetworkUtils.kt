package com.jimmysun.wanandroid.base.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * 网络工具类
 * @author SunQiang
 * @since 2020-02-05
 */

fun isNetworkAvailable(context: Context): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    manager ?: return false
    val networkInfo = manager.activeNetworkInfo
    networkInfo ?: return false
    return networkInfo.isAvailable
}