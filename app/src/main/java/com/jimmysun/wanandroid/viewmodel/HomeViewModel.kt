package com.jimmysun.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.jimmysun.wanandroid.base.BaseViewModel
import com.jimmysun.wanandroid.repository.HomeRepository

/**
 * @author SunQiang
 * @since 2020-01-09
 */
class HomeViewModel : BaseViewModel() {
    private val homeRepository = HomeRepository()
    val dataListLiveData: MutableLiveData<List<Any>> = homeRepository.dataListLiveData

    init {
        loadData(0)
    }

    fun loadData(page: Int) {
        runOnIO {
            homeRepository.loadData(page)
        }
    }
}