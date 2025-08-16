package com.example.mvvmproject.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmproject.network.RetrofitClient
import com.example.mvvmproject.repository.PostsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {

    private val repository = PostsRepository(RetrofitClient.apiService)

    private val _uiState = MutableStateFlow<UiState<List<com.example.mvvmproject.model.Post>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<com.example.mvvmproject.model.Post>>> = _uiState.asStateFlow()

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val posts = repository.fetchPosts()
                _uiState.value = UiState.Success(posts)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }
}