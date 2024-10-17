package com.example.unsplash.data.remote

import com.rxmobileteam.android_006.lec9_unsplash_app.data.remote.response.CollectionItemResponse
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {
    //TODO: add API endpoints here

    @GET("collections")
    suspend fun getCollection(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<CollectionItemResponse>

    companion object{
        operator fun invoke(retrofit: Retrofit): UnsplashApiService = retrofit.create()
    }
}