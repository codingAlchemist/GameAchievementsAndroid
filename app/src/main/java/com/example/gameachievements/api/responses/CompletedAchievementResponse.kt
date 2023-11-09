package com.example.gameachievements.api.responses

data class CompletedAchievementResponse(val id: Int,
                                        val playerId: Int,
                                        val achievementId: Int,
                                        val completed: Boolean)