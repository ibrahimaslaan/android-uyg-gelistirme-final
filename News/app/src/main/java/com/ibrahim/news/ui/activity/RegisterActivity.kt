package com.ibrahim.news.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.ibrahim.news.R
import com.ibrahim.news.data.local.NewsRepository
import com.ibrahim.news.models.User
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var newsRepository : NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        newsRepository = NewsRepository(this)
    }

    fun girisSayfasinaGit(view: View) {
        logineGit()
    }
    fun kaydol(view: View) {
        if (TextUtils.isEmpty(tv_register_email.text.toString()) || TextUtils.isEmpty(tv_register_password.text.toString()) || TextUtils.isEmpty(tv_register_re_password.text.toString())) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
        }else {
            if(!tv_register_password.text.toString().equals(tv_register_re_password.text.toString())) {
                Toast.makeText(this, "şifreler eşleşmiyor", Toast.LENGTH_SHORT).show()
            }else {
                var id : Long =  newsRepository.insertUser(User(name = tv_register_email.text.toString(), password = tv_register_password.text.toString()))
                if (id > -1) {
                    Toast.makeText(this, "Kullanıcı oluşturuldu, giriş yapabilirsiniz", Toast.LENGTH_SHORT).show()
                    logineGit()
                }
            }
        }
    }

    private fun logineGit() {
        val  intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        finish()
    }
}