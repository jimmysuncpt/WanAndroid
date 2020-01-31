package com.jimmysun.wanandroid.base.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.jimmysun.wanandroid.base.R
import kotlinx.android.synthetic.main.title_bar.view.*

/**
 * 标题栏
 * @author SunQiang
 * @since 2020-01-20
 */
class TitleBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.title_bar, this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar)
        setMoreVisible(typedArray.getBoolean(R.styleable.TitleBar_moreVisible, false))
        typedArray.recycle()
        setBackgroundColor(Color.WHITE)
    }

    fun setTitle(title: CharSequence) {
        tv_title.text = title
    }

    fun setOnBackClickListener(onClick: (View) -> Unit) {
        iv_back.setOnClickListener {
            onClick(it)
        }
    }

    fun setMoreVisible(visible: Boolean) {
        iv_more.visibility = if (visible) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    fun setOnMoreClickListener(onClick: (View) -> Unit) {
        iv_more.setOnClickListener {
            onClick(it)
        }
    }
}