package com.example.gameachievements.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gameachievements.dao.AchievementDao
import com.example.gameachievements.dao.PlayerDao
import com.example.gameachievements.models.Player
import com.example.mtgcommanderachievements.models.Achievement

@Database(entities = [Player::class, Achievement::class], version = 4)
abstract class AchievementsDatabase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun achievementDao(): AchievementDao
}