package com.example.unsplash.okhttp.intercepter

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

class CustomHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        println(">>> CustomHeaderInterceptor start")

        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader(
                name = "Custom-Header",
                value = "Android/${Build.VERSION.SDK_INT} ${Build.MODEL} ${Build.DEVICE}"
            ).build()
        val response = chain.proceed(newRequest)
        println(">>> CustomHeaderInterceptor end")
        return response
    }
}
