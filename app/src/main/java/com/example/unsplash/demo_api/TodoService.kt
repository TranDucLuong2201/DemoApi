package com.example.unsplash.demo_api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TodoService {

    @GET("/todos/1")
    fun getTodos(): Call<TodoResponse>

    @GET("/todos/1")
    suspend fun getTodosSuspend(): TodoResponse

    @GET("/users")
    suspend fun getUsersSuspend(): List<GetListDemoItem>

    @POST("/posts")
    suspend fun postUserSuspend(@Body postTodoRequest: PostTodoRequest): PostDemoResponse


    companion object {
        fun retrofitProvider(retrofit: Retrofit): TodoService {
            return retrofit.create(TodoService::class.java)
        }
    }
}