package com.jimmysun.wanandroid.api.model

/**
 * 首页文章列表
 * @author SunQiang
 * @since 2020-01-09
 */
data class ArticleList(
    var data: Data? = null
) : BaseModel() {
    data class Data(
        var datas: List<Article>? = null
    )
}