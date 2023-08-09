package com.example.gameachievements.api

import com.example.gameachievements.models.LoginModel
import com.example.mtgcommanderachievements.models.Achievement
import com.example.gameachievements.models.Player
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AchievementsService {

    @GET("achievement")
    suspend fun getAllAchievements():Response<List<Achievement>>

    @POST("players/login")
    suspend fun loginPlayer(@Body loginModel: LoginModel): Response<Player>

    @POST("players/create")
    suspend fun createPlayer(@Body player: Player): Response<Player>
}