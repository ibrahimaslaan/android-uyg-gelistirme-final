package com.ibrahim.news.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "news.db"

        val USER_TABLE_NAME = "table_user"
        val KEY_ID = "_id"
        val KEY_NAME = "name"
        val KEY_PASSWORD = "password"

        val NEWS_TABLE_NAME = "table_user_news"
        val KEY_NEWS_ID = "_id"
        val KEY_NEWS_AUTHOR = "author"
        val KEY_NEWS_TITLE = "title"
        val KEY_NEWS_DESCRIPTION = "description"
        val KEY_NEWS_URL = "url"
        val KEY_NEWS_URLTOIMAGE = "urlToImage"
        val KEY_NEWS_USER_ID = "userId"
        private var mInstance : DBHelper? = null

        @Synchronized fun getInstance(context: Context) : DBHelper {
            if(mInstance == null) {
                mInstance = DBHelper(context.applicationContext)
            }
            return mInstance as DBHelper
        }

    }

    private fun createTable(db : SQLiteDatabase) {
        val CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS $USER_TABLE_NAME($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, $KEY_NAME TEXT NOT NULL, $KEY_PASSWORD TEXT NOT NULL)"
        db.execSQL(CREATE_USER_TABLE)

        val CREATE_NEWS_TABLE = "CREATE TABLE IF NOT EXISTS $NEWS_TABLE_NAME($KEY_NEWS_ID INTEGER PRIMARY KEY AUTOINCREMENT, $KEY_NEWS_AUTHOR TEXT NOT NULL, $KEY_NEWS_TITLE TEXT NOT NULL, $KEY_NEWS_DESCRIPTION TEXT NOT NULL, $KEY_NEWS_URL TEXT NOT NULL, $KEY_NEWS_URLTOIMAGE TEXT NOT NULL, $KEY_NEWS_USER_ID INTEGER NOT NULL)"
        db.execSQL(CREATE_NEWS_TABLE)
    }

    override fun onCreate(db: SQLiteDatabase?) = createTable(db!!)

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USER_TABLE_NAME")
    }
}