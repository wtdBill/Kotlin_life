package com.example.ypp.life.utils

import android.support.annotation.NonNull
import android.util.Log
import com.example.ypp.life.BuildConfig

/**
 * Created by ypp0623 on 2018/11/16.
 */
object Logger {
    private var isLog = BuildConfig.DEBUG
    private val tag: String = "kotlin_study"
    fun setTag(isLog: Boolean) {
        Logger.isLog = isLog
    }

    val get: Boolean
        get() = isLog

    fun d(@NonNull msg: String) {
        if (isLog) {
            Log.d(tag, msg)
        }
    }

    fun e(@NonNull msg: String) {
        if (isLog) {
            Log.e(tag, msg)
        }
    }

    fun i(@NonNull msg: String) {
        if (isLog) {
            Log.i(tag, msg)
        }
    }

    fun v(@NonNull msg: String) {
        if (isLog) {
            Log.v(tag, msg)
        }
    }

    fun w(@NonNull msg: String) {
        if (isLog) {
            Log.w(tag, msg)
        }
    }

}