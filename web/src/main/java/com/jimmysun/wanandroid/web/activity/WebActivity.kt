package com.jimmysun.wanandroid.web.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import com.jimmysun.wanandroid.base.activity.BaseActivity
import com.jimmysun.wanandroid.base.net.SCHEME_HTTP
import com.jimmysun.wanandroid.base.net.SCHEME_HTTPS
import com.jimmysun.wanandroid.base.util.toast
import com.jimmysun.wanandroid.web.*
import com.jimmysun.wanandroid.web.widget.WebBottomDialog
import kotlinx.android.synthetic.main.activity_web.*


class WebActivity : BaseActivity(), WebViewClientListener, WebChromeClientListener {
    companion object {
        const val EXTRA_URL = "url"
    }

    private var url: String? = ""

    private lateinit var webView: WebView
    private var currentUrl: String? = ""

    private val bottomDialog by lazy {
        WebBottomDialog(this).apply {
            setOnReloadClickListener {
                webView.reload()
                dismiss()
            }
            setOnCopyClickListener {
                val clipboardManager =
                    getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("WanAndroid", currentUrl)
                clipboardManager.setPrimaryClip(clipData)
                toast("复制成功")
                dismiss()
            }
            setOnBrowserClickListener {
                val uri = Uri.parse(currentUrl)
                startActivity(Intent(Intent.ACTION_VIEW, uri).apply {
                    addCategory(Intent.CATEGORY_BROWSABLE)
                })
                dismiss()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        intent.run {
            url = intent.getStringExtra(EXTRA_URL)
            if (TextUtils.isEmpty(url)) {
                return@onCreate
            }
            val uri = Uri.parse(url)
            if (uri.scheme == SCHEME_HTTP) {
                url = uri.buildUpon().scheme(SCHEME_HTTPS).toString()
            }
            currentUrl = url
        }
        initTitleBar()
        initWebView()
        iv_reload.setOnClickListener {
            group_reload.isVisible = false
            webView.isVisible = true
            webView.reload()
        }
    }

    private fun initTitleBar() {
        title_bar.apply {
            setOnBackClickListener {
                onBackPressed()
            }
            setOnMoreClickListener {
                bottomDialog.show()
            }
        }
    }

    private fun initWebView() {
        webView = web_view.apply {
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
            setDownloadListener { _, _, _, _, _ ->
                group_browser_guide.isVisible = true
            }
            loadUrl(this@WebActivity.url)
        }
    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {
        currentUrl = url
        progress_bar.isVisible = true
    }

    override fun onPageFinished(url: String?) {
        progress_bar.isVisible = false
    }

    override fun onReceivedError() {
        title_bar.setTitle(ERROR_PAGE_MSG)
        webView.isVisible = false
        group_reload.isVisible = true
    }

    override fun onLoadUrlError() {
        group_browser_guide.isVisible = true
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
        title_bar.setTitle(HtmlCompat.fromHtml(title ?: "网页", 0))
    }

    override fun onBackPressed() {
        if (group_reload.isVisible) {
            group_reload.isVisible = false
        }
        when {
            group_browser_guide.isVisible -> group_browser_guide.isVisible = false
            webView.canGoBack() -> webView.goBack()
            else -> super.onBackPressed()
        }
    }

    override fun onDestroy() {
        webView.run {
            loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            clearHistory()
            (parent as ViewGroup).removeView(this)
            destroy()
        }
        super.onDestroy()
    }
}
