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
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var newsRepository : NewsRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        newsRepository = NewsRepository(this)
    }

    fun girisYap(view: View) {
        if(TextUtils.isEmpty(tv_login_email.text.toString()) || TextUtils.isEmpty(tv_login_password.text.toString())) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
        }
        else {
            val user : User? =  newsRepository.getUser(tv_login_email.text.toString(), tv_login_password.text.toString())
            if(user == null) {
                Toast.makeText(this, "Kullanıcı adı ve şifrenizi kontrol edin", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Giriş yaptın", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AskActivity::class.java)
                intent.putExtra("name", user.name)
                intent.putExtra("password", user.password)
                intent.putExtra("id", user.id)
                startActivity(intent)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                finish()
            }
        }
    }
    fun kayitOl(view: View) {
        val  intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        finish()
    }
}