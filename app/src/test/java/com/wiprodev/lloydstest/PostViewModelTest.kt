package com.wiprodev.lloydstest

import com.wiprodev.data.repository.PostRepositoryImpl
import com.wiprodev.domain.model.PostModel
import com.wiprodev.domain.usecase.FetchPostsUseCase
import com.wiprodev.lloydstest.state.PostsResponse
import com.wiprodev.lloydstest.viewmodel.PostsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PostViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var repositoryImpl: PostRepositoryImpl

    private lateinit var fetchPostsUseCase: FetchPostsUseCase

    @Before
    fun setUp() {
        fetchPostsUseCase = FetchPostsUseCase(repositoryImpl)
    }

    @Test
    fun check_response_success() = runTest {
        val expectedData = PostsResponse.Success(
            listOf(
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
        )
        Mockito.`when`(repositoryImpl.getPosts()).thenReturn(expectedData.data)
        val viewModel = PostsViewModel(fetchPostsUseCase)
        advanceTimeBy(1000)
        verify(repositoryImpl).getPosts()
        val result = viewModel.postResponse.first() as PostsResponse.Success
        Assert.assertEquals(expectedData, result)
    }
}