package com.jimmysun.wanandroid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jimmysun.wanandroid.base.app.BaseApplication
import com.jimmysun.wanandroid.base.ktx.logE
import com.jimmysun.wanandroid.base.net.NET_ERROR
import com.jimmysun.wanandroid.base.util.isNetworkAvailable
import com.jimmysun.wanandroid.base.util.toast
import kotlinx.coroutines.*

/**
 * @author SunQiang
 * @since 2020-01-13
 */
open class BaseViewModel : ViewModel(), CoroutineScope by MainScope() {
    var listener: ViewModelListener? = null

    /**
     * 在 IO 线程执行操作
     */
    protected fun runOnIO(doSomething: suspend () -> Unit) {
        if (isNetworkAvailable(BaseApplication.INSTANCE)) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    doSomething()
                } catch (e: Exception) {
                    toast(NET_ERROR)
                    listener?.onLoadFailed()
                    logE(e.message)
                }
            }
        } else {
            toast(NET_ERROR)
            listener?.onLoadFailed()
        }
    }

    override fun onCleared() {
        cancel()
        super.onCleared()
    }
}

interface ViewModelListener {
    fun onLoadFailed()
}