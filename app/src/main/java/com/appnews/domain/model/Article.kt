package com.appnews.domain.model

import com.designsystem.contracts.HeadlineCardContract

data class Article (
    val sourceName : String,
    var author: String,
    override var title: String,
    override var description: String,
    var url: String,
    override var imageUrl: String,
    var publishedAt: String,
    var content: String
) : HeadlineCardContract