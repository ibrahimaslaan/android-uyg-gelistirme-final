package com.ibrahim.news.data.remote

import com.ibrahim.news.models.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    // Top Headlines
    @GET("top-headlines")
    fun getTopHeadlinesNews() : Call<ArticleResponse>

}