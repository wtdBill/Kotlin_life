package com.example.ypp.life.application

import android.app.Application

/**
 * Created by ypp0623 on 2018/11/15.
 */
class KotApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mAppliaction = this
    }

    companion object {
        private lateinit var mAppliaction: KotApplication
        val context: KotApplication?
            get() = mAppliaction
    }
}