package com.example.muhammadjon.myproject.component

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

import com.example.muhammadjon.myproject.R
import kotlinx.android.synthetic.main.layout_circles.view.*

import java.util.ArrayList

class PinCircles : FrameLayout {
    private var circles = ArrayList<View>()

    private val count = 4
    private var emptyBachground: Drawable? = null
    private var fillBachground: Drawable? = null

    constructor(context: Context) : super(context) {
        initialize()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initialize()
    }

    private fun initialize() {
        View.inflate(context, R.layout.layout_circles, this)

        emptyBachground = context.resources.getDrawable(R.drawable.circle_pin_empty)
        fillBachground = context.resources.getDrawable(R.drawable.circle_pin_fill)

        circles.add(one)
        circles.add(two)
        circles.add(three)
        circles.add(four)
        circles.add(five)
        circles.add(six)

        for (i in circles.indices) {
            if (i < count)
                circles[i].visibility = View.VISIBLE
            else
                circles[i].visibility = View.GONE
        }
    }

    fun setCircles(circles: ArrayList<View>) {
        this.circles = circles
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    fun fillCircles(fillCount: Int) {
        for (i in 0 until count) {
            if (i < fillCount)
                circles[i].background = fillBachground
            else
                circles[i].background = emptyBachground
        }
    }
}
