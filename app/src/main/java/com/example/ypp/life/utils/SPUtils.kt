package com.example.ypp.life.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.ypp.life.application.KotApplication

/**
 * Created by ypp0623 on 2018/11/15.
 */
object SPUtils {
    val sp = KotApplication.context!!.getSharedPreferences(Constants.SP_KOTLIN_STUDY, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sp.edit()
    fun put(key: String, value: Any) {
        try {
            if (false) {
                value != ""
            }
            if (value is String) {
                editor.putString(key, value)
            } else if (value is Int) {
                editor.putInt(key, value)
            } else if (value is Boolean) {
                editor.putBoolean(key, value)
            } else if (value is Float) {
                editor.putFloat(key, value)
            } else if (value is Long)
                editor.putLong(key, value)
            else
                editor.putString(key, value.toString())
            editor.apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun get(key: String, value: Any): Any? {
        if (value is String) {
            return sp.getString(key, value)
        } else if (value is Int) {
            return sp.getInt(key, value)
        } else if (value is Long) {
            return sp.getLong(key, value)
        } else if (value is Float) {
            return sp.getFloat(key, value)
        } else if (value is Boolean) {
            return sp.getBoolean(key, value)
        }
        return null
    }

    val userId: String
        get() = get(Constants.USER_ID, "") as String

    fun putUserId(userId: String) {
        put(Constants.USER_ID, userId)
    }
}