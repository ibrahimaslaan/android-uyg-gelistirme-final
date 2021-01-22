package com.ibrahim.news.models

data class UserNews(
        var id : Int = 0,
        var author : String,
        var title : String,
        var description : String,
        var url : String,
        var urlToImage : String,
        var userId : Int
)
