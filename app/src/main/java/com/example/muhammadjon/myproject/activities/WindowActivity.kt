package com.example.muhammadjon.myproject.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.muhammadjon.myproject.R
import com.example.muhammadjon.myproject.adapter.WindowAdapter
import com.example.muhammadjon.myproject.dbase.Merchants
import com.example.muhammadjon.myproject.presentation.IWindowPrisenter
import com.example.muhammadjon.myproject.presentation.WindowPresenter
import kotlinx.android.synthetic.main.category_activity.*
import java.util.ArrayList

class WindowActivity : AppCompatActivity(), IWindowView {
    private var categoryid: Long = 0
    private var prisenter: IWindowPrisenter? = null
    private var pBar: ProgressBar? = null
    private var adapter: WindowAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.window_category)
        pBar = findViewById(R.id.progressBar_window)
        val arg = intent.extras
        prisenter = WindowPresenter.getINSTAINCE(this)
        if (arg != null) {
            categoryid = arg.getLong("key")
        }
        Toast.makeText(this, "id = $categoryid", Toast.LENGTH_SHORT).show()
        adapter = WindowAdapter(this)
        val manager = LinearLayoutManager(this)
        window_category_rv.layoutManager = manager
        prisenter!!.onMerchants(categoryid.toInt())

    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

    override fun onHidePb() {
        pBar!!.progress = View.INVISIBLE
    }

    override fun onShowPb() {
        pBar!!.progress = View.VISIBLE
    }

    override fun onResult(merchants: List<Merchants>) {
        adapter!!.setItemMarches(merchants as ArrayList<Merchants>)
        window_category_rv.adapter = adapter
    }

    override fun onError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
