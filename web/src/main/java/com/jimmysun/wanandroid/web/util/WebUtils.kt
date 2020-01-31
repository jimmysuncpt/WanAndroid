package com.jimmysun.wanandroid.web.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.jimmysun.wanandroid.web.activity.WebActivity

/**
 * 网页工具类
 * @author SunQiang
 * @since 2020-01-20
 */
fun openUrl(context: Context?, url: String?) {
    if (context == null || TextUtils.isEmpty(url)) {
        return
    }
    val intent = Intent(context, WebActivity::class.java)
    intent.putExtra(WebActivity.EXTRA_URL, url)
    context.startActivity(intent)
}

fun Activity.openUrl(url: String?) {
    openUrl(this, url)
}

fun Fragment.openUrl(url: String?) {
    openUrl(context, url)
}