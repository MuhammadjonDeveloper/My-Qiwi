package com.example.muhammadjon.myproject.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtils {
    private ConnectivityManager manager;
    private static NetUtils INSTAINS;

    private NetUtils(Context ctx) {
        manager= (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static NetUtils getINSTAINS(Context ctx) {
        if (INSTAINS==null) {
            INSTAINS=new NetUtils(ctx);
        }
        return INSTAINS;
    }

    public boolean isOnline() {
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }
}
