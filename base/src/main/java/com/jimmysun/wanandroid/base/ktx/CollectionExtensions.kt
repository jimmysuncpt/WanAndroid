package com.jimmysun.wanandroid.base.ktx

/**
 * 集合扩展
 * @author SunQiang
 * @since 2020-02-01
 */

fun <E> MutableList<E>.removeLast() {
    removeAt(lastIndex)
}