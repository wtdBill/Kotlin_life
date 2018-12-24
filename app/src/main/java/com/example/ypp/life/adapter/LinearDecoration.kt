package com.example.ypp.life.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.ypp.life.R
import com.example.ypp.life.application.KotApplication
import com.example.ypp.life.utils.DensityUtils

/**
 * Created by Yin on 2018/12/24.
 */
class LinearDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val dividerHeight: Int
    private val dividerPaint: Paint = Paint()

    init {
        dividerPaint.color = context.resources.getColor(R.color.divider)
        dividerHeight = DensityUtils.dip2px(1F)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = dividerHeight
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        for (i in 0 until childCount - 1) {
            val view = parent.getChildAt(i)
            val top = view.bottom.toFloat()
            val bottom = (view.bottom + dividerHeight).toFloat()
            c.drawRect(left.toFloat(), top, right.toFloat(), bottom, dividerPaint)
        }
    }
}