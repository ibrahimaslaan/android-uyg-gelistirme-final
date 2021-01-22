package com.ibrahim.news.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import kotlinx.android.synthetic.main.row_item.view.card_image
import kotlinx.android.synthetic.main.row_item2.view.*

class Grid2Adapter: BaseAdapter {
    var context : Context? = null
    var userNews : ArrayList<UserNews>

    constructor(context : Context, userNews:  ArrayList<UserNews>){
        this.userNews = userNews
        this.context = context
    }


    override fun getCount(): Int = userNews.size

    override fun getItem(position: Int): UserNews = userNews[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val circularProgressDrawable = CircularProgressDrawable(context!!)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val requestOption = RequestOptions()
            .placeholder(circularProgressDrawable).centerCrop()

        val news = this.userNews[position]
        var inf = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var artView = inf.inflate(R.layout.row_item2, null)

        artView.card_txt_header2.text = news.title
        artView.card_txt_description2.text = news.description
        Glide.with(this.context!!)
            .load(news.urlToImage)
            .apply(requestOption)
            .into(artView.card_image2)

        artView.card_delete_news2.setOnClickListener {
            var newsRepository : NewsRepository = NewsRepository(this.context!!)
            // DELETE
            val res : Int = newsRepository.deleteUserNews(url = news.url)
            if(res > -1 ) {
                userNews.remove(news)
                notifyDataSetChanged()
            }
        }

        artView.card_go_news2.setOnClickListener {
            //Toast.makeText(this.context, "URL ${art.url}", Toast.LENGTH_SHORT).show()
            val uris = Uri.parse(news.url)
            val intents = Intent(Intent.ACTION_VIEW, uris)
            this.context!!.startActivity(intents)
        }

        return  artView
    }

}