package com.wiprodev.domain

import com.wiprodev.domain.model.PostModel
import com.wiprodev.domain.repository.PostRepository
import com.wiprodev.domain.usecase.FetchPostsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FetchPostsUseCaseTest {
    @get:Rule
    val dispatchRule = MainDispatcherRule()

    @Mock
    lateinit var postRepository: PostRepository

    private lateinit var fetchPostsUseCase: FetchPostsUseCase

    @Before
    fun setUp() {
        fetchPostsUseCase = FetchPostsUseCase(postRepository)
    }

    @Test
    fun `repository returns posts, use case should emit data`() = runTest {
        val mockData = listOf(
            PostModel(
                userId = 1,
                title = "",
                body = "",
                likes = 10,
                disLikes = 5,
                views = 20,
                id = 1
            )
        )
        Mockito.`when`(postRepository.getPosts()).thenReturn(mockData)
        val result = fetchPostsUseCase.invoke()
        Assert.assertEquals(result, mockData)
    }

    @Test
    fun `repository throws an error, use case should propagate exception`() = runTest {
        val exception = RuntimeException("Network error")
        Mockito.`when`(postRepository.getPosts()).thenThrow(exception)
        val result = try {
            fetchPostsUseCase.invoke()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Assert.assertEquals(result, exception)
    }
}