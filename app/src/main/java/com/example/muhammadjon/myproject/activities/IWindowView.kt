package com.example.muhammadjon.myproject.activities

import com.example.muhammadjon.myproject.dbase.Merchants

interface IWindowView {

    fun onHidePb()

    fun onShowPb()

    fun onResult(merchants: List<Merchants>)

    fun onError(text: String)
}
