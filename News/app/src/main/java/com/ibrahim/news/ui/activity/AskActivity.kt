package com.ibrahim.news.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.ibrahim.news.R
import kotlinx.android.synthetic.main.activity_ask.*

class AskActivity : AppCompatActivity() {
    private var userId : Int? = null
    private var userName : String? = null
    private var userPassword : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)

        setSupportActionBar(ask_toolbar as Toolbar?)
        userId = intent.getIntExtra("id", -1)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_updatePassword -> {
                val intent = Intent(this, ChangePasswordActivity::class.java)
                intent.putExtra("userId", userId)
                startActivity(intent)
                return  true
            }
            R.id.action_logout -> {
                val intent = Intent(this, LoginActivity::class.java)

                startActivity(intent)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                finish()
                return  true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    fun gunceleGit(view: View) {
        val intent = Intent(this, HeadlineActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }
    fun kaydettiklerimeGit(view: View) {
        val intent = Intent(this, UserNewsActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }
}