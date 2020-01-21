package com.jimmysun.wanandroid.api

import com.jimmysun.wanandroid.api.service.HomeService
import com.jimmysun.wanandroid.base.net.Net

/**
 * 网络请求 API
 * @author SunQiang
 * @since 2020-01-09
 */
object WanApi {
    val homeService by lazy {
        Net.createService(HomeService::class.java)
    }
}