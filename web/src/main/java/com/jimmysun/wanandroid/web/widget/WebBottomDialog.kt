package com.jimmysun.wanandroid.web.widget

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import com.jimmysun.wanandroid.base.widget.BottomDialog
import com.jimmysun.wanandroid.web.R
import kotlinx.android.synthetic.main.dialog_web_more.*

/**
 * Web 页底部弹窗
 * @author SunQiang
 * @since 2020-01-30
 */
class WebBottomDialog(context: Context) : BottomDialog(context) {
    init {
        setContentView(R.layout.dialog_web_more)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.attributes?.run {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
            gravity = Gravity.BOTTOM
        }
        btn_cancel.setOnClickListener {
            dismiss()
        }
    }

    fun setOnReloadClickListener(onClick: () -> Unit) {
        iv_reload.setOnClickListener {
            onClick()
        }
    }

    fun setOnCopyClickListener(onClick: () -> Unit) {
        iv_copy.setOnClickListener {
            onClick()
        }
    }

    fun setOnBrowserClickListener(onClick: () -> Unit) {
        iv_browser.setOnClickListener {
            onClick()
        }
    }
}