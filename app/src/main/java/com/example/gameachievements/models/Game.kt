package com.example.gameachievements.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Game(
    @PrimaryKey val id: Int,
    @ColumnInfo("rounds") val rounds: Int?,
    @ColumnInfo("eventCode") val eventCode: String?,
    @ColumnInfo("timeStarted") val timeStarted: String?,
    @ColumnInfo("timeEnded") val timeEnded: String?,
    @ColumnInfo("datePlayed") val datePlayed: String?,
    @ColumnInfo("gameCode") val gameCode: String?)