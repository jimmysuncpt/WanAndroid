package com.jimmysun.wanandroid.web

/**
 * @author SunQiang
 * @since 2020-01-20
 */
interface WebChromeClientListener {
    fun onProgressChanged(progress: Int)
    fun onReceivedTitle(title: String?)
}