package com.example.unsplash

import androidx.annotation.MainThread
import com.example.unsplash.data.remote.UnsplashApiService
import com.example.unsplash.data.remote.interceptor.AuthorizationInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object UnsplashServiceLocator {
    const val UNSPLASH_API_BASE_URL = "https://api.unsplash.com/"

    private val authorizationInterceptor:AuthorizationInterceptor
        get() = AuthorizationInterceptor(clientId = BuildConfig.UNSPLASH_CLIENT_ID)

    private var _application: UnsplashApplication? = null

    @MainThread
    fun initWith(app: UnsplashApplication) {
        _application = app
    }


    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    private val httpLoggingInterceptor: HttpLoggingInterceptor
        get() = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG){
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(UNSPLASH_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .build()
    }

    val unsplashApiService: UnsplashApiService by lazy { UnsplashApiService(retrofit) }
}