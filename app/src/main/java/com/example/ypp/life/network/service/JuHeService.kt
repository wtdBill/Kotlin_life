package com.example.ypp.life.network.service

import com.example.ypp.life.network.entity.BaseEntity
import com.example.ypp.life.network.entity.NewsEntity
import com.example.ypp.life.utils.Constants
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * Created by Yin on 2018/12/20.
 */
interface JuHeService {
    @POST(Constants.JUHE_NEWS)
    @FormUrlEncoded
    fun getNews(@Field("type") type: String, @Field("key") key: String = Constants.JUHE_NEWS_KEY): Observable<NewsEntity>
}