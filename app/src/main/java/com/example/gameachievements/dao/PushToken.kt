package com.example.gameachievements.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.gameachievements.models.PushToken

@Dao
interface PushToken {
    @Query("SELECT * FROM PushToken")
    fun getToken(): PushToken

    @Delete
    fun deleteToken(pushToken: PushToken)
}