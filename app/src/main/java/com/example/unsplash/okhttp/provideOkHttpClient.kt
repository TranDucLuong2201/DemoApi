package com.example.unsplash.okhttp

import com.example.unsplash.BuildConfig
import com.example.unsplash.okhttp.intercepter.AuthInterceptor
import com.example.unsplash.okhttp.intercepter.CustomHeaderInterceptor
import com.example.unsplash.okhttp.intercepter.JwtTokenManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.util.concurrent.TimeUnit

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15,TimeUnit.SECONDS)
        .writeTimeout(15,TimeUnit.SECONDS)
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