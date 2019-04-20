package com.example.muhammadjon.myproject.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.muhammadjon.myproject.dbase.AppDataBase;
import com.example.muhammadjon.myproject.dbase.Merchants;
import com.example.muhammadjon.myproject.dbase.MerchantsDao;
import com.example.muhammadjon.myproject.utils.NetUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class WindowRepository implements IWindowRepository {
    private MutableLiveData<List<Merchants>> data;
    private NetUtils utils;
    private MerchantsDao dao;
    private static IWindowRepository INSTAINS;

    public static IWindowRepository getINSTAINS(Context ctx) {
        if (INSTAINS==null) {
            INSTAINS=new WindowRepository(ctx);
        }
        return INSTAINS;
    }

    private WindowRepository(Context ctx) {
    this.data=new MutableLiveData<>();
    this.dao= AppDataBase.getInstaince(ctx).getMerchantDao();
    this.utils=NetUtils.getINSTAINS(ctx);
    }

    @Override
    public LiveData<List<Merchants>> search(int categoryid) {
        dao.getAll(categoryid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<List<Merchants>>() {
                    @Override
                    public void onSuccess(List<Merchants> merchants) {
                        if (merchants!=null&&!merchants.isEmpty()) {
                            data.postValue(merchants);
                        }else{
                            data.postValue(null);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return data;
    }
}
