package com.example.gameachievements.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gameachievements.dao.PlayerDao
import com.example.gameachievements.models.Player

@Database(entities = [Player::class], version = 1)
abstract class AchievementsDatabase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}