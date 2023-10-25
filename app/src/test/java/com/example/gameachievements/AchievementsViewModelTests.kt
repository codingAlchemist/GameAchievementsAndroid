package com.example.gameachievements

import com.example.gameachievements.mocks.MockDatabaseRepository
import com.example.mtgcommanderachievements.models.Achievement
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
class AchievementsViewModelTests {
    val mockDatabaseRepository = MockDatabaseRepository()
    @Test
    fun testCompleteAchivement() {
        val achievement = Achievement(0,"name","desc",0,false,"reward")
        GlobalScope.launch {
            mockDatabaseRepository.getAchievement(achievement = achievement)
            assertEquals(true, mockDatabaseRepository.didCallGetAchievement)
        }
    }
}