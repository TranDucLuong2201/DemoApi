package com.example.unsplash.demo_api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val todoService by lazy {
        TodoService.retrofitProvider(retrofit)
    }
}