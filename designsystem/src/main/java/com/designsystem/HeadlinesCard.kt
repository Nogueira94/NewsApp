package com.designsystem

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.designsystem.contracts.HeadlineCardContract

class HeadlinesCard @JvmOverloads constructor(
    context: Context,attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayoutCompat(context,attrs,defStyleAttr) {

    private val title: TextView
    private val desc: TextView
    private val image: ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.headlines_card,this,true)

        title = findViewById(R.id.headline_tile)
        desc = findViewById(R.id.headline_desc)
        image = findViewById(R.id.headline_img)
    }

    fun setHeadline(article: HeadlineCardContract) {
        title.text = article.title
        desc.text = article.description
        Glide.with(image.context).asBitmap()
            .load(article.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(image)
    }

}