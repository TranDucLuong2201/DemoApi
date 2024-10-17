package com.example.unsplash.okhttp.intercepter

import android.os.Build
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection

class JwtTokenManager {
    suspend fun getTokenFromStorage(): String {
        println("JwtTokenManager.getTokenFromStorage")
        delay(200)
        return "OK"
    }

    suspend fun clearTokenFromStorage() {
        println("JwtTokenManager.clearTokenFromStorage")
        delay(200)
    }
}

class AuthInterceptor(
    private val jwtTokenManager: JwtTokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        println(">>> AuthInterceptor start")

        val request = chain.request()

        val token = runBlocking { jwtTokenManager.getTokenFromStorage() }

        val newRequest = if (token !== null){
            request.newBuilder()
                .addHeader(
                    name = "Authorization",
                    value = "Bearer $token")
                .build()
        } else {
            request
        }
        val response = chain.proceed(newRequest)

        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            runBlocking { jwtTokenManager.clearTokenFromStorage() }
        }

        println(">>> AuthInterceptor end")
        return response
    }
}
