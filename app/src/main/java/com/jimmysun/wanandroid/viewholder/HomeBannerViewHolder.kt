package com.jimmysun.wanandroid.viewholder

import android.view.View
import android.view.ViewGroup
import com.jimmysun.ultrarecyclerview.BannerView
import com.jimmysun.wanandroid.R
import com.jimmysun.wanandroid.adapter.BannerAdapter
import com.jimmysun.wanandroid.adapter.OnBannerClickListener
import com.jimmysun.wanandroid.api.model.Banner
import com.jimmysun.wanandroid.base.recyclerview.Layout
import com.jimmysun.wanandroid.base.recyclerview.SuperViewHolder
import kotlinx.android.synthetic.main.item_home_banner.view.*

/**
 * 首页 banner 的 view holder
 * @author SunQiang
 * @since 2020-01-18
 */
@Layout(R.layout.item_home_banner)
class HomeBannerViewHolder(view: View) : SuperViewHolder<BannerItem>(view) {
    private val bannerView: BannerView = view.banner_view
    private val bannerAdapter = BannerAdapter()

    fun setOnClickListener(onBannerClickListener: OnBannerClickListener) {
        bannerAdapter.onBannerClickListener = onBannerClickListener
    }

    override fun onCreate(parent: ViewGroup) {
        bannerView.setAdapter(bannerAdapter)
    }

    override fun onBind(data: BannerItem, position: Int) {
        super.onBind(data, position)
        bannerAdapter.bannerList.clear()
        bannerAdapter.bannerList.addAll(data.banners)
        bannerView.startAutoScroll(3000)
    }
}

class BannerItem(val banners: List<Banner.Data>)