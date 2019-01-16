package com.example.ypp.life.activity

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import android.view.Window
import com.example.ypp.life.R
import com.example.ypp.life.base.BaseActivity
import com.example.ypp.life.network.entity.Test
import com.example.ypp.life.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v) {
            joke -> {
                mContext.startActivity(Intent(mContext, JokeActivity::class.java))
            }
            news -> {
                mContext.startActivity(Intent(mContext, NewsActivity::class.java))
            }
            pic -> {
                val intent = Intent(mContext,Main3Activity::class.java)

                val compact = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions.makeSceneTransitionAnimation(this,pic,pic.transitionName)
//                    ActivityOptions.makeSceneTransitionAnimation(this)
                } else {
                    TODO("VERSION.SDK_INT < LOLLIPOP")

                }
                mContext.startActivity(intent,compact.toBundle())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 127)
        joke.setOnClickListener(this)
        news.setOnClickListener(this)
        pic.setOnClickListener(this)
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
