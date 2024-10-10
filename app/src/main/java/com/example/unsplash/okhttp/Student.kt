package com.example.unsplash.okhttp


import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
internal data class Student(
    @Json(name = "id") val id: String, // c5e79118-5c4f-4ad5-a554-8a2516ae42d1
    @Json(name = "first_name") val firstName: String, // Araceli Meyer
    @Json(name = "last_name") val lastName: String, // Kreiger
    @Json(name = "age") val age: Int // 21
)