package com.jimmysun.wanandroid.api.service

import com.jimmysun.wanandroid.api.model.ArticleList
import com.jimmysun.wanandroid.api.model.Banner
import com.jimmysun.wanandroid.api.model.TopArticle
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 首页接口
 * @author SunQiang
 * @since 2020-01-09
 */
interface HomeService {
    @GET("/banner/json")
    suspend fun getBanner(): Banner

    @GET("/article/top/json")
    suspend fun getTopArticle(): TopArticle

    @GET("/article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): ArticleList
}