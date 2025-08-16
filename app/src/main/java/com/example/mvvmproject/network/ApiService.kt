package com.example.mvvmproject.network

import com.example.mvvmproject.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}