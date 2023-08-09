package com.example.gameachievements.di

import com.example.gameachievements.api.AchievementsService
import com.example.gameachievements.api.BaseApiResponse
import com.example.gameachievements.api.NetworkResult
import com.example.gameachievements.models.LoginModel
import com.example.mtgcommanderachievements.models.Achievement
import com.example.gameachievements.models.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val achievementsService: AchievementsService):
    BaseApiResponse() {

    suspend fun getAchievements(): Flow<NetworkResult<List<Achievement>>> {
        return  flow {
            emit(safeApiCall { achievementsService.getAllAchievements() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun loginPlayer(loginModel: LoginModel): Flow<NetworkResult<Player>> {
        return flow {
            emit( safeApiCall { achievementsService.loginPlayer(loginModel) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun signUpPlayer(player: Player): Flow<NetworkResult<Player>> {
        return flow {
            emit( safeApiCall { achievementsService.createPlayer(player = Player(
                0,
                username = player.username,
                password = player.password,
                desc = player.desc,
                level = 0,
                points = 0,
                email = player.email,
                game_id = null,
                event_id = null,
                isEventApproved = false,
                isLookingForGame = false
            ))
            })
        }
    }
}