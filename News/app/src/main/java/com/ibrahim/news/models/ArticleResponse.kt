package com.ibrahim.news.models

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("articles")
    var articles : List<Articles>
)
