package com.example.gameachievements.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gameachievements.models.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM Game")
    fun getGames(): List<Game>

    @Query("SELECT * FROM Game WHERE Game.gameCode == :gameCode")
    fun getGame(gameCode: String):Game

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Game::class)
    fun insertGame(game: Game)
}