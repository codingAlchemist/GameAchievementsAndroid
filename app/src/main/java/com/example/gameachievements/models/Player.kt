package com.example.gameachievements.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Player(@PrimaryKey val id: Int,
                  @ColumnInfo(name = "username") val username: String?,
                  @ColumnInfo(name = "password") val password: String?,
                  @ColumnInfo(name = "desc") val desc: String?,
                  @ColumnInfo(name = "level") val level: Int?,
                  @ColumnInfo(name = "points") val points: Int?,
                  @ColumnInfo(name = "email") val email: String?,
                  @ColumnInfo(name = "gameCode") val gameCode: String?,
                  @ColumnInfo(name = "event_id") val event_id: Int?,
                  @ColumnInfo(name = "isEventApproved") val isEventApproved: Boolean,
                  @ColumnInfo(name = "isLookingForGame") val isLookingForGame: Boolean)