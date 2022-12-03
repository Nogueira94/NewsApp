package com.appnews.presenter.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.appnews.R
import com.appnews.databinding.HeadlinesItemBinding
import com.appnews.domain.model.Article
import com.designsystem.contracts.HeadlineCardContract

class TopHeadlinesAdapter(private val articles: List<HeadlineCardContract>, private val parentFragment: Fragment) : RecyclerView.Adapter<TopHeadlinesAdapter.ArticleViewHolder>() {

    private lateinit var navController : NavController

    inner class ArticleViewHolder(val binding: HeadlinesItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = HeadlinesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        navController = parentFragment.findNavController()
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.articleCard.setHeadline(article)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("ARTICLE",article as Article)
            navController.navigate(R.id.action_homeFragmentNav_to_articleDetailsFragmentNav,bundle)
        }
    }

    override fun getItemCount(): Int = articles.size
}