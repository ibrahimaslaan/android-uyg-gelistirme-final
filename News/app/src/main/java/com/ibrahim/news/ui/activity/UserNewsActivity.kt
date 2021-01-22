package com.ibrahim.news.ui.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ibrahim.news.R
import com.ibrahim.news.data.local.NewsRepository
import com.ibrahim.news.data.remote.ApiClient
import com.ibrahim.news.models.UserNews
import com.ibrahim.news.service.MyReceiver
import com.ibrahim.news.ui.adapter.Grid2Adapter
import com.ibrahim.news.ui.adapter.GridAdapter
import com.ibrahim.news.util.gone
import com.ibrahim.news.util.visible
import kotlinx.android.synthetic.main.activity_user_news.*

class UserNewsActivity : AppCompatActivity() {

    private var userId : Int? = null
    private lateinit var newsRepository : NewsRepository
    var adapter: Grid2Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_news)

        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)
        registerReceiver(MyReceiver(), intentFilter)

        userId = intent.getIntExtra("id", -1)
        newsRepository = NewsRepository(this)
        val userNews : ArrayList<UserNews>? = newsRepository.getUserNews(userId = userId!!)

        if (userNews != null) {

            adapter = Grid2Adapter(this, userNews)
            user_news_gridView.adapter = adapter
            user_news_progress.gone()
            user_news_gridView.visible()
        }
        else {
            Toast.makeText(this, "kayıtlı haber yok", Toast.LENGTH_SHORT).show()
        }
        //adapter = GridAdapter(this@HeadlineActivity, res, userId!!)
    }
}