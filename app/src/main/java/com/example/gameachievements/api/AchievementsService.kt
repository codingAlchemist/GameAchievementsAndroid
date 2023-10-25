package com.example.gameachievements.api

import com.example.gameachievements.models.LoginModel
import com.example.mtgcommanderachievements.models.Achievement
import com.example.gameachievements.models.Player
import com.example.mtgcommanderachievements.models.CompleteAchievementRequest
import com.example.mtgcommanderachievements.models.CompletedAchievementResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AchievementsService {

    //region Achievements
    @GET("achievements")
    suspend fun getAllAchievements():Response<List<Achievement>>

    @GET("achievements/{id}")
    suspend fun getAchievementById(@Path("id") id: Int): Response<Achievement>

    @POST("achievements/{id}/complete")
    suspend fun completeAchievement(@Path("id") id: Int, @Body playerRequest: CompleteAchievementRequest): Response<CompletedAchievementResponse>
    //endregion


    @POST("players/login")
    suspend fun loginPlayer(@Body loginModel: LoginModel): Response<Player>

    @POST("players/create")
    suspend fun createPlayer(@Body player: Player): Response<Player>


}