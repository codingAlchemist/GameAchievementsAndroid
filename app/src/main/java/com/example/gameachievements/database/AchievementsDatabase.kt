package com.example.gameachievements.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gameachievements.dao.AchievementDao
import com.example.gameachievements.dao.GameDao
import com.example.gameachievements.dao.PlayerDao
import com.example.gameachievements.dao.PushTokenDao
import com.example.gameachievements.dao.UserDao
import com.example.gameachievements.models.Game
import com.example.gameachievements.models.Player
import com.example.gameachievements.models.PushToken
import com.example.gameachievements.models.User
import com.example.mtgcommanderachievements.models.Achievement

@Database(entities = [Player::class, Achievement::class, PushToken::class, User::class, Game::class], version = 12)
abstract class AchievementsDatabase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun achievementDao(): AchievementDao
    abstract fun pushTokenDao(): PushTokenDao
    abstract fun userDao(): UserDao
    abstract fun gameDao(): GameDao
}