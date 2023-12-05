package com.example.gameachievements.di

import android.content.Context
import androidx.room.Room
import com.example.gameachievements.dao.AchievementDao
import com.example.gameachievements.dao.GameDao
import com.example.gameachievements.dao.PlayerDao
import com.example.gameachievements.dao.PushTokenDao
import com.example.gameachievements.dao.UserDao
import com.example.gameachievements.database.AchievementsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext applicationContext: Context) : AchievementsDatabase {
        return Room.databaseBuilder(applicationContext, AchievementsDatabase::class.java, "achievements")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providePlayerDao(achievementsDatabase: AchievementsDatabase): PlayerDao {
        return achievementsDatabase.playerDao()
    }

    @Provides
    fun providesAchievementDao(achievementsDatabase: AchievementsDatabase): AchievementDao {
        return achievementsDatabase.achievementDao()
    }

    @Provides
    fun providesPushTokenDao(achievementsDatabase: AchievementsDatabase): PushTokenDao {
        return achievementsDatabase.pushTokenDao()
    }

    @Provides
    fun providesUserDao(achievementsDatabase: AchievementsDatabase): UserDao {
        return achievementsDatabase.userDao()
    }

    @Provides
    fun providesGameDao(achievementsDatabase: AchievementsDatabase): GameDao {
        return achievementsDatabase.gameDao()
    }
}