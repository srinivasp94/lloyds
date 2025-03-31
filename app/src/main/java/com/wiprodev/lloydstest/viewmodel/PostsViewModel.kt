package com.wiprodev.lloydstest.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiprodev.domain.model.PostModel
import com.wiprodev.domain.usecase.FetchPostsUseCase
import com.wiprodev.lloydstest.state.PostsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val fetchPostsUseCase: FetchPostsUseCase) :
    ViewModel() {

    private val _postsResponse = MutableStateFlow<PostsResponse<List<PostModel>>>(PostsResponse.Loading())
    val postResponse = _postsResponse.asStateFlow()

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        _postsResponse.value = PostsResponse.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _postsResponse.value = try {
                val response = fetchPostsUseCase.invoke()
                PostsResponse.Success(response)
            } catch (e: Exception) {
                e.printStackTrace()
                PostsResponse.Failure(e.message.toString())
            }
        }
    }

}