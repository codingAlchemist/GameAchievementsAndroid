package com.example.gameachievements.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gameachievements.models.Player

@Dao
interface PlayerDao {
    @Query("SELECT * from player")
    suspend fun getAll(): List<Player>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Player::class)
    suspend fun insertPlayer(player: Player): Long

    @Delete
    fun delete(player: Player)

    @Query("SELECT * from player WHERE player.username = :username")
    fun getPlayer(username: String) : Player

}