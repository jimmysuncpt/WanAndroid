package com.jimmysun.wanandroid.base.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.jimmysun.wanandroid.base.ktx.removeLast

/**
 * Application 基类
 * @author SunQiang
 * @since 2020-01-10
 */
open class BaseApplication : Application(), Application.ActivityLifecycleCallbacks {
    companion object {
        lateinit var INSTANCE: BaseApplication
            private set
        val activityStack = mutableListOf<Activity>()
        val topActivity
            get() = activityStack.last()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activityStack.add(activity)
    }

    override fun onActivityStarted(activity: Activity) {
        // do nothing
    }

    override fun onActivityResumed(activity: Activity) {
        // do nothing
    }

    override fun onActivityPaused(activity: Activity) {
        // do nothing
    }

    override fun onActivityStopped(activity: Activity) {
        // do nothing
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // do nothing
    }

    override fun onActivityDestroyed(activity: Activity) {
        activityStack.removeLast()
    }
}