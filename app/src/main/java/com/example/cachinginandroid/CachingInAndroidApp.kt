package com.example.cachinginandroid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class CachingInAndroidApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}