package com.example.mvvmproject.repository

import com.example.mvvmproject.model.Post
import com.example.mvvmproject.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsRepository(private val api: ApiService) {
    suspend fun fetchPosts(): List<Post> = withContext(Dispatchers.IO) {
        api.getPosts()
    }
}