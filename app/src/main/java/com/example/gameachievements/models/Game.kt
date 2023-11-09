package com.example.gameachievements.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Game(
    @PrimaryKey val id: Int,
    @ColumnInfo("rounds") val rounds: Int,
    @ColumnInfo("event_id") val event_id: Int,
    @ColumnInfo("eventCode") val eventCode: String,
    @ColumnInfo("timeStarted") val timeStarted: Date,
    @ColumnInfo("timeEnded") val timeEnded: Date,
    @ColumnInfo("datePlayed") val datePlayed: Date,
    @ColumnInfo("gameCode") val gameCode: String)