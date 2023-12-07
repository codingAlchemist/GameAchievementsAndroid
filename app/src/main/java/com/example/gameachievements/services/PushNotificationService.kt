package com.example.gameachievements.services

import androidx.lifecycle.MutableLiveData
import com.example.gameachievements.di.DatabaseRepository
import com.example.gameachievements.models.Player
import com.example.gameachievements.viewmodels.AchievementsViewModel
import com.example.gameachievements.viewmodels.Players
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import javax.inject.Inject



class PushNotificationService: FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Players.playersJoined.postValue(arrayListOf(Player(0,message.data.get("username"),"","", message.data.get("level")!!.toInt(), 0,"",message.data.get("gameCode"),0,true,false)))
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}
