package com.jimmysun.wanandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.jimmysun.wanandroid.R
import com.jimmysun.wanandroid.api.model.Banner
import com.jimmysun.wanandroid.base.ktx.load
import kotlinx.android.synthetic.main.item_banner.view.*

/**
 * @author SunQiang
 * @since 2020-01-15
 */
class BannerAdapter : RecyclerView.Adapter<BannerViewHolder>() {
    val bannerList = mutableListOf<Banner.Data>()
    var onBannerClickListener: OnBannerClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return BannerViewHolder(rootView)
    }

    override fun getItemCount(): Int = bannerList.size

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = bannerList[position]
        holder.imageView.load(banner.imagePath)
        holder.titleTextView.text = HtmlCompat.fromHtml(banner.title ?: "", 0)
        onBannerClickListener?.run {
            holder.itemView.setOnClickListener {
                onClick(banner)
            }
        }
    }
}

class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.iv_banner
    val titleTextView: TextView = view.tv_title
}

interface OnBannerClickListener {
    fun onClick(data: Banner.Data)
}