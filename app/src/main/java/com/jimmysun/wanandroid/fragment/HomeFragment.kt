package com.jimmysun.wanandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimmysun.wanandroid.R
import com.jimmysun.wanandroid.adapter.OnBannerClickListener
import com.jimmysun.wanandroid.api.model.Banner
import com.jimmysun.wanandroid.base.recyclerview.OnHolderCreateListener
import com.jimmysun.wanandroid.base.recyclerview.SuperAdapter
import com.jimmysun.wanandroid.viewholder.ArticleViewHolder
import com.jimmysun.wanandroid.viewholder.HomeBannerViewHolder
import com.jimmysun.wanandroid.viewholder.LoadMoreItem
import com.jimmysun.wanandroid.viewholder.LoadMoreViewHolder
import com.jimmysun.wanandroid.viewmodel.HomeViewModel
import com.jimmysun.wanandroid.web.util.openUrl
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 首页
 * @author SunQiang
 * @since 2020-01-09
 */
class HomeFragment : Fragment() {
    private lateinit var homeAdapter: SuperAdapter
    private val loadMoreItem = LoadMoreItem()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.dataListLiveData.observe(this, Observer {
            it?.run {
                homeAdapter.data.remove(loadMoreItem)
                homeAdapter.notifyItemRemoved(homeAdapter.itemCount)
                if (refresh_layout.isRefreshing) {
                    homeAdapter.init()
                    refresh_layout.isRefreshing = false
                }
                homeAdapter.loadMore(this)
            }
        })
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            homeAdapter = SuperAdapter.Builder()
                .registerViewHolder(
                    HomeBannerViewHolder::class.java,
                    object : OnHolderCreateListener<HomeBannerViewHolder> {
                        override fun onCreate(viewHolder: HomeBannerViewHolder) {
                            viewHolder.setOnClickListener(object : OnBannerClickListener {
                                override fun onClick(data: Banner.Data) {
                                    openUrl(data.url, data.title)
                                }
                            })
                        }
                    })
                .registerViewHolder(
                    ArticleViewHolder::class.java,
                    object : OnHolderCreateListener<ArticleViewHolder> {
                        override fun onCreate(viewHolder: ArticleViewHolder) {
                            viewHolder.setOnItemClickListener { data, _ ->
                                openUrl(data.link, data.title)
                            }
                        }
                    })
                .registerViewHolder(LoadMoreViewHolder::class.java)
                .setLoadMoreOffset(10)
                .setOnPageChangeListener {
                    homeAdapter.data.add(loadMoreItem)
                    post {
                        homeAdapter.notifyItemInserted(homeAdapter.itemCount - 1)
                    }
                    homeViewModel.loadData(it)
                }
                .build()
            adapter = homeAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        refresh_layout.apply {
            setColorSchemeResources(R.color.colorPrimary, R.color.red01)
            isRefreshing = true
            setOnRefreshListener {
                homeViewModel.loadData(0)
            }
        }
    }
}
