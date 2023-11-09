package com.example.gameachievements.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PushToken(
    @PrimaryKey val id: Int,
    @ColumnInfo("fcm") val fcm: String,
    @ColumnInfo("gameCode") val gameCode: String
)