package com.jimmysun.wanandroid.base.widget

import android.app.Dialog
import android.content.Context
import com.jimmysun.wanandroid.base.R

/**
 * @author SunQiang
 * @since 2020-01-30
 */
open class BaseDialog(context: Context) : Dialog(context, R.style.BaseDialog) {

    override fun dismiss() {
        try {
            super.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}