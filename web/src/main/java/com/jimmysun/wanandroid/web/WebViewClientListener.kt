package com.jimmysun.wanandroid.web

import android.graphics.Bitmap

/**
 * @author SunQiang
 * @since 2020-01-20
 */
interface WebViewClientListener {
    fun onPageStarted(url: String?, favicon: Bitmap?)

    fun onPageFinished(url: String?)

    fun onReceivedError()

    fun onLoadUrlError()
}