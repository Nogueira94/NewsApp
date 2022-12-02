package com.appnews.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appnews.databinding.HeadlinesItemBinding
import com.designsystem.contracts.HeadlineCardContract

class TopHeadlinesAdapter(private val articles: List<HeadlineCardContract>) : RecyclerView.Adapter<TopHeadlinesAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: HeadlinesItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = HeadlinesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]

        holder.binding.articleCard.setHeadline(article)

    }

    override fun getItemCount(): Int = articles.size
}