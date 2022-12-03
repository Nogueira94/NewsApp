package com.appnews.presenter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.appnews.databinding.FragmentArticleDetailsBinding
import com.appnews.domain.model.Article
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ArticleDetailsFragment : Fragment() {

    private lateinit var binding: FragmentArticleDetailsBinding
    private var article : Article? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)

        article = requireArguments().getParcelable("ARTICLE")

        setupView()
        return binding.root
    }

    private fun setupView() {
        article?.let {
            binding.apply {
                headlineContent.text = it.content
                headlineDesc.text = it.description
                headlineTile.text = it.title
                Glide.with(requireContext()).asBitmap()
                    .load(it.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(headlineImg)
            }
        }
    }
}