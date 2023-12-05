package com.example.gameachievements.di

import androidx.room.util.query
import com.example.gameachievements.dao.AchievementDao
import com.example.gameachievements.dao.GameDao
import com.example.gameachievements.dao.PlayerDao
import com.example.gameachievements.dao.PushTokenDao
import com.example.gameachievements.dao.UserDao
import com.example.gameachievements.models.Game
import com.example.gameachievements.models.Player
import com.example.gameachievements.models.PushToken
import com.example.gameachievements.models.User
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
    suspend fun deleteAllPlayers()
    suspend fun getAllAchievements(): List<Achievement>
    suspend fun insertAchievement(achievement: Achievement): Long
    suspend fun deleteAchievement(achievement: Achievement)
    suspend fun delete(player: Player)
    suspend fun deleteAllAchievements()
    suspend fun getAchievement(achievement: Achievement)
    suspend fun completeAchievement(achievement: Achievement)
    suspend fun savePushToken(pushToken: PushToken)
    suspend fun getPushToken(): PushToken
    suspend fun getUser(): User
    suspend fun saveUser(user: User)
    suspend fun removeUser()
    suspend fun getGameWithGameCode(gameCode: String): Game
}

class DatabaseRepository @Inject constructor(
    private val playerDao: PlayerDao,
    private val achievementDao: AchievementDao,
    private val pushTokenDao: PushTokenDao,
    private val userDao: UserDao,
    private val gameDao: GameDao

) : DatabaseRepositoryInterface {

    // region User
    override suspend fun getUser(): User {
        return userDao.getUser().last()
    }

    override suspend fun saveUser(user: User) {
        return userDao.insertUser(user = user)
    }

    override suspend fun removeUser() {
       return userDao.deleteUser()
    }

    //endregion

    //region Player
    override suspend fun getPlayers(): List<Player> {
        return playerDao.getAll()
    }

    override suspend fun insertPlayer(player: Player): Long {
        return playerDao.insertPlayer(player = player)
    }

    override suspend fun delete(player: Player) {
        return playerDao.delete(player = player)
    }

    override suspend fun deleteAllPlayers() {
        return playerDao.deleteAllPlayers()
    }


    //endregion

    //region achievements
    override suspend fun getAllAchievements(): List<Achievement> {
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
    //endregion

    //region push_token
    override suspend fun savePushToken(pushToken: PushToken) {
        return pushTokenDao.savePushToken(pushToken = pushToken)
    }

    override suspend fun getPushToken(): PushToken {
        return pushTokenDao.getToken().last()
    }
    //endregion

    // Game
    override suspend fun getGameWithGameCode(gameCode: String): Game {
        return gameDao.getGame(gameCode)
    }
    // endregion
}