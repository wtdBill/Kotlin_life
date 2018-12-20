package com.example.ypp.life.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ypp.life.NewsAdapter

import com.example.ypp.life.R
import com.example.ypp.life.network.Api
import com.example.ypp.life.network.entity.NewsEntity
import com.example.ypp.life.utils.Constants
import kotlinx.android.synthetic.main.fragment_news.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment() {

    private val newsArray: ArrayList<NewsEntity.ResultBean.DataBean> = ArrayList()
    private lateinit var mAdapter: NewsAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val type: String = arguments!!.getString("type")
        mAdapter = NewsAdapter(R.layout.layout, newsArray)
        mRecyclerView.adapter = mAdapter
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView.layoutManager = layoutManager

        Api.juHeService.getNews(type, Constants.JUHE_NEWS_KEY)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    newsArray.addAll(it.result!!.data!!)
                    mAdapter.notifyDataSetChanged()
                }

    }


    companion object {
        fun Instance(type: String): NewsFragment {
            val newsFragment = NewsFragment()
            val bundle = Bundle()
            bundle.putString("type", type)
            newsFragment.arguments = bundle
            return newsFragment
        }
    }

}
