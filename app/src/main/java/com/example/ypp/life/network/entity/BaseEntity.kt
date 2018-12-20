package com.example.ypp.life.network.entity

/**
 * 新版接口的entity基类
 *
 * @author huangyao
 */
class BaseEntity {

    val reCode: String? = null
    val reMsg: String? = null

    val busCode: String? = null // 返回代码 0成功，其他失败
    val busMsg: String? = null // 返回描述	中文描述信息
}
