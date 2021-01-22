package com.ibrahim.news.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.widget.CircularProgressDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ibrahim.news.R
import com.ibrahim.news.data.local.NewsRepository
import com.ibrahim.news.models.Articles
import com.ibrahim.news.models.UserNews
import kotlinx.android.synthetic.main.row_item.view.*

class GridAdapter : BaseAdapter {

    var context : Context? = null
    var articles : List<Articles>
    var userId : Int? = null

    constructor(context : Context, articles:  List<Articles>, userId : Int){
        this.articles = articles
        this.context = context
        this.userId = userId
    }


    override fun getCount(): Int = articles.size

    override fun getItem(position: Int): Articles = articles[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val circularProgressDrawable = CircularProgressDrawable(context!!)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val requestOption = RequestOptions()
            .placeholder(circularProgressDrawable).centerCrop()

        val art = this.articles[position]
        var inf = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var artView = inf.inflate(R.layout.row_item, null)

        artView.card_txt_header.text = art.title
        artView.card_txt_description.text = art.description
        Glide.with(this.context!!)
            .load(art.urlToImage)
            .apply(requestOption)
            .into(artView.card_image)

        artView.card_save_news.setOnClickListener {
            var newsRepository : NewsRepository = NewsRepository(this.context!!)
            //Toast.makeText(this.context, "Haber Kaydedilecek", Toast.LENGTH_SHORT).show()
            val userNews = UserNews(author = art.author, title = art.title, description = art.description, urlToImage = art.urlToImage, url = art.url, userId = userId!!)
            val res : Long = newsRepository.insertNews(userNews)
            if (res > -1) {
                Toast.makeText(this.context, "Kaydedildi", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this.context, "Kaydedilemedi", Toast.LENGTH_SHORT).show()
            }
        }

        artView.card_go_news.setOnClickListener {
            //Toast.makeText(this.context, "URL ${art.url}", Toast.LENGTH_SHORT).show()
            val uris = Uri.parse(art.url)
            val intents = Intent(Intent.ACTION_VIEW, uris)
            this.context!!.startActivity(intents)
        }

        return  artView
    }


}