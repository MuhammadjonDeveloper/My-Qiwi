package com.example.muhammadjon.myproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.muhammadjon.myproject.R;
import com.example.muhammadjon.myproject.adapter.CategoryAdapter;
import com.example.muhammadjon.myproject.dbase.AppDataBase;
import com.example.muhammadjon.myproject.dbase.Categories;
import com.example.muhammadjon.myproject.dbase.CategoriesDao;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private CategoriesDao dao;
    private RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);
        rv=findViewById(R.id.window_category_rv);
        dao = AppDataBase.getInstaince(this).getCategoryDao();

        ArrayList<Categories> list = (ArrayList<Categories>) dao.getAll();
        CategoryAdapter adapter = new CategoryAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        adapter.setItems(list);
    }
}
