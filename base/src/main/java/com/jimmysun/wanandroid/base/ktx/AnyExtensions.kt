package com.jimmysun.wanandroid.base.ktx

import android.util.Log
import com.jimmysun.wanandroid.base.util.isDebug

/**
 * Any 类扩展
 * @author SunQiang
 * @since 2020-01-13
 */

fun Any.logV(msg: String?, throwable: Throwable? = null) {
    if (isDebug()) {
        Log.v(this::class.simpleName, msg, throwable)
    }
}

fun Any.logD(msg: String?, throwable: Throwable? = null) {
    if (isDebug()) {
        Log.d(this::class.simpleName, msg, throwable)
    }
}

fun Any.logI(msg: String?, throwable: Throwable? = null) {
    if (isDebug()) {
        Log.i(this::class.simpleName, msg, throwable)
    }
}

fun Any.logW(msg: String?, throwable: Throwable? = null) {
    if (isDebug()) {
        Log.w(this::class.simpleName, msg, throwable)
    }
}

fun Any.logE(msg: String?, throwable: Throwable? = null) {
    if (isDebug()) {
        Log.e(this::class.simpleName, msg, throwable)
    }
}
