package com.jimmysun.wanandroid.web.activity

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import com.jimmysun.wanandroid.base.activity.BaseActivity
import com.jimmysun.wanandroid.base.util.toast
import com.jimmysun.wanandroid.web.*
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity(), WebViewClientListener, WebChromeClientListener {
    companion object {
        const val EXTRA_URL = "url"
        const val EXTRA_TITLE = "title"
    }

    private var url: String? = ""
    private var title: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        title_bar.setOnBackClickListener {
            onBackPressed()
        }
        intent.run {
            url = intent.getStringExtra(EXTRA_URL)
            title = intent.getStringExtra(EXTRA_TITLE)
        }
        if (TextUtils.isEmpty(url)) {
            return
        }
        if (!TextUtils.isEmpty(title)) {
            title_bar.setTitle(HtmlCompat.fromHtml(title!!, 0))
        }
        web_view.apply {
            loadUrl(this@WebActivity.url)
            settings.apply {
                useWideViewPort = true // 将图片调整到适合 WebView 的大小
                loadWithOverviewMode = true // 缩放至屏幕的大小
                setAppCacheEnabled(true) // 启用应用缓存
                domStorageEnabled = true // 启用 DOM 缓存
                databaseEnabled = true
                cacheMode = WebSettings.LOAD_DEFAULT // WebView 缓存
                loadsImagesAutomatically = true // 支持自动加载图片
                defaultTextEncodingName = "utf-8" // 设置编码格式
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // 允许从任何来源加载内容，即使起源是不安全的
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }
            }
            webViewClient = WanWebViewClient(this@WebActivity)
            webChromeClient = WanWebChromeClient(this@WebActivity)
        }
    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {
        progress_bar.isVisible = true
    }

    override fun onPageFinished(url: String?) {
        progress_bar.isVisible = false
    }

    override fun onReceivedError(request: WebResourceRequest?, error: WebResourceError?) {
        toast("加载出错")
    }

    override fun onProgressChanged(progress: Int) {
        progress_bar.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setProgress(progress, true)
            } else {
                setProgress(progress)
            }
            if (progress == 100) {
                isVisible = false
            }
        }
    }

    override fun onReceivedTitle(title: String?) {
        if (TextUtils.isEmpty(this.title)) {
            title_bar.setTitle(HtmlCompat.fromHtml(title ?: "网页", 0))
        }
    }

    override fun onBackPressed() {
        if (web_view.canGoBack()) {
            web_view.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        web_view.run {
            loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            clearHistory()
            (parent as ViewGroup).removeView(this)
            destroy()
        }
        super.onDestroy()
    }
}
