package com.jimmysun.wanandroid.app

import com.facebook.stetho.Stetho
import com.jimmysun.wanandroid.base.app.BaseApplication

/**
 * Application 配置
 * @author SunQiang
 * @since 2020-01-09
 */
class WanApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}