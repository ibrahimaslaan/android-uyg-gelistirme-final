package com.ibrahim.news.ui.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ibrahim.news.R
import com.ibrahim.news.data.remote.ApiClient
import com.ibrahim.news.models.ArticleResponse
import com.ibrahim.news.models.Articles
import com.ibrahim.news.service.MyReceiver
import com.ibrahim.news.ui.adapter.GridAdapter
import com.ibrahim.news.util.gone
import com.ibrahim.news.util.visible
import kotlinx.android.synthetic.main.activity_headline.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadlineActivity : AppCompatActivity() {

    private var userId : Int? = null
    private val apiClient = ApiClient.getApiService()
    var adapter: GridAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headline)

        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)
        registerReceiver(MyReceiver(), intentFilter)

        userId = intent.getIntExtra("id", -1)
        apiClient.getTopHeadlinesNews().enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                val res : List<Articles>? = response.body()?.articles
                if (res != null) {

                    adapter = GridAdapter(this@HeadlineActivity, res, userId!!)
                    headline_gridview.adapter = adapter

                    headline_progress.gone()
                    headline_gridview.visible()
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                Toast.makeText(this@HeadlineActivity, "Alert", Toast.LENGTH_SHORT).show()
            }

        })
    }
}