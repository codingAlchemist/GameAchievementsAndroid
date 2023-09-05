package com.example.gameachievements.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.gameachievements.AchievementsApplication
import com.example.gameachievements.api.NetworkResult
import com.example.gameachievements.database.AchievementsDatabase
import com.example.gameachievements.di.DatabaseRepository
import com.example.gameachievements.di.NetworkRepository
import com.example.gameachievements.models.LoginModel
import com.example.mtgcommanderachievements.models.Achievement
import com.example.gameachievements.models.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementsViewModel @Inject constructor(private val repository: NetworkRepository, private val databaseRepo: DatabaseRepository): ViewModel() {
    private val player: MutableStateFlow<Player> = MutableStateFlow(Player(
        0,
        "",
        "",
        "",
        0,
        0,
        "",
        0,
        0,
        false,
        false
    ))
    val _player: StateFlow<Player> = player

    private val achievements: MutableStateFlow<List<Achievement>> = MutableStateFlow(
        listOf(Achievement(0, "", "",0,false))
    )
    val _achievements:StateFlow<List<Achievement>> = achievements

    fun loginPlayer(userName: String, password: String) = viewModelScope.launch{
        repository.loginPlayer(LoginModel(username = userName, password = password)).collect { result ->
            viewModelScope.launch {
                result.data.let {
                    val result = databaseRepo.insertPlayer(player = it!!)
                    Log.d("","")
                }
                databaseRepo.getPlayers().let {
                    Log.d("","${it.count()}")
                }

                player.emit(result.data!!)
            }

        }
    }

    fun signUpPlayer(playerIn: Player) = viewModelScope.launch {
        repository.signUpPlayer(playerIn).collect { result ->
            player.emit(result.data!!)
        }
    }
    fun getAllAchievements() = viewModelScope.launch {
        repository.getAchievements().collect { result ->
            result.data?.let {
                achievements.emit(it)
            }
        }
    }
    
    fun saveAllAchievements(achievementsIn: List<Achievement>) = viewModelScope.launch {
        achievementsIn.forEach {
            databaseRepo.insertAchievement(it)
        }
    }
    fun getPlayer(): Player? {
        return player.value
    }
}