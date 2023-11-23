package com.example.gameachievements.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gameachievements.models.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = User::class)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User")
    suspend fun getUser(): List<User>

    @Delete
    suspend fun deleteUser(user: User)
}