package com.example.ypp.life.utils

import com.example.ypp.life.application.KotApplication

/**
 * Created by ypp0623 on 2018/11/16.
 */
object DensityUtils {
    val density: Float = KotApplication.context!!.resources.displayMetrics.density

    fun dip2px(float: Float): Int {
        return (float * density + 0.5f).toInt()
    }
}