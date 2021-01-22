package com.ibrahim.news.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.ibrahim.news.R
import com.ibrahim.news.data.local.NewsRepository
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity : AppCompatActivity() {
    private var userId : Int? = null
    private lateinit var newsRepository : NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        newsRepository = NewsRepository(this)

        userId = intent.getIntExtra("userId", -1)

    }

    fun sifreDegistir(view: View) {
        if(TextUtils.isEmpty( tv_new_password.text.toString()))
            Toast.makeText(this, "Lütfen bir şifre girin", Toast.LENGTH_SHORT).show()
        else {
            val res : Int =  newsRepository.updateUser(userId!!, tv_new_password.text.toString())
            if(res > -1) {
                finish()
            }else {
                Toast.makeText(this, "Şifre değiştirilemedi", Toast.LENGTH_SHORT).show()
            }
        }
        newsRepository.updateUser(userId!!, tv_new_password.text.toString())
    }
}