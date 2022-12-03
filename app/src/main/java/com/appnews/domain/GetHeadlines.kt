package com.appnews.domain

import com.appnews.data.TopHeadlinesRepo
import com.appnews.data.model.NetworkResult
import com.appnews.domain.model.Article
import com.appnews.domain.model.StateView

class GetHeadlines(private val topHeadlinesRepo: TopHeadlinesRepo) : GetHeadlinesUseCase {
    override suspend fun invoke() : StateView<List<Article>> {
        return when(val articleList = topHeadlinesRepo.getHeadlines()){
            is NetworkResult.Success -> StateView.DataLoaded(articleList.data.sortedByDescending {
                it.publishedAt
            })
            is NetworkResult.Error -> StateView.Error(articleList.exception)
        }
    }
}

interface GetHeadlinesUseCase{
    suspend operator fun invoke() : StateView<List<Article>>
}