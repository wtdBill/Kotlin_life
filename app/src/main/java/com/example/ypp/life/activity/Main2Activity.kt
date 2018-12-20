package com.example.ypp.life.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.ypp.life.NewsAdapter
import com.example.ypp.life.R
import com.example.ypp.life.network.Api
import com.example.ypp.life.network.entity.NewsEntity
import com.example.ypp.life.utils.Constants
import com.example.ypp.life.utils.Logger
import kotlinx.android.synthetic.main.activity_main2.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class Main2Activity : AppCompatActivity() {
    private val newsArray: ArrayList<NewsEntity.ResultBean.DataBean> = ArrayList()
    private lateinit var mAdapter:NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mAdapter= NewsAdapter(R.layout.layout,newsArray)
        mRecyclerView.adapter = mAdapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView.layoutManager = layoutManager

        Api.juHeService.getNews("toutiao", Constants.JUHE_NEWS_KEY)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    newsArray.addAll(it.result!!.data!!)
                    mAdapter.notifyDataSetChanged()
                }


    }
}
