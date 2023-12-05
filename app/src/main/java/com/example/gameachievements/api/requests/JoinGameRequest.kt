package com.example.gameachievements.api.requests

data class JoinGameRequest(val id: Int,
                           val game_code: String,
                           val username: String,
                           val level: String) {
}