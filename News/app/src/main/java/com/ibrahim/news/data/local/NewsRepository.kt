package com.ibrahim.news.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.ibrahim.news.models.User
import com.ibrahim.news.models.UserNews

class NewsRepository(var context: Context) {

    private var mDBHelper : DBHelper = DBHelper.getInstance(context)

    fun getUser(name_: String, password_ : String) : User? {
        var user : User? = null

        val db = mDBHelper.readableDatabase

        var query = "SELECT ${DBHelper.KEY_ID}, ${DBHelper.KEY_NAME}, ${DBHelper.KEY_PASSWORD} FROM ${DBHelper.USER_TABLE_NAME} WHERE ${DBHelper.KEY_NAME}='$name_' AND ${DBHelper.KEY_PASSWORD}='$password_'"

        val cursor : Cursor = db.rawQuery(query, null)
        if(cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID))
            val name = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME))
            val password = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PASSWORD))

            user = User(id, name, password)
        }
        cursor.close()
        db.close()
        return user
    }

    fun insertUser(user : User) : Long{

        val db = mDBHelper.writableDatabase
        val values = ContentValues()
        values.apply {
            put(DBHelper.KEY_NAME, user.name)
            put(DBHelper.KEY_PASSWORD, user.password)
        }
        val id : Long=  db.insert(DBHelper.USER_TABLE_NAME, null, values)
        db.close()

        return id
    }

    fun updateUser(id: Int, password : String) : Int {
        val db = mDBHelper.writableDatabase
        val values = ContentValues()
        values.apply {
            put(DBHelper.KEY_PASSWORD, password)
        }
        val id : Int =  db.update(DBHelper.USER_TABLE_NAME,values, DBHelper.KEY_ID + "= ?", arrayOf(id.toString()))
        db.close()
        return  id
    }

    fun insertNews(news : UserNews) : Long{

        val db = mDBHelper.writableDatabase
        val values = ContentValues()
        values.apply {
            put(DBHelper.KEY_NEWS_AUTHOR, news.author)
            put(DBHelper.KEY_NEWS_TITLE, news.title)
            put(DBHelper.KEY_NEWS_DESCRIPTION, news.description)
            put(DBHelper.KEY_NEWS_URL, news.url)
            put(DBHelper.KEY_NEWS_URLTOIMAGE, news.urlToImage)
            put(DBHelper.KEY_NEWS_USER_ID, news.userId)

        }
        val id : Long=  db.insert(DBHelper.NEWS_TABLE_NAME, null, values)
        db.close()

        return id
    }

    fun getUserNews(userId : Int) : ArrayList<UserNews>? {

        val db = mDBHelper.readableDatabase

        var query = "SELECT ${DBHelper.KEY_NEWS_ID}, ${DBHelper.KEY_NEWS_AUTHOR}, ${DBHelper.KEY_NEWS_TITLE}, ${DBHelper.KEY_NEWS_DESCRIPTION}, ${DBHelper.KEY_NEWS_URL}, ${DBHelper.KEY_NEWS_URLTOIMAGE}, ${DBHelper.KEY_NEWS_USER_ID} FROM ${DBHelper.NEWS_TABLE_NAME} WHERE ${DBHelper.KEY_NEWS_USER_ID}=$userId"

        var newsList = ArrayList<UserNews>()
        val cursor : Cursor = db.rawQuery(query, null)
        if(cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_NEWS_ID))
                val author = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NEWS_AUTHOR))
                val title = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NEWS_TITLE))
                val desc = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NEWS_DESCRIPTION))
                val url = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NEWS_URL))
                val imageUrl = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NEWS_URLTOIMAGE))
                val userId = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_NEWS_USER_ID))
                val userNews = UserNews(id, author, title, desc, url, imageUrl, userId)
                newsList.add(userNews)
            }while (cursor.moveToNext())


        }
        cursor.close()
        db.close()
        return newsList
    }

    fun deleteUserNews(url : String) : Int{
        val db = mDBHelper.writableDatabase

        val id : Int =  db.delete(DBHelper.NEWS_TABLE_NAME, DBHelper.KEY_NEWS_URL + "= ?", arrayOf(url))
        db.close()
        return  id
    }
}