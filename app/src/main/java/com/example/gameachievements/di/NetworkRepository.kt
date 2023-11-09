package com.example.gameachievements.di

import com.example.gameachievements.api.AchievementsService
import com.example.gameachievements.api.BaseApiResponse
import com.example.gameachievements.api.NetworkResult
import com.example.gameachievements.api.requests.CompleteAchievementRequest
import com.example.gameachievements.api.requests.GameRequest
import com.example.gameachievements.api.responses.CompletedAchievementResponse
import com.example.gameachievements.models.Game
import com.example.gameachievements.models.LoginModel
import com.example.mtgcommanderachievements.models.Achievement
import com.example.gameachievements.models.Player
import com.example.gameachievements.models.PushToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface NetworkRepositoryInterface {
    suspend fun getAchievements(): Flow<NetworkResult<List<Achievement>>>
    suspend fun getAchievementById(id: Int): Flow<NetworkResult<Achievement>>
    suspend fun completeAchievement(achievement: Achievement, playerRequest: CompleteAchievementRequest): Flow<NetworkResult<CompletedAchievementResponse>>
    suspend fun loginPlayer(loginModel: LoginModel): Flow<NetworkResult<Player>>
    suspend fun signUpPlayer(player: Player): Flow<NetworkResult<Player>>
    suspend fun createGame(gameRequest: GameRequest): Flow<NetworkResult<Game>>
    suspend fun sendFCMToken(pushToken: PushToken): Flow<NetworkResult<PushToken>>
}
class NetworkRepository @Inject constructor(private val achievementsService: AchievementsService):
    BaseApiResponse(), NetworkRepositoryInterface {
    override suspend fun getAchievements(): Flow<NetworkResult<List<Achievement>>> {
        return  flow {
            emit(safeApiCall { achievementsService.getAllAchievements() })
        }.flowOn(Dispatchers.IO)
    }
    override suspend fun getAchievementById(id: Int): Flow<NetworkResult<Achievement>> {
        return flow {
            emit( safeApiCall { achievementsService.getAchievementById(id)})
        }.flowOn(Dispatchers.IO)
    }
    override suspend fun completeAchievement(achievement: Achievement, playerRequest: CompleteAchievementRequest): Flow<NetworkResult<CompletedAchievementResponse>> {
        return flow {
            emit( safeApiCall { achievementsService.completeAchievement(achievement.id, playerRequest) })
        }.flowOn(Dispatchers.IO)
    }
    override suspend fun loginPlayer(loginModel: LoginModel): Flow<NetworkResult<Player>> {
        return flow {
            emit( safeApiCall { achievementsService.loginPlayer(loginModel) })
        }.flowOn(Dispatchers.IO)
    }
    override suspend fun signUpPlayer(player: Player): Flow<NetworkResult<Player>> {
        return flow {
            emit( safeApiCall { achievementsService.createPlayer(player = player) })
        }
    }

    override suspend fun createGame(gameRequest: GameRequest): Flow<NetworkResult<Game>> {
        return flow{
            emit( safeApiCall { achievementsService.createGame(gameRequest) })
        }
    }

    override suspend fun sendFCMToken(pushToken: PushToken): Flow<NetworkResult<PushToken>> {
        return flow {
            emit( safeApiCall { achievementsService.sendFCMToken(pushToken = pushToken) })
        }
    }
}