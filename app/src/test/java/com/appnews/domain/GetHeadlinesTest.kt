package com.appnews.domain

import com.appnews.data.TopHeadlinesRepo
import com.appnews.data.model.NetworkResult
import com.appnews.domain.model.Article
import com.appnews.domain.model.StateView
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetHeadlinesTest{

    val yesterday = Date.from(LocalDate.now().minusDays(2).atStartOfDay().toInstant(
        ZoneOffset.MIN))

    val articleList = listOf<Article>(
        Article("BBC News","BBC News","BBC News","BBC News","BBC News", "BBC News",yesterday,"BBC News"),
        Article("BBC News","BBC News","BBC News","BBC News","BBC News", "BBC News",Date(),"BBC News"),
    )

    @Test
    fun `test domain layer when GetHeadlines() success`() = runBlockingTest {
        val mockTopHeadlinesRepo = mockk<TopHeadlinesRepo> {
            coEvery { getHeadlines() } returns NetworkResult.Success(articleList)
        }

        val result = GetHeadlines(mockTopHeadlinesRepo).invoke()

        assertTrue(result is StateView.DataLoaded)

        val dataLoaded = result as StateView.DataLoaded

        assertEquals(2, dataLoaded.data.size)

        assertTrue(dataLoaded.data[0].publishedAt >= dataLoaded.data[1].publishedAt)
    }

    @Test
    fun `test domain layer when GetHeadlines() fail`() = runBlockingTest {
        val mockTopHeadlinesRepo = mockk<TopHeadlinesRepo> {
            coEvery { getHeadlines() } returns NetworkResult.Error(Exception("Test exception"))
        }
        val result = GetHeadlines(mockTopHeadlinesRepo).invoke()

        assertTrue(result is StateView.Error)
    }
}