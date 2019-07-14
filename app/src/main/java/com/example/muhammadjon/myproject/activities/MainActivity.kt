package com.example.muhammadjon.myproject.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar

import com.example.muhammadjon.myproject.R
import com.example.muhammadjon.myproject.dbase.AppDataBase
import com.example.muhammadjon.myproject.dbase.CategoriesDao
import com.example.muhammadjon.myproject.dbase.FieldsDao
import com.example.muhammadjon.myproject.dbase.MerchantsDao
import com.example.muhammadjon.myproject.dbase.MyPojo
import com.example.muhammadjon.myproject.dbase.ValuesDao
import com.example.muhammadjon.myproject.network.ApiService
import com.example.muhammadjon.myproject.network.NetworkModul

import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var service: ApiService? = null
    private var categoriesDao: CategoriesDao? = null
    private var merchantsDao: MerchantsDao? = null
    private var fieldsDao: FieldsDao? = null
    private var valuesDao: ValuesDao? = null
    private val cd = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorBlueT)
        }
        paynet_uz!!.setOnClickListener(this)
        payment_main!!.setOnClickListener(this)
        webmoney!!.setOnClickListener(this)

        paynet_uz_pb!!.visibility = View.INVISIBLE
        val networkModul = NetworkModul(this)
        service = networkModul.apiservice
        categoriesDao = AppDataBase.getInstaince(this).categoryDao
        fieldsDao = AppDataBase.getInstaince(this).fielsDao
        merchantsDao = AppDataBase.getInstaince(this).merchantDao
        valuesDao = AppDataBase.getInstaince(this).valuesDao
    }

    override fun onDestroy() {
        super.onDestroy()
        if (cd != null && cd.isDisposed) {
            cd.dispose()
        }
    }

    override fun onClick(view: View) {
        paynet_uz_pb!!.visibility = View.VISIBLE
        val single = service!!.event()
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<MyPojo> {
                    override fun onSubscribe(d: Disposable) {
                        cd.add(d)
                    }

                    override fun onSuccess(event: MyPojo) {
                        categoriesDao!!.insert(event.categories!!)
                        merchantsDao!!.insert(event.merchants!!)
                        valuesDao!!.insert(event.values!!)
                        fieldsDao!!.insert(event.fields!!)
                        paynet_uz_pb!!.visibility = View.GONE
                        val intent = Intent(this@MainActivity, CategoryActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onError(e: Throwable) {
                        paynet_uz_pb!!.visibility = View.INVISIBLE
                        Log.e(TAG, "onError: " + e.message)
                    }
                })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
