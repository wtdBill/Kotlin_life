package com.example.ypp.life.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.eightbitlab.rxbus.Bus
import com.example.ypp.life.R
import com.example.ypp.life.base.BaseActivity
import com.example.ypp.life.network.Api
import com.example.ypp.life.utils.Logger
import com.example.ypp.life.utils.event.BaseEvent
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler.postDelayed({ mContext.startActivity(Intent(mContext, Main2Activity::class.java)) }, 1000)
//        Observable.just(1, 2, 3, 4)
//                .map {
//                    when (it) {
//                        1 -> "hah"
//                        2 -> "qqq"
//                        3 -> "eewew"
//                        4 -> "dsds"
//                        else -> "sajds"
//                    }
//                }
//                .subscribeBy(onNext = { println(it) })
//
//        Bus.observe<BaseEvent>()
//                .subscribe {
//                    Logger.e("ok")
//                }
//
//        var map: MutableMap<String,String> = HashMap()
//        map.put("userid","name")
//
//        Api.normalService.userLogin(map).subscribe {
//            Logger.e(it.busMsg!!)
//        }
//
//        for (i in 1..20){
//
//        }

    }
}
