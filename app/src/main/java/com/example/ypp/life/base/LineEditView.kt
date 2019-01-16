package com.example.ypp.life.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.example.ypp.life.R

class LineEditView : View {
    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    @SuppressLint("Recycle", "CustomViewStyleable")
    fun init(attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.LLineEditView)
        this.setBackgroundColor(typedArray.getColor(R.styleable.LLineEditView_backGround, Color.WHITE))
    }
}
