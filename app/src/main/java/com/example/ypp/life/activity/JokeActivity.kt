package com.example.ypp.life.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.widget.LinearLayoutManager
import android.view.WindowManager
import android.view.animation.ScaleAnimation
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.ypp.life.R
import com.example.ypp.life.adapter.JokeAdapter
import com.example.ypp.life.adapter.LinearDecoration
import com.example.ypp.life.base.BaseActivity
import com.example.ypp.life.network.Api
import com.example.ypp.life.network.entity.JokeEntity
import com.example.ypp.life.utils.Constants

import com.example.ypp.life.utils.Logger
import com.example.ypp.life.utils.SPUtils
import com.example.ypp.life.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_joke.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class JokeActivity : BaseActivity() {

    private val jokeArrry: ArrayList<JokeEntity.ResultBean.DataBean> = ArrayList()
    private val count = 20
    private var page = 1
    private val type_after = "asc"
    private val type_before = "desc"
    private lateinit var adapter: JokeAdapter
    private lateinit var scaleAnim:ScaleAnimation

    companion object {
        private var time: String = System.currentTimeMillis().toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        overridePendingTransition(R.anim.test_anim,R.anim.test_anim)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_joke)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,Main3Activity::class.java))
        },2000)
//        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 127)
        adapter = JokeAdapter(R.layout.joke_item, jokeArrry)
        adapter.setOnLoadMoreListener {
            page++
            getJokeData()
        }
        mRecyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.addItemDecoration(LinearDecoration(mContext))
        getJokeData()
    }

    private fun getJokeData() {
        time = SPUtils.get(Constants.JOKE_TIME, "1418745237") as String
        var tm = time.substring(0, 9)
        tm += time[9]
        Logger.e(tm)
        Api.juHeService.getJoke(type_after, page, count, tm, Constants.JUHE_JOKE_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.loadMoreComplete()
                    if (it != null) {
                        if (it.result!!.data!!.isNotEmpty()) {
                            SPUtils.put(Constants.JOKE_TIME, it.result?.data?.last()?.unixtime.toString())
                            Logger.e(it.result?.data?.last()?.unixtime.toString())
                            jokeArrry.addAll(it.result?.data!!)
                            adapter.notifyDataSetChanged()
                        } else {
                            SPUtils.put(Constants.JOKE_TIME, "1418745237")
                            getJokeData()
                        }
                    }
                }
    }

}
