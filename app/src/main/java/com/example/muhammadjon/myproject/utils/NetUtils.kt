package com.example.muhammadjon.myproject.utils


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetUtils private constructor(ctx: Context) {
    private val manager: ConnectivityManager = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val isOnline: Boolean
        get() {
            val info = manager.activeNetworkInfo
            return info != null && info.isConnected
        }

    companion object {
        private var INSTAINS: NetUtils? = null

        fun getINSTAINS(ctx: Context): NetUtils {
            if (INSTAINS == null) {
                INSTAINS = NetUtils(ctx)
            }
            return INSTAINS as NetUtils
        }
    }
}
