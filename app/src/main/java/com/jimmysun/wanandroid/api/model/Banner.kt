package com.jimmysun.wanandroid.api.model

/**
 * Banner model
 * @author SunQiang
 * @since 2020-01-15
 */
data class Banner(var data: List<Data>? = null) {
    data class Data(
        var id: String? = null,
        var title: String? = null,
        var imagePath: String? = null,
        var url: String? = null
    )
}