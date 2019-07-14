package com.example.muhammadjon.myproject.model

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context

import com.example.muhammadjon.myproject.dbase.AppDataBase
import com.example.muhammadjon.myproject.dbase.Merchants
import com.example.muhammadjon.myproject.dbase.MerchantsDao
import com.example.muhammadjon.myproject.utils.NetUtils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers

class WindowRepository private constructor(ctx: Context) : IWindowRepository {
    private val data: MutableLiveData<List<Merchants>> = MutableLiveData()
    private val utils: NetUtils = NetUtils.getINSTAINS(ctx)
    private val dao: MerchantsDao = AppDataBase.getInstaince(ctx).merchantDao

    @SuppressLint("CheckResult")
    override fun search(categoryid: Int): LiveData<List<Merchants>> {
        dao.getAll(categoryid.toLong())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    if (it != null && it.isNotEmpty()) {
                        data.postValue(it)
                    } else {
                        data.postValue(null)
                    }})


        return data
    }

    companion object {
        private var INSTAINS: IWindowRepository? = null

        fun getINSTAINS(ctx: Context): IWindowRepository {
            if (INSTAINS == null) {
                INSTAINS = WindowRepository(ctx)
            }
            return INSTAINS as IWindowRepository
        }
    }
}
