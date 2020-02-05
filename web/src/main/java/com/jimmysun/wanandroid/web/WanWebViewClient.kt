package com.jimmysun.wanandroid.web

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.webkit.*
import androidx.annotation.RequiresApi
import com.jimmysun.wanandroid.base.app.BaseApplication
import com.jimmysun.wanandroid.base.net.CODE_404
import com.jimmysun.wanandroid.base.net.CODE_500
import com.jimmysun.wanandroid.base.net.SCHEME_HTTP
import com.jimmysun.wanandroid.base.net.SCHEME_HTTPS

/**
 * @author SunQiang
 * @since 2020-01-20
 */
class WanWebViewClient(private val listener: WebViewClientListener? = null) : WebViewClient() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean =
        shouldOverrideUrlLoading(view, request.url)

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean =
        shouldOverrideUrlLoading(view, Uri.parse(url))

    private fun shouldOverrideUrlLoading(view: WebView, uri: Uri): Boolean {
        when (uri.scheme) {
            SCHEME_HTTP -> view.loadUrl(uri.buildUpon().scheme(SCHEME_HTTPS).toString())
            SCHEME_HTTPS -> return false
            else -> {
                val intent = Intent(Intent.ACTION_VIEW, uri)
                if (intent.resolveActivity(BaseApplication.INSTANCE.packageManager) != null) {
                    BaseApplication.topActivity.startActivity(intent)
                } else {
                    listener?.onLoadUrlError()
                }
            }
        }
        return true
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        listener?.onPageStarted(url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        listener?.onPageFinished(url)
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onReceivedError(
        view: WebView,
        request: WebResourceRequest,
        error: WebResourceError
    ) {
        super.onReceivedError(view, request, error)
        if (error.errorCode == ERROR_HOST_LOOKUP || error.errorCode == ERROR_CONNECT || error.errorCode == ERROR_TIMEOUT) {
            listener?.onReceivedError()
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onReceivedHttpError(
        view: WebView,
        request: WebResourceRequest?,
        errorResponse: WebResourceResponse
    ) {
        when (errorResponse.statusCode) {
            CODE_404, CODE_500 -> {
                listener?.onReceivedError()
            }
        }
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        handler?.proceed()
    }
}