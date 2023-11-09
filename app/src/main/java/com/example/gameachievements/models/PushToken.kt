package com.example.gameachievements.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class PushToken(@ColumnInfo("token") val token: String,
                     @ColumnInfo("gameCode") val gameCode: String)