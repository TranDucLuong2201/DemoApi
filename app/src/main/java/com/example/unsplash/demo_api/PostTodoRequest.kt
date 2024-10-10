package com.example.unsplash.demo_api

import com.google.gson.annotations.SerializedName

data class PostTodoRequest(
    @SerializedName("body")
    val body: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int,
)
