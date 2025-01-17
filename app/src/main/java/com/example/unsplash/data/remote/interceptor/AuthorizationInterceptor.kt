package com.example.unsplash.data.remote.interceptor

import com.example.unsplash.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(
    private val clientId: String
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.request()
        .newBuilder()
        .addHeader("Authorization","Client-ID $clientId")
        .build()
        .let { chain.proceed(it) }

}