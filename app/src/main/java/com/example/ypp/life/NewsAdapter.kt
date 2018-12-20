package com.example.ypp.life

import android.content.Intent
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.ypp.life.activity.WebViewActivity
import com.example.ypp.life.network.entity.NewsEntity

/**
* Created by Yin on 2018/12/20.
*/
class NewsAdapter(layoutResId: Int, data: MutableList<NewsEntity.ResultBean.DataBean>?) : BaseQuickAdapter<NewsEntity.ResultBean.DataBean, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder?, item: NewsEntity.ResultBean.DataBean?) {
        helper!!.getView<TextView>(R.id.title).text = item!!.title
        helper.getView<TextView>(R.id.author).text = item.author_name
        Glide.with(mContext).load(item.thumbnail_pic_s).into(helper.getView(R.id.iv1))
        Glide.with(mContext).load(item.thumbnail_pic_s02).into(helper.getView(R.id.iv2))
        Glide.with(mContext).load(item.thumbnail_pic_s03).into(helper.getView(R.id.iv3))
        helper.getView<LinearLayout>(R.id.rootLayout).setOnClickListener {
            mContext.startActivity(Intent(mContext,WebViewActivity::class.java).putExtra(WebViewActivity.PARAM_URL,item.url))
        }

    }
}