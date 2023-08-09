package com.example.gameachievements.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gameachievements.models.Player

@Dao
interface PlayerDao {
    @Query("SELECT * from player")
    fun getAll(): List<Player>

    @Insert
    fun insertPlayer(player: Player)

    @Delete
    fun delete(player: Player)
}