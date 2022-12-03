package com.appnews.domain.model

import android.os.Parcelable
import com.designsystem.contracts.HeadlineCardContract
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article (
    val sourceName : String,
    var author: String,
    override var title: String,
    override var description: String,
    var url: String,
    override var imageUrl: String,
    var publishedAt: String,
    var content: String
) : HeadlineCardContract, Parcelable