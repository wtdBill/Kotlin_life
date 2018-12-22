package com.example.ypp.life.utils

import android.text.TextUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * 日期工具类
 * Created by wangcunjun on 2017/5/22.
 */

object DateUtils {

    private val DATEFORMAT_YMD_HMS = SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss", Locale.getDefault())
    private val DATEFORMAT_YMD = SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault())
    private val DATEFORMAT_YMD_DOT = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
    private val DATEFORMAT_YM = SimpleDateFormat("yyyy/MM", Locale.getDefault())
    private val DATEFORMAT_HM = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val DATEFORMAT_M = SimpleDateFormat("MM", Locale.getDefault())
    private val DATEFORMAT_YM1 = SimpleDateFormat("yyyy年MM月", Locale.getDefault())
    private val DATEFORMAT_YMD2 = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    private val DATEFORMAT_YMD_HM = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())

    /**
     * 获取当前日期是否为1-5号
     *
     * @return
     */
    val date: Boolean
        get() {
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            return (day == 1) or (day == 2) or (day == 3) or (day == 4) or (day == 5)
        }

    fun ms2YMD_HMS(time: Long): String {
        return DATEFORMAT_YMD_HMS.format(Date(time))
    }

    fun ms2YMD(time: Long): String {
        return DATEFORMAT_YMD.format(Date(time))
    }

    fun ms2YMD_DOT(time: Long): String {
        return DATEFORMAT_YMD_DOT.format(Date(time))
    }

    fun ms2YM(time: Long): String {
        return DATEFORMAT_YM.format(Date(time))
    }

    fun ms2M(time: Long): String {
        return DATEFORMAT_M.format(Date(time))
    }

    fun ms2HM(time: Long): String {
        return DATEFORMAT_HM.format(Date(time))
    }

    fun ms2YM1(time: Long): String {
        return DATEFORMAT_YM1.format(Date(time))
    }

    fun ms2YM2(time: Long): String {
        return DATEFORMAT_YMD2.format(Date(time))
    }

    fun ms2YMD_HM(time: Long): String {
        return DATEFORMAT_YMD_HM.format(Date(time))
    }

    // 年
    fun ms2Year(ms: Long): Int {
        val year = SimpleDateFormat("yyyy").format(Date(ms))
        return Integer.parseInt(year)
    }

    // 月
    fun ms2Month(ms: Long): Int {
        val month = SimpleDateFormat("MM").format(Date(ms))
        return Integer.parseInt(month)
    }

    // 日
    fun ms2Day(ms: Long): Int {
        val day = SimpleDateFormat("dd").format(Date(ms))
        return Integer.parseInt(day)
    }

    // 小时
    fun ms2H(ms: Long): Int {
        val hour = SimpleDateFormat("H").format(Date(ms))
        return Integer.parseInt(hour)
    }

    // 分
    fun ms2mm(ms: Long): Int {
        val mm = SimpleDateFormat("mm").format(Date(ms))
        return Integer.parseInt(mm)
    }

    // 毫秒
    @JvmOverloads
    fun date2MS(date: String, format: String = "yyyy-MM-dd H:mm:ss"): Long {
        if (TextUtils.isEmpty(date)) {
            return System.currentTimeMillis()
        }
        val c = Calendar.getInstance()
        try {
            if (date.contains("年") && date.contains("月") && date.contains("日")) {
                c.time = SimpleDateFormat(format).parse(date)
            } else if (date.matches("\\d{8}".toRegex()) && date.contains("-")) {
                c.time = SimpleDateFormat("yyyy-MM-dd").parse(date)
            } else {
                c.time = SimpleDateFormat(format).parse(date)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return c.timeInMillis
    }

    fun date2MS2(date: String): Long {
        return date2MS(date, "yyyyMMddHHmm") // 默认格式
    }

    fun date2MS3(date: String): Long {
        return date2MS(date, "yyyy-MM-dd")
    }

    fun date2MS4(date: String): Long {
        return date2MS(date, "yyyy-MM-dd HH:mm:ss") // 默认格式
    }

    // 毫秒转星期几 (星期一 至 星期天)
    fun ms2WeekString(ms: Long): String {
        return SimpleDateFormat("EEEE").format(Date(ms))
    }

    // 毫秒转 周几 (1-7)
    fun ms2Week(ms: Long): Int {
        val cal = Calendar.getInstance()
        try {
            val dateFormat = SimpleDateFormat("yyyy年MM月dd日")
            val date = dateFormat.parse(ms2YMD(ms))
            cal.time = date
            val i = cal.get(Calendar.DAY_OF_WEEK)
            return if (i == 1) {
                7
            } else i - 1
        } catch (e: java.text.ParseException) {
            e.printStackTrace()
        }

        return -1

    }

    /**
     * * 根据 年、月 获取对应的月份 的 天数
     */
    fun getDaysByYearMonth(year: Int, month: Int): Int {
        val a = Calendar.getInstance()
        a.set(Calendar.YEAR, year)
        a.set(Calendar.MONTH, month - 1)
        a.set(Calendar.DATE, 1)
        a.roll(Calendar.DATE, -1)
        return a.get(Calendar.DATE)
    }

    // 是否是今天
    fun isToday(ms: Long): Boolean {
        val cTime = System.currentTimeMillis()
        val cYear = ms2Year(cTime)
        val cMonth = ms2Month(cTime)
        val cDay = ms2Day(cTime)
        val year = ms2Year(ms)
        val month = ms2Month(ms)
        val day = ms2Day(ms)
        if (year < cYear || year > cYear) {
            return false
        }
        return if (month < cMonth || month > cMonth) {
            false
        } else day >= cDay && day <= cDay
    }

    // 是否是同一天
    fun isSameDate(t1: Long, t2: Long): Boolean {
        val y1 = ms2Year(t1)
        val m1 = ms2Month(t1)
        val d1 = ms2Day(t1)
        val y2 = ms2Year(t2)
        val m2 = ms2Month(t2)
        val d2 = ms2Day(t2)
        return y1 == y2 && m1 == m2 && d1 == d2
    }

    fun getAddTime(time: Long): String {
        val intervalTime = System.currentTimeMillis() - time
        val date: String
        if (intervalTime > 1000L * 60 * 60 * 24 * 30 * 6) {
            date = ms2YMD(time)
        } else if (intervalTime > 1000L * 60 * 60 * 24 * 30 * 3) {
            date = "3个月前"
        } else if (intervalTime > 1000L * 60 * 60 * 24 * 30 * 2) {
            date = "2个月前"
        } else if (intervalTime > 1000L * 60 * 60 * 24 * 30) {
            date = "1个月前"
        } else if (intervalTime > 1000L * 60 * 60 * 24 * 15) {
            date = "半个月前"
        } else if (intervalTime > 1000L * 60 * 60 * 24 * 5) {
            date = "5天前"
        } else if (intervalTime > 1000L * 60 * 60 * 24 * 3) {
            date = "3天前"
        } else if (intervalTime > 1000L * 60 * 60 * 24 * 2) {
            date = "2天前"
        } else if (intervalTime > 1000L * 60 * 60 * 24) {
            date = "1天前"
        } else if (intervalTime > 1000L * 60 * 60 * 12) {
            date = "12小时前"
        } else if (intervalTime > 1000L * 60 * 60 * 5) {
            date = "5小时前"
        } else if (intervalTime > 1000L * 60 * 60 * 2) {
            date = "2小时前"
        } else if (intervalTime > 1000L * 60 * 60) {
            date = "1小时前"
        } else if (intervalTime > 1000L * 60 * 45) {
            date = "45分钟前"
        } else if (intervalTime > 1000L * 60 * 30) {
            date = "30分钟前"
        } else if (intervalTime > 1000L * 60 * 20) {
            date = "20分钟前"
        } else if (intervalTime > 1000L * 60 * 10) {
            date = "10分钟前"
        } else if (intervalTime > 1000L * 60 * 5) {
            date = "5分钟前"
        } else if (intervalTime > 1000L * 60) {
            date = "1分钟前"
        } else if (intervalTime > 1000L * 10) {
            date = "10秒前"
        } else {
            date = "刚刚"
        }
        return date
    }

    /*
     * 将时间戳转换为时间
     */
    fun stamp2Date(s: String): String {
        val dateTime: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val timeLong = java.lang.Long.valueOf(s)!!
        dateTime = simpleDateFormat.format(Date(timeLong * 1000L))
        return dateTime
    }

    /*
     * HH:mm:ss去除秒，显示时:分
     */
    fun TimedeleteSecond(time: String): String {
        val buff = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return buff[0] + ":" + buff[1]
    }

    /*
     * 字符串转换为日期yyyy-MM-dd
     */
    fun str2Date(str: String): Date? {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var date: Date? = null
        try {
            date = sdf.parse(str)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return date
    }

    /*
     * 字符串转换为日期yyyyMMddHHmm
     */
    fun str2Date1(str: String): Date? {
        val sdf = SimpleDateFormat("yyyyMMddHHmm")
        var date: Date? = null
        try {
            date = sdf.parse(str)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return date
    }

    /**
     * 未来某个时间距离今天多少天
     *
     * @param ms 未来的时间，单位是毫秒
     * @return 距离今天多少天
     */
    fun getDateFromToday(ms: Long): Double {
        val s2 = System.currentTimeMillis()
        return (ms - s2).toDouble() / 1000.0 / 60.0 / 60.0 / 24.0
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    @Throws(ParseException::class)
    fun daysBetween(smdate: String, bdate: String): Int {
        val sdf = SimpleDateFormat("yyyyMMdd")
        val cal = Calendar.getInstance()
        cal.time = sdf.parse(smdate)
        val time1 = cal.timeInMillis
        cal.time = sdf.parse(bdate)
        val time2 = cal.timeInMillis
        val between_days = (time2 - time1) / (1000 * 3600 * 24)

        return Integer.parseInt(between_days.toString())
    }

}// 默认格式
