@file:JvmName("NumberUtils")

package com.jimmysun.wanandroid.base.ktx

import android.content.res.Resources
import kotlin.math.round

/**
 * 为 UI 扩展 Number 的方法
 * @author SunQiang
 * @since 2020-01-07
 */

/**
 * dp 转 px
 */
val Number.dp @JvmName("dp2px") get() = round(toFloat() * Resources.getSystem().displayMetrics.density).toInt()

/**
 * sp 转 px
 */
val Number.sp @JvmName("sp2px") get() = round(toFloat() * Resources.getSystem().displayMetrics.scaledDensity).toInt()

/**
 * px 转 dp
 */
val Number.px2dp @JvmName("px2dp") get() = round(toFloat() / Resources.getSystem().displayMetrics.density).toInt()

/**
 * px 转 sp
 */
val Number.px2sp @JvmName("px2sp") get() = round(toFloat() / Resources.getSystem().displayMetrics.scaledDensity).toInt()
