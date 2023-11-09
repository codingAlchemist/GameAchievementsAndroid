package com.example.gameachievements.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gameachievements.models.PushToken

@Dao
interface PushTokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = PushToken::class)
    suspend fun savePushToken(pushToken: PushToken)

    @Query("SELECT * FROM PushToken")
    suspend fun getToken(): List<PushToken>

    @Delete
    suspend fun deleteToken(pushToken: PushToken)


}