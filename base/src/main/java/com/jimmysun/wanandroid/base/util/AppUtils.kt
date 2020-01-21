package com.jimmysun.wanandroid.base.util

import android.content.pm.ApplicationInfo
import com.jimmysun.wanandroid.base.app.BaseApplication

/**
 * 应用相关工具类
 * @author SunQiang
 * @since 2020-01-13
 */

fun isDebug(): Boolean =
    (BaseApplication.INSTANCE.applicationInfo?.flags ?: 0 and ApplicationInfo.FLAG_DEBUGGABLE) != 0