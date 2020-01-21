package com.jimmysun.wanandroid.repository

import androidx.lifecycle.MutableLiveData
import com.jimmysun.wanandroid.api.WanApi
import com.jimmysun.wanandroid.viewholder.BannerItem

/**
 * 首页数据仓库
 * @author SunQiang
 * @since 2020-01-13
 */
class HomeRepository {
    val dataListLiveData = MutableLiveData<List<Any>>()

    suspend fun loadData(page: Int) {
        if (page < 0) {
            return
        }
        val dataList = mutableListOf<Any>()
        if (page == 0) {
            WanApi.homeService.getBanner().data?.run {
                dataList.add(BannerItem(this))
            }
            WanApi.homeService.getTopArticle().data?.forEach {
                it.top = true
                dataList.add(it)
            }
        }
        WanApi.homeService.getArticleList(page).data?.datas?.run {
            dataList.addAll(this)
        }
        dataListLiveData.postValue(dataList)
    }
}