package com.example.muhammadjon.myproject.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.example.muhammadjon.myproject.R
import com.example.muhammadjon.myproject.adapter.CategoryAdapter
import com.example.muhammadjon.myproject.dbase.AppDataBase
import com.example.muhammadjon.myproject.dbase.Categories
import com.example.muhammadjon.myproject.dbase.CategoriesDao
import kotlinx.android.synthetic.main.category_activity.*

import java.util.ArrayList

class CategoryActivity : AppCompatActivity() {
    private var dao: CategoriesDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_activity)
        dao = AppDataBase.getInstaince(this).categoryDao

        val list = dao!!.all as ArrayList<Categories>
        val adapter = CategoryAdapter(this)
        val manager = LinearLayoutManager(this)
        window_category_rv.layoutManager = manager
        window_category_rv.adapter = adapter
        adapter.setItems(list)
    }
}