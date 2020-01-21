package com.jimmysun.wanandroid.viewholder

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.jimmysun.wanandroid.R
import com.jimmysun.wanandroid.api.model.Article
import com.jimmysun.wanandroid.base.ktx.dp
import com.jimmysun.wanandroid.base.recyclerview.Layout
import com.jimmysun.wanandroid.base.recyclerview.SuperViewHolder
import com.jimmysun.wanandroid.widget.TagView
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * 首页文章的 view holder
 * @author SunQiang
 * @since 2020-01-18
 */
@Layout(R.layout.item_article)
class ArticleViewHolder(view: View) : SuperViewHolder<Article>(view) {
    private val tagsLayout: LinearLayout = view.layout_tags
    private val authorTextView: TextView = view.tv_author
    private val titleTextView: TextView = view.tv_title
    private val chapterTextView: TextView = view.tv_chapter
    private val dateTextView: TextView = view.tv_date
    private val collectImageView: ImageView = view.iv_collect

    override fun onBind(data: Article, position: Int) {
        super.onBind(data, position)
        val context = itemView.context
        tagsLayout.removeAllViews()
        if (data.top) {
            val tagView = TagView(context).color(TagView.Color.COLOR_RED).text("置顶")
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                rightMargin = 4.dp
            }
            tagsLayout.addView(tagView, layoutParams)
        }
        if (data.fresh) {
            val tagView = TagView(context).color(TagView.Color.COLOR_RED).text("新")
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                rightMargin = 4.dp
            }
            tagsLayout.addView(tagView, layoutParams)
        }
        data.tags?.run {
            forEach {
                if (TextUtils.isEmpty(it.name)) {
                    return@forEach
                }
                val tagView = TagView(context).text(it.name)
                val layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    rightMargin = 4.dp
                }
                tagsLayout.addView(tagView, layoutParams)
            }
        }
        authorTextView.text = if (!TextUtils.isEmpty(data.author)) {
            "作者：${data.author}"
        } else if (!TextUtils.isEmpty(data.shareUser)) {
            "分享人：${data.shareUser}"
        } else ""
        titleTextView.text = HtmlCompat.fromHtml(data.title ?: "", 0)
        val chapterStringBuilder = StringBuilder()
        if (TextUtils.isEmpty(data.superChapterName)) {
            if (!TextUtils.isEmpty(data.chapterName)) {
                chapterStringBuilder.append(data.chapterName)
            }
        } else {
            chapterStringBuilder.append(data.superChapterName)
            if (!TextUtils.isEmpty(data.chapterName)) {
                chapterStringBuilder.append(" / ${data.chapterName}")
            }
        }
        chapterTextView.text = chapterStringBuilder.toString()
        dateTextView.text = if (chapterStringBuilder.isEmpty()) {
            data.niceDate
        } else {
            " · ${data.niceDate}"
        }
        updateCollectImageView(data.collect)
        collectImageView.setOnClickListener {
            data.collect = !data.collect
            updateCollectImageView(data.collect)
        }
    }

    private fun updateCollectImageView(collect: Boolean) {
        collectImageView.setImageResource(if (collect) R.drawable.ic_collect_enable else R.drawable.ic_collect_disable)
    }
}