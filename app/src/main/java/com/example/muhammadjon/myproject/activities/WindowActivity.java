package com.example.muhammadjon.myproject.activities;

import android.opengl.Visibility;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.muhammadjon.myproject.R;
import com.example.muhammadjon.myproject.adapter.WindowAdapter;
import com.example.muhammadjon.myproject.dbase.AppDataBase;
import com.example.muhammadjon.myproject.dbase.Merchants;
import com.example.muhammadjon.myproject.dbase.MerchantsDao;
import com.example.muhammadjon.myproject.presentation.IWindowPrisenter;
import com.example.muhammadjon.myproject.presentation.WindowPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.observers.BlockingBaseObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class WindowActivity extends AppCompatActivity implements IWindowView {
    private RecyclerView rv;
    private long categoryid;
    private IWindowPrisenter prisenter;
    private ProgressBar pBar;
    private WindowAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_category);
        pBar = findViewById(R.id.progressBar_window);
        Bundle arg = getIntent().getExtras();
        if (arg != null) {
            categoryid = arg.getLong("key");
        }
        prisenter = WindowPresenter.getINSTAINCE(this);
        prisenter.onMerchants((int) categoryid);
        Toast.makeText(this, "id = " + categoryid, Toast.LENGTH_SHORT).show();
        rv = findViewById(R.id.window_category_rv);
        adapter = new WindowAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onHidePb() {
        pBar.setProgress(View.INVISIBLE);
    }

    @Override
    public void onShowPb() {
      pBar.setProgress(View.VISIBLE);
    }

    @Override
    public void onResult(List<Merchants> merchants) {
     adapter.setItemMarches((ArrayList<Merchants>) merchants);
     rv.setAdapter(adapter);
    }

    @Override
    public void onError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
