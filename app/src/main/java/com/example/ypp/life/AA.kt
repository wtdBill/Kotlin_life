package com.example.ypp.life

import android.content.Context
import android.content.Intent
import android.os.Handler

import com.example.ypp.life.activity.MainActivity
import com.example.ypp.life.application.KotApplication

/**
 * Created by Yin on 2018/12/20.
 */

class AA {
    private val handler = Handler()
    private val context = KotApplication.context

    private fun put() {
        handler.postDelayed({ context!!.startActivity(Intent(context, MainActivity::class.java)) }, 2000)
    }
}
