package com.jimmysun.wanandroid.base.app

import android.app.Application

/**
 * Application 基类
 * @author SunQiang
 * @since 2020-01-10
 */
open class BaseApplication : Application() {
    companion object {
        lateinit var INSTANCE: BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}