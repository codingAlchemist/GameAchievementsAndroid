package com.example.gameachievements.di

import com.example.gameachievements.dao.AchievementDao
import com.example.gameachievements.dao.PlayerDao
import com.example.gameachievements.models.Player
import com.example.mtgcommanderachievements.models.Achievement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow
import java.io.LineNumberReader
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val playerDao: PlayerDao,
                                             private val achievementDao: AchievementDao) {
    suspend fun getPlayers(): List<Player> {
        return playerDao.getAll()
    }

    suspend fun insertPlayer(player: Player): Long {
        return playerDao.insertPlayer(player = player)
    }

    suspend fun getAllAchievements() : List<Achievement> {
        return achievementDao.getAll()
    }

    suspend fun insertAchievement(achievement: Achievement): Long {
        return achievementDao.insertAchievment(achievement)
    }

    suspend fun deleteAchievement(achievement: Achievement) {
        achievementDao.deleteAchievement(achievement)
    }

    suspend fun deleteAllAchievements() {
        achievementDao.deleteAll()
    }

}