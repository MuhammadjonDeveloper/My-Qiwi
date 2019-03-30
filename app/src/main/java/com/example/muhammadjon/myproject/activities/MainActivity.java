package com.example.muhammadjon.myproject.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.muhammadjon.myproject.R;
import com.example.muhammadjon.myproject.dbase.AppDataBase;
import com.example.muhammadjon.myproject.dbase.CategoriesDao;
import com.example.muhammadjon.myproject.dbase.FieldsDao;
import com.example.muhammadjon.myproject.dbase.MerchantsDao;
import com.example.muhammadjon.myproject.dbase.MyPojo;
import com.example.muhammadjon.myproject.dbase.ValuesDao;
import com.example.muhammadjon.myproject.network.ApiService;
import com.example.muhammadjon.myproject.network.NetworkModul;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ApiService service;
    private LinearLayout btn_paynet,btn_payme,btn_webmoney;
    private ProgressBar paynetuzpb;
    private CategoriesDao categoriesDao;
    private MerchantsDao merchantsDao;
    private FieldsDao fieldsDao;
    private ValuesDao valuesDao;
    private CompositeDisposable cd = new CompositeDisposable();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBlueT));
        }
        btn_paynet = findViewById(R.id.paynet_uz);
        btn_payme=findViewById(R.id.payment_main);
        btn_webmoney=findViewById(R.id.webmoney);
        btn_paynet.setOnClickListener(this);
        btn_webmoney.setOnClickListener(this);
        btn_payme.setOnClickListener(this);

        paynetuzpb = findViewById(R.id.paynet_uz_pb);
        paynetuzpb.setVisibility(View.INVISIBLE);
        NetworkModul networkModul = new NetworkModul(this);
        service = networkModul.getApiservice();
        categoriesDao= AppDataBase.getInstaince(this).getCategoryDao();
        fieldsDao= AppDataBase.getInstaince(this).getFielsDao();
        merchantsDao= AppDataBase.getInstaince(this).getMerchantDao();
        valuesDao=AppDataBase.getInstaince(this).getValuesDao();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cd != null && cd.isDisposed()) {
            cd.dispose();
        }
    }

    @Override
    public void onClick(View view) {
        paynetuzpb.setVisibility(View.VISIBLE);
        Single<MyPojo> single = service.event();
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MyPojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onSuccess(MyPojo event) {
                        categoriesDao.insert(event.getCategories());
                        merchantsDao.insert(event.getMerchants());
                        valuesDao.insert(event.getValues());
                        fieldsDao.insert(event.getFields());
                        paynetuzpb.setVisibility(View.GONE);
                        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        paynetuzpb.setVisibility(View.INVISIBLE);
                        Log.e(TAG, "onError: " + e.getMessage());
                    }
                });
    }
}
