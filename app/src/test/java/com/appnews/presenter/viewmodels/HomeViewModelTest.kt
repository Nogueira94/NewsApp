package com.appnews.presenter.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.appnews.domain.GetHeadlinesUseCase
import com.appnews.domain.model.Article
import com.appnews.domain.model.StateView
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class HomeViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }


    @Test
    fun `test when getHeadlines in viewModel is success`() = runBlockingTest {

        val articleList = listOf(mockkClass(Article::class), mockkClass(Article::class))
        val mockGetHeadlines = mockk<GetHeadlinesUseCase>()
        coEvery { mockGetHeadlines.invoke() } returns StateView.DataLoaded(articleList)
        val homeViewModel = HomeViewModel(mockGetHeadlines)

        homeViewModel.getArticles()
        dispatcher.scheduler.advanceUntilIdle()

        assertTrue(homeViewModel.headlinesState.value is StateView.DataLoaded)

        val dataLoaded = homeViewModel.headlinesState.value as StateView.DataLoaded

        assertEquals(2, dataLoaded.data.size)
    }

    @Test
    fun `test when getHeadlines in viewModel is fail`() = runBlockingTest {

        val mockGetHeadlines = mockk<GetHeadlinesUseCase>()

        coEvery { mockGetHeadlines.invoke() } returns StateView.Error(Exception("Test exception"))

        val homeViewModel = HomeViewModel(mockGetHeadlines)

        homeViewModel.getArticles()
        dispatcher.scheduler.advanceUntilIdle()

        assertTrue(homeViewModel.headlinesState.value is StateView.Error)

    }


}