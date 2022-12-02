package com.appnews

import com.appnews.data.TopHeadlinesRepo
import com.appnews.data.TopHeadlinesRepoImpl
import com.appnews.data.api.TopHeadlinesApi
import com.appnews.domain.GetHeadlines
import com.appnews.domain.GetHeadlinesUseCase
import com.appnews.network.ServiceNetwork
import com.appnews.presenter.viewmodels.HomeViewModel
import org.koin.dsl.module

val appNewsModule = module {

    single { ServiceNetwork().createService(TopHeadlinesApi::class.java) }

    single<TopHeadlinesRepo> { TopHeadlinesRepoImpl(get()) }

    single<GetHeadlinesUseCase> { GetHeadlines(get()) }

    single { HomeViewModel(get()) }

}