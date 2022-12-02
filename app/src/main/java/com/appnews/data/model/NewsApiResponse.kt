package com.appnews.data.model

data class NewsApiResponse(
    var status: String,
    var code: String,
    var message: String,
    var totalResults: Int,
    var articles: ArrayList<ArticlesResponse>
)
