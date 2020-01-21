package com.jimmysun.wanandroid

import android.os.Bundle
import com.jimmysun.wanandroid.base.activity.BaseActivity
import com.jimmysun.wanandroid.fragment.HomeFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, HomeFragment())
            .commit()
    }
}
