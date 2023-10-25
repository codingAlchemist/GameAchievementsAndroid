package com.example.gameachievements.mocks

import com.example.gameachievements.di.DatabaseRepositoryInterface
import com.example.gameachievements.models.Player
import com.example.mtgcommanderachievements.models.Achievement

class MockDatabaseRepository: DatabaseRepositoryInterface {
    var didCallGetPlayers: Boolean = false
    var didCallInsertPlayer: Boolean = false
    var didCallGetAllAchievements: Boolean = false
    var didCallInsertAchievement: Boolean = false
    var didCallDeleteAchievement: Boolean = false
    var didCallDeleteAllAchievements: Boolean = false
    var didCallGetAchievement: Boolean = false
    var didCallComppleteAchievement: Boolean = false


    var players: List<Player> = listOf()
    var achievements: List<Achievement> = listOf()

    override suspend fun getPlayers(): List<Player> {
        didCallGetPlayers = true
        players = listOf(Player(0,"username","password","desc",0,0,"email",0,0,false,false))
        return players
    }

    override suspend fun insertPlayer(player: Player): Long {
        didCallInsertPlayer = true
        return 1
    }

    override suspend fun getAllAchievements(): List<Achievement> {
        didCallGetAllAchievements = true
        achievements = listOf(Achievement(0,"name","desc",0,false,"reward"))
        return achievements
    }

    override suspend fun insertAchievement(achievement: Achievement): Long {
        didCallInsertAchievement =  true
        return 1
    }

    override suspend fun deleteAchievement(achievement: Achievement) {
        didCallDeleteAchievement = true
    }

    override suspend fun deleteAllAchievements() {
        didCallDeleteAllAchievements = true
    }

    override suspend fun getAchievement(achievement: Achievement) {
        didCallGetAchievement = true
    }

    override suspend fun completeAchievement(achievement: Achievement) {
        didCallComppleteAchievement = true
    }

}