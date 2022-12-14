package com.appnews.data.api

import com.appnews.BuildConfig
import com.appnews.data.model.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopHeadlinesApi {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("sources") source : String = BuildConfig.API_SOURCE
    ) : Response<NewsApiResponse>
}