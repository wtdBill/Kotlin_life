package com.example.ypp.life.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.ypp.life.R
import com.example.ypp.life.base.BaseActivity
import com.example.ypp.life.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() , View.OnClickListener{
    override fun onClick(v: View?) {
        when(v){
            joke -> {
                mContext.startActivity(Intent(mContext,JokeActivity::class.java))
            }
            news ->{
                mContext.startActivity(Intent(mContext,NewsActivity::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarUtil.setTranslucentForCoordinatorLayout(this,127)
        joke.setOnClickListener(this)
        news.setOnClickListener(this)
//        joke.setOnClickListener {
//            mContext.startActivity(Intent(mContext,JokeActivity::class.java))
//        }
//        news.setOnClickListener {
//            mContext.startActivity(Intent(mContext,NewsActivity::class.java))
//        }
//        handler.postDelayed({ mContext.startActivity(Intent(mContext, JokeActivity::class.java)) }, 1000)
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
