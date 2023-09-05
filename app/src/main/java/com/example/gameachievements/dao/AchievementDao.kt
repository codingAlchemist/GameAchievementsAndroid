package com.example.gameachievements.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mtgcommanderachievements.models.Achievement

@Dao
interface AchievementDao {
    @Query("SELECT * from achievement")
    suspend fun getAll(): List<Achievement>

    @Query("SELECT * from achievement where achievement.id == :id")
    fun getAchievementById(id: Int): Achievement

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Achievement::class)
    suspend fun insertAchievment(achievement: Achievement): Long

    @Delete
    fun deleteAchievement(achievement: Achievement)

    @Query("UPDATE achievement SET completed = :completed where achievement.id == :id")
    fun setAchievementCompleted(completed: Boolean, id: Int)
}