package com.jimmysun.wanandroid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jimmysun.wanandroid.base.ktx.logE
import com.jimmysun.wanandroid.base.net.NET_ERROR
import com.jimmysun.wanandroid.base.util.toast
import kotlinx.coroutines.*

/**
 * @author SunQiang
 * @since 2020-01-13
 */
open class BaseViewModel : ViewModel(), CoroutineScope by MainScope() {
    /**
     * 在 IO 线程执行操作
     */
    protected fun runOnIO(doSomething: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                doSomething()
            } catch (e: Exception) {
                toast(NET_ERROR)
                logE(e.message)
            }
        }
    }

    override fun onCleared() {
        cancel()
        super.onCleared()
    }
}