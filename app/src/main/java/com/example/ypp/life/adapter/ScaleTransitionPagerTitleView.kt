package com.example.ypp.life.adapter

import android.content.Context

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView

/**
 * 带颜色渐变和缩放的指示器标题
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
class ScaleTransitionPagerTitleView(context: Context) : ColorTransitionPagerTitleView(context) {
    var minScale = 0.75f
    private var mOnSelectedListener: OnSelectedListener? = null

    private var mTextMode = MODE_DEFAULT

    override fun onEnter(index: Int, totalCount: Int, enterPercent: Float, leftToRight: Boolean) {
        super.onEnter(index, totalCount, enterPercent, leftToRight)    // 实现颜色渐变
        scaleX = minScale + (1.0f - minScale) * enterPercent
        scaleY = minScale + (1.0f - minScale) * enterPercent
        if (mOnSelectedListener != null) {
            mOnSelectedListener!!.onEnter(index)
        }
    }

    override fun onLeave(index: Int, totalCount: Int, leavePercent: Float, leftToRight: Boolean) {
        super.onLeave(index, totalCount, leavePercent, leftToRight)    // 实现颜色渐变
        scaleX = 1.0f + (minScale - 1.0f) * leavePercent
        scaleY = 1.0f + (minScale - 1.0f) * leavePercent
        if (mOnSelectedListener != null) {
            mOnSelectedListener!!.onLeave(index)
        }
    }

    fun setTextBold(isBold: Boolean) {
        paint.isFakeBoldText = isBold
    }

    interface OnSelectedListener {
        fun onEnter(index: Int)

        fun onLeave(index: Int)
    }

    fun setOnSelectedListener(onSelectedListener: OnSelectedListener) {
        this.mOnSelectedListener = onSelectedListener
    }

    fun setTextDisplayMode(mode: Int) {
        this.mTextMode = mode
    }

    override fun onSelected(index: Int, totalCount: Int) {
        super.onSelected(index, totalCount)
    }

    override fun onDeselected(index: Int, totalCount: Int) {
        super.onDeselected(index, totalCount)
    }

    companion object {

        val MODE_DEFAULT = 0   // 默认  |无阴影
        val MODE_NOTE_PLAY = 1 // 拍摄  |选中拍摄界面时，两边颜色都设置阴影，反之都没有阴影
    }
}
