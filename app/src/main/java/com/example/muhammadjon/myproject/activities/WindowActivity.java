package com.example.muhammadjon.myproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.muhammadjon.myproject.R;
import com.example.muhammadjon.myproject.adapter.WindowAdapter;
import com.example.muhammadjon.myproject.dbase.AppDataBase;
import com.example.muhammadjon.myproject.dbase.Merchants;
import com.example.muhammadjon.myproject.dbase.MerchantsDao;

import java.util.ArrayList;

public class WindowActivity extends AppCompatActivity {
    private MerchantsDao merchantsdao;
    private RecyclerView rv;
    private long categoryid;
    private ArrayList<Merchants> merchanlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_category);
        Bundle arg = getIntent().getExtras();
        if (arg != null) {
            categoryid = arg.getLong("key");
        }
        Toast.makeText(this, "id = " + categoryid, Toast.LENGTH_SHORT).show();
        rv = findViewById(R.id.window_category_rv);
        merchantsdao = AppDataBase.getInstaince(this).getMerchantDao();
        WindowAdapter adapter = new WindowAdapter(this);
        merchanlist = (ArrayList<Merchants>) merchantsdao.getAll(categoryid);
        adapter.setItemMarches(merchanlist);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
