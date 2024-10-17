package com.example.unsplash

import android.app.Application

class UnsplashApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        UnsplashServiceLocator.initWith(this)
    }
}