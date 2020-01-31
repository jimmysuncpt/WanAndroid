package com.jimmysun.wanandroid.base.widget

import android.content.Context
import android.os.Bundle
import com.jimmysun.wanandroid.base.R

/**
 * @author SunQiang
 * @since 2020-01-30
 */
open class BottomDialog(context: Context) : BaseDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setWindowAnimations(R.style.BottomDialogAnim)
    }
}