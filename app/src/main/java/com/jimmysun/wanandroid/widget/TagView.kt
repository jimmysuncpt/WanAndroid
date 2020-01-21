package com.jimmysun.wanandroid.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.jimmysun.wanandroid.R
import com.jimmysun.wanandroid.base.ktx.dp

/**
 * 标签 View
 * @author SunQiang
 * @since 2020-01-14
 */
class TagView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    TextView(context, attrs, defStyleAttr) {
    enum class Color {
        COLOR_YELLOW, COLOR_RED
    }

    init {
        setPadding(4.dp, 1.dp, 4.dp, 1.dp)
        setBackgroundResource(R.drawable.bg_tag_yellow)
        setTextColor(ContextCompat.getColor(context, R.color.yellow01))
        textSize = 9f
    }

    /**
     * 设置边框与字体颜色
     */
    fun color(color: Color): TagView {
        when (color) {
            Color.COLOR_YELLOW -> {
                setBackgroundResource(R.drawable.bg_tag_yellow)
                setTextColor(ContextCompat.getColor(context, R.color.yellow01))
            }
            Color.COLOR_RED -> {
                setBackgroundResource(R.drawable.bg_tag_red)
                setTextColor(ContextCompat.getColor(context, R.color.red01))
            }
        }
        return this
    }

    /**
     * 设置文字
     */
    fun text(text: CharSequence?): TagView {
        this.text = text
        return this
    }
}