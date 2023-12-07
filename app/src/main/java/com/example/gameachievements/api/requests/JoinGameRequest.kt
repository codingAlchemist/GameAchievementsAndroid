package com.example.gameachievements.api.requests

data class JoinGameRequest(val id: Int,
                           val gameCode: String,
                           val username: String,
                           val level: String) {
}