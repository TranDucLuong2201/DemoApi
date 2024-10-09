package com.example.unsplash.demo_api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

interface TodoService {

    @GET("/todos/1")
    fun getTodos(): Call<TodoResponse>

    @GET("/todos/1")
    suspend fun getTodosSuspend(): TodoResponse


    companion object {
        fun retrofitProvider(retrofit: Retrofit): TodoService {
            return retrofit.create(TodoService::class.java)
        }
    }
}