package com.example.ypp.life.network

import com.example.ypp.life.application.KotApplication
import com.example.ypp.life.network.service.JuHeService
import com.example.ypp.life.network.service.NormalService
import com.example.ypp.life.utils.Logger
import com.example.ypp.life.utils.Utils
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Yin on 2018/11/16.
 */
object Api {
    private val baseUrl: String = ""
    private val juheUrl = "http://v.juhe.cn/"
    private var mOkHttpClient: OkHttpClient? = null
    val cache = Cache(File(KotApplication.context!!.cacheDir, "HttpCache"), (1024 * 1024 * 10).toLong())

    private fun initOkHttpClient() {
        Logger.e("hahahahahahha")
//        synchronized(Api) {
//            if (false) {
                mOkHttpClient = OkHttpClient.Builder()
                        .cache(cache)
                        .addNetworkInterceptor(CacheInterceptor())
                        .retryOnConnectionFailure(true)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .addInterceptor(ParameterInterceptor())
                        .build()
                Logger.e("sssssss")

//                        val interceptors :ArrayList<Interceptor> = ArrayList()
//                        interceptors.addAll(mOkHttpClient.interceptors())
//                        interceptors.add(RetryAndFollowUpInterceptor(mOkHttpClient,true))
//                        interceptors.add(BridgeInterceptor(mOkHttpClient.cookieJar()))
//                        interceptors.add(CacheInterceptor())
//                        interceptors.addAll(mOkHttpClient.networkInterceptors())
//                        interceptors.add(CallServerInterceptor(true))
//                        val chain : Interceptor.Chain =RealInterceptorChain(interceptors,null,null,
//                                )
//            }
//        }
    }

    class ParameterInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response {
            var request: Request = chain!!.request()
            val builder: HttpUrl.Builder = request.url()
                    .newBuilder()
                    .scheme(request.url().scheme())
            val newRequest: Request = request.newBuilder()
                    .method(request.method(), request.body())
                    .url(builder.build())
                    .build()
            return chain.proceed(newRequest)
        }

    }

    private fun <T> createApi(clazz: Class<T>, baseUrl: String): T {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(EncryptConverterFactoryNew.create())
                .build()
        return retrofit.create(clazz)
    }

    val normalService: NormalService
        get() {
            return createApi(NormalService::class.java, "")
        }
    val juHeService: JuHeService
        get() {
            return createApi(JuHeService::class.java, juheUrl)
        }

    init {
        initOkHttpClient()
    }
}

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val maxAge = 0
        val maxStale = 60 * 60 * 24;
        var request: Request = chain!!.request()
        if (Utils.isNetWorkAvailable()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build()
        } else {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
        }
        var response: Response = chain.proceed(request)
        if (Utils.isNetWorkAvailable()) {
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build()
        } else {
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build()
        }
        return response
    }

}