package com.example.ypp.life.network.entity

/**
 * Created by Yin on 2018/12/25.
 */
data class Test(
        var error_code: Int = 0,
        var reason: String = "",
        var result: Result = Result()
)

data class Result(
    var `data`: List<Data> = listOf()
)

data class Data(
    var content: String = "",
    var hashId: String = "",
    var unixtime: Int = 0,
    var updatetime: String = ""
)