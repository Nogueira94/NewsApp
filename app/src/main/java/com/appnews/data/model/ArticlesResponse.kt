package com.appnews.data.model

import com.appnews.domain.model.Article
import java.util.Date

data class ArticlesResponse(
    var source: SourceResponse,
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: Date,
    var content: String
)

fun ArticlesResponse.toArticle() = Article(
    sourceName = source.name,
    author = author,
    title = title,
    description = description,
    url = url,
    imageUrl = urlToImage,
    publishedAt = publishedAt,
    content = content
)