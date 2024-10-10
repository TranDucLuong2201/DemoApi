package com.example.unsplash.okhttp

import android.content.Context
import androidx.annotation.WorkerThread


@WorkerThread
fun Context.readAssetFile(fileName: String): String =
    assets.open(fileName)
        .bufferedReader()
        .use { it.readText() }