package com.jimmysun.wanandroid.web

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest

/**
 * @author SunQiang
 * @since 2020-01-20
 */
interface WebViewClientListener {
    fun onPageStarted(url: String?, favicon: Bitmap?)
    fun onPageFinished(url: String?)
    fun onReceivedError(request: WebResourceRequest?, error: WebResourceError?)
}