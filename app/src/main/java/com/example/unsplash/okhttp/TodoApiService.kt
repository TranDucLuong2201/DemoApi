package com.example.unsplash.okhttp

import com.example.unsplash.BuildConfig
import com.example.unsplash.okhttp.intercepter.AuthInterceptor
import com.example.unsplash.okhttp.intercepter.CustomHeaderInterceptor
import com.example.unsplash.okhttp.intercepter.JwtTokenManager
import com.rxmobileteam.android_006.okhttp.TodoItemResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import retrofit2.converter.moshi.MoshiConverterFactory

internal interface TodoApiService{
    @GET("todos")
    suspend fun getTodos(): List<TodoItemResponse>
}

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    Level.BODY
                } else Level.NONE
            }
        ).addInterceptor(CustomHeaderInterceptor())
        .addInterceptor(AuthInterceptor(
            JwtTokenManager()
        )).build()
}

private fun providerMoshi(): Moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

internal fun createTodoApiService(): TodoApiService {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            provideOkHttpClient()
        )
        .addConverterFactory(
            MoshiConverterFactory.create(
                providerMoshi()
            )
        ).build()
        .create(TodoApiService::class.java)
}