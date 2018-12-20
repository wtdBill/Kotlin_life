package com.example.ypp.life.network.service

import com.example.ypp.life.network.entity.BaseEntity
import retrofit2.http.*
import rx.Observable

/**
 * Created by ypp0623 on 2018/11/16.
 */
interface NormalService {

    @POST("www.baidu.com")
    @FormUrlEncoded
    fun userLogin(@FieldMap options: Map<String, String>): Observable<BaseEntity>
}