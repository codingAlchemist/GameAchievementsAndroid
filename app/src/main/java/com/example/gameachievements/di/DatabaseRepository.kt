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

interface DatabaseRepositoryInterface {
    suspend fun getPlayers(): List<Player>
    suspend fun insertPlayer(player: Player): Long
    suspend fun getAllAchievements() : List<Achievement>
    suspend fun insertAchievement(achievement: Achievement): Long
    suspend fun deleteAchievement(achievement: Achievement)
    suspend fun deleteAllAchievements()
    suspend fun getAchievement(achievement: Achievement)
    suspend fun completeAchievement(achievement: Achievement)
}
class DatabaseRepository @Inject constructor(private val playerDao: PlayerDao,
                                             private val achievementDao: AchievementDao): DatabaseRepositoryInterface {
    override suspend fun getPlayers(): List<Player> {
        return playerDao.getAll()
    }

    override suspend fun insertPlayer(player: Player): Long {
        return playerDao.insertPlayer(player = player)
    }

    override suspend fun getAllAchievements() : List<Achievement> {
        return achievementDao.getAll()
    }

    override suspend fun insertAchievement(achievement: Achievement): Long {
        return achievementDao.insertAchievment(achievement)
    }

    override suspend fun deleteAchievement(achievement: Achievement) {
        achievementDao.deleteAchievement(achievement)
    }

    override suspend fun deleteAllAchievements() {
        achievementDao.deleteAll()
    }

    override suspend fun getAchievement(achievement: Achievement) {
        achievementDao.getAchievementById(achievement.id)
    }

    override suspend fun completeAchievement(achievement: Achievement) {
        achievementDao.setAchievementCompleted(true, achievement.id)
    }
}