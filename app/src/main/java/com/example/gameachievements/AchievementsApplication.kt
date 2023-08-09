package com.example.gameachievements

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AchievementsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}