package com.example.muhammadjon.myproject.comon

import android.text.Editable
import android.text.TextWatcher

class TextWatcherImpl(private val iWatcher: IWatcher) : TextWatcher {

    override fun beforeTextChanged(charSequence: CharSequence,
                                   i: Int, i1: Int, i2: Int) {

    }

    override fun onTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {
        iWatcher.onTextChanged(c.toString())
    }

    override fun afterTextChanged(editable: Editable) {}
}
