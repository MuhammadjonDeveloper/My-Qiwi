package com.example.muhammadjon.myproject.component

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView

import com.example.muhammadjon.myproject.R
import kotlinx.android.synthetic.main.layout_pin_keyboard.view.*

class PinKeyboard : FrameLayout, View.OnClickListener {
    private var listener: TabListener? = null

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
        View.inflate(context, R.layout.layout_pin_keyboard, this)

        one!!.setOnClickListener(this)
        two!!.setOnClickListener(this)
        three!!.setOnClickListener(this)
        four!!.setOnClickListener(this)
        five!!.setOnClickListener(this)
        six!!.setOnClickListener(this)
        seven!!.setOnClickListener(this)
        eight!!.setOnClickListener(this)
        nine!!.setOnClickListener(this)
        zero!!.setOnClickListener(this)

        erase!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.erase -> if (listener != null) {
                listener!!.onEraseTab()
            }
            else -> if (listener != null) {
                listener!!.onNumberTab((findViewById<View>(v.id) as TextView).text.toString())
            }
        }
    }

    fun setOnTabListener(listener: TabListener) {
        this.listener = listener
    }

    interface TabListener {

        fun onNumberTab(number: String)

        fun onEraseTab()
    }
}
