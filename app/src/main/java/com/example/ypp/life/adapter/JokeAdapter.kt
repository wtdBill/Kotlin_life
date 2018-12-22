package com.example.ypp.life.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.ypp.life.R
import com.example.ypp.life.network.entity.JokeEntity


/**
* Created by Yin on 2018/12/22.
*/
class JokeAdapter(layoutResId: Int, data: MutableList<JokeEntity.ResultBean.DataBean>?) : BaseQuickAdapter<JokeEntity.ResultBean.DataBean, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder?, item: JokeEntity.ResultBean.DataBean?) {
        helper!!.getView<TextView>(R.id.text).text = item!!.content
    }
}