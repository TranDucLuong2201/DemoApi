package com.rxmobileteam.android_006.okhttp


import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
internal data class TodoItemResponse(
    @Json(name = "userId") val userId: Int, // 1
    @Json(name = "id") val id: Int, // 1
    @Json(name = "title") val title: String, // delectus aut autem
    @Json(name = "completed") val completed: Boolean // false
)
