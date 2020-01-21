package com.jimmysun.wanandroid.base.util

import android.os.Handler
import android.os.Looper
import android.widget.Toast.*
import androidx.annotation.StringRes
import com.jimmysun.wanandroid.base.app.BaseApplication

/**
 * Toast 工具类
 * @author SunQiang
 * @since 2020-01-10
 */
private val mainHandler = Handler(Looper.getMainLooper())

fun toast(text: CharSequence?) {
    text ?: return
    if (Looper.myLooper() == Looper.getMainLooper()) {
        showToast(text, LENGTH_SHORT)
    } else {
        mainHandler.post {
            showToast(text, LENGTH_SHORT)
        }
    }
}

fun toast(@StringRes resId: Int?) {
    resId ?: return
    if (Looper.myLooper() == Looper.getMainLooper()) {
        showToast(resId, LENGTH_SHORT)
    } else {
        mainHandler.post {
            showToast(resId, LENGTH_SHORT)
        }
    }
}

fun longToast(text: CharSequence) {
    text ?: return
    if (Looper.myLooper() == Looper.getMainLooper()) {
        showToast(text, LENGTH_LONG)
    } else {
        mainHandler.post {
            showToast(text, LENGTH_LONG)
        }
    }
}

fun longToast(@StringRes resId: Int) {
    resId ?: return
    if (Looper.myLooper() == Looper.getMainLooper()) {
        showToast(resId, LENGTH_LONG)
    } else {
        mainHandler.post {
            showToast(resId, LENGTH_LONG)
        }
    }
}

private fun showToast(text: CharSequence, duration: Int) {
    makeText(BaseApplication.INSTANCE, text, duration).show()
}

private fun showToast(@StringRes resId: Int, duration: Int) {
    makeText(BaseApplication.INSTANCE, resId, duration).show()
}