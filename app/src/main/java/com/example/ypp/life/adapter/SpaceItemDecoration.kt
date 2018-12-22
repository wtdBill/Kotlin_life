package com.example.ypp.life.adapter

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View

/**
 * Created by ypp on 18-5-4.
 * 竖直方向上的条目间距
 */

class SpaceItemDecoration(context: Context, dpValue: Int) : RecyclerView.ItemDecoration() {

    private val mSpace: Int

    init {
        mSpace = dp2px(context, dpValue)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.getChildAdapterPosition(view) > 0) {
            //从第二个条目开始，距离上方Item的距离
            outRect.top = mSpace

        }
    }

    /**
     * dp to px转换
     * @param context
     * @param dpValue
     * @return
     */
    private fun dp2px(context: Context, dpValue: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), context.resources.displayMetrics).toInt()
    }
}
