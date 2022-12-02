package com.appnews.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val NEWS_API_URL = "https://newsapi.org/"
private const val API_VERSION = "v2/"

class ServiceNetwork {

    private val retrofit = Retrofit.Builder()
        .baseUrl(NEWS_API_URL + API_VERSION)
        .client(getOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> createService(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }

    private fun getOkHttpClient() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val headerInterceptor = Interceptor{ chain ->
            val builder = chain.request().newBuilder()
                .addHeader("X-Api-Key",BuildConfig.NEWS_API_KEY)
                .build()
            chain.proceed(builder)
        }

        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120,TimeUnit.SECONDS)
            .build()

    }

}