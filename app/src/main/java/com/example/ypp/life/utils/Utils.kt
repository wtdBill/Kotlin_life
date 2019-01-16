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
import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.TextUtils
import android.view.View
import com.example.ypp.life.BuildConfig


/**
 * Created by ypp0623 on 2018/11/15.
 */
object Utils {
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

    @JvmStatic
    @SuppressLint("MissingPermission")
    fun getNetworkInfo(context: Context): NetworkInfo {
        val cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

    @JvmStatic
    fun checkPermission(context: Context) {
        val sdk = Build.VERSION.SDK
        val model = Build.MODEL
        val release = Build.VERSION.RELEASE
        val brand = Build.BRAND
        val name = Build.MANUFACTURER
        if (TextUtils.equals(brand.toLowerCase(), "redmi") || TextUtils.equals(brand.toLowerCase(), "xiaomi")) {
            gotoMiUiPermission(context)
        } else if (TextUtils.equals(brand.toLowerCase(), "meizu")) {
            goMeizuPermission(context)
        } else if (TextUtils.equals(brand.toLowerCase(), "huawei") || TextUtils.equals(brand.toLowerCase(), "honor")) {
            goHuaweiPermission(context)
        } else {
            context.startActivity(getAppDetailSettingIntent(context))
        }
    }

    fun gotoMiUiPermission(context: Context) {
        try {
            val intent = Intent("miui.intent.action.APP_PERM_EDITOR")
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity")
            intent.putExtra("extra_pkgname", context.packageName)
            context.startActivity(intent)
        } catch (e: java.lang.Exception) {
            try {
                val newIntent = Intent("miui.intent.action.APP_PERM_EDITOR")
                newIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity")
                newIntent.putExtra("extra_pkgname", context.packageName)
                context.startActivity(newIntent)
            } catch (e: java.lang.Exception) {
                context.startActivity(getAppDetailSettingIntent(context))
            }
        }
    }

    private fun getAppDetailSettingIntent(context: Context): Intent {
        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
        intent.data = Uri.fromParts("package", context.packageName, null)
        return intent
    }

    private fun goMiuiPermission(context: Context) {
        try {
            val intent = Intent("miui.intent.action.APP_PERM_EDITOR")
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity")
            intent.putExtra("extra_pkgname", context.packageName)
            context.startActivity(intent)
        } catch (e: java.lang.Exception) {
            val intent = Intent("miui.intent.action.APP_PERM_EDITOR")
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity")
            intent.putExtra("extra_pkgname", context.packageName)
            context.startActivity(intent)
        }

    }

    private fun goMeizuPermission(context: Context){
        try {
            val intent = Intent("com.meizu.safe.security.SHOW_APPSEC")
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID)
            context.startActivity(intent)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            context.startActivity(getAppDetailSettingIntent(context))
        }
    }

    private fun goHuaweiPermission(context: Context){
        try {
            val intent = Intent()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val componentName = ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity")
            intent.component = componentName
            context.startActivity(intent)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            context.startActivity(getAppDetailSettingIntent(context))
        }
    }

}