package com.example.muhammadjon.myproject.model

import android.arch.lifecycle.LiveData

import com.example.muhammadjon.myproject.dbase.Merchants

interface IWindowRepository {
    fun search(categoryid: Int): LiveData<List<Merchants>>
}
