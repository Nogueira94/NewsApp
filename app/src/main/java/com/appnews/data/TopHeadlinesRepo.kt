package com.appnews.data

import com.appnews.data.api.TopHeadlinesApi
import com.appnews.data.model.NetworkResult
import com.appnews.data.model.StatusResponse
import com.appnews.data.model.toArticle
import com.appnews.domain.model.Article
import com.appnews.network.ApiResult
import com.appnews.network.parseResult

class TopHeadlinesRepoImpl(
    private val service: TopHeadlinesApi
) : TopHeadlinesRepo {
    override suspend fun getHeadlines(): NetworkResult<List<Article>> {
        val result = service.getHeadlines().parseResult()

        return when(result) {
            is ApiResult.Success -> {

                when(result.value.status.toStatus()){
                    StatusResponse.OK -> {
                        val articleList = result.value.articles.map {
                            it.toArticle()
                        }
                        NetworkResult.Success(articleList)
                    }
                    StatusResponse.ERROR -> NetworkResult.Error(TopHeadlinesException(result.value.message))
                }

            }
            is ApiResult.Failure -> NetworkResult.Error(TopHeadlinesException(result.statusDesc.toString()))
        }
    }

}

interface TopHeadlinesRepo{
    suspend fun getHeadlines() : NetworkResult<List<Article>>
}

class TopHeadlinesException(override val message : String) : Exception()

fun String.toStatus() : StatusResponse = when(this){
    "ok" -> StatusResponse.OK
    else -> StatusResponse.ERROR
}