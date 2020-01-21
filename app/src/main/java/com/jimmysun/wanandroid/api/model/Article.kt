package com.jimmysun.wanandroid.api.model

/**
 * 文章 model
 * @author SunQiang
 * @since 2020-01-14
 */
data class Article(
    var id: String? = null,
    var title: String? = null,
    var userId: String? = null,
    var author: String? = null,
    var shareUser: String? = null,
    var superChapterId: String? = null, // 父分类 id
    var superChapterName: String? = null, // 父分类
    var chapterId: String? = null, // 分类 id
    var chapterName: String? = null, // 分类
    var collect: Boolean = false, // 收藏
    var fresh: Boolean = false, // 新
    var link: String? = null,
    var publishTime: Long = 0L,
    var niceDate: String? = null,
    var tags: List<Tag>? = null,
    var top: Boolean = false
)