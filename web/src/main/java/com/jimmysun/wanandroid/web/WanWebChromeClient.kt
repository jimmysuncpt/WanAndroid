package com.jimmysun.wanandroid.web

import android.webkit.WebChromeClient
import android.webkit.WebView

/**
 * @author SunQiang
 * @since 2020-01-20
 */
class WanWebChromeClient(private val listener: WebChromeClientListener? = null) :
    WebChromeClient() {
    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        listener?.onProgressChanged(newProgress)
    }

    override fun onReceivedTitle(view: WebView?, title: String?) {
        listener?.onReceivedTitle(title)
    }
}