package com.example.ypp.life.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.view.WindowManager
import com.example.ypp.life.application.KotApplication
import java.util.*
import android.annotation.TargetApi
import android.graphics.Color
import android.view.View


/**
 * Created by ypp0623 on 2018/11/15.
 */
class Utils {
    companion object {
        val versionCode: Int
            get() {
                var packageInfo: PackageInfo? = null
                try {
                    val packManager: PackageManager = KotApplication.context!!.packageManager
                    packageInfo = packManager.getPackageInfo(KotApplication.context!!.packageName, 0)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return packageInfo!!.versionCode
            }

        val versionName: String
            get() {
                var packagetInfo: PackageInfo? = null
                try {
                    val packManager: PackageManager = KotApplication.context!!.packageManager
                    packagetInfo = packManager.getPackageInfo(KotApplication.context!!.packageName, 0)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return packagetInfo!!.versionName
            }

        val uid: String
            get() {
                return UUID.randomUUID().toString()
            }

        fun isNetWorkAvailable(): Boolean {
            val info: NetworkInfo = getNetworkInfo(KotApplication.context!!)
            return info.isAvailable
        }

        @SuppressLint("MissingPermission")
        fun getNetworkInfo(context: Context): NetworkInfo {
            val cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo
        }
    }
}