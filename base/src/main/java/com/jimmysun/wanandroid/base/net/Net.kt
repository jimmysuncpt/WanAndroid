package com.jimmysun.wanandroid.base.net

import com.jimmysun.wanandroid.base.util.isDebug
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy

/**
 * 网络请求管理类
 * @author SunQiang
 * @since 2020-01-08
 */
object Net {
    const val NET_ERROR = "似乎出了点问题~"

    private const val BASE_URL = "https://www.wanandroid.com"

    private val retrofit: Retrofit by lazy {
        val clientBuilder = OkHttpClient.Builder()
        if (!isDebug()) {
            clientBuilder.proxy(Proxy.NO_PROXY)
        }
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> createService(classOfService: Class<T>): T = retrofit.create(classOfService)
}