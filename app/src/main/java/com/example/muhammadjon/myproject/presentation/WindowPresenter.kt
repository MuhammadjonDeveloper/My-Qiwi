package com.example.muhammadjon.myproject.presentation

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import com.example.muhammadjon.myproject.activities.IWindowView
import com.example.muhammadjon.myproject.activities.WindowActivity
import com.example.muhammadjon.myproject.dbase.Merchants
import com.example.muhammadjon.myproject.model.IWindowRepository
import com.example.muhammadjon.myproject.model.WindowRepository

class WindowPresenter private constructor(activity: WindowActivity) : IWindowPrisenter {
    private val repository: IWindowRepository
    private val owner: LifecycleOwner
    private val view: IWindowView

    init {
        this.owner = activity
        this.view = activity
        repository = WindowRepository.getINSTAINS(activity)

    }

    override fun onMerchants(categoryId: Int) {
        view.onShowPb()
        repository.search(categoryId)
                .observe(owner, Observer<List<Merchants>> { this.checklist(it) })
    }

    private fun checklist(merchants: List<Merchants>?) {
        if (merchants == null) {
            view.onHidePb()
            view.onError("ERROR MASSAGE :")
        } else {
            view.onResult(merchants)
            view.onHidePb()
        }


    }

    companion object {
        private var INSTAINCE: IWindowPrisenter? = null

        fun getINSTAINCE(activity: WindowActivity): IWindowPrisenter {
            if (INSTAINCE == null) {
                INSTAINCE = WindowPresenter(activity)
            }
            return INSTAINCE as IWindowPrisenter
        }
    }
}
