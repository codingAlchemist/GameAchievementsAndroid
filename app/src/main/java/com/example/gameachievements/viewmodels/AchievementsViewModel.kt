package com.example.gameachievements.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameachievements.api.NetworkResult
import com.example.gameachievements.di.NetworkRepository
import com.example.gameachievements.models.LoginModel
import com.example.mtgcommanderachievements.models.Achievement
import com.example.gameachievements.models.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementsViewModel @Inject constructor(private val repository: NetworkRepository): ViewModel() {
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
        emptyList()
    )
    val _achievements:StateFlow<List<Achievement>> = achievements

    fun loginPlayer(userName: String, password: String) = viewModelScope.launch{
        repository.loginPlayer(LoginModel(username = userName, password = password)).collect { result ->
            player.emit(result.data!!)
        }
    }

    fun signUpPlayer(playerIn: Player) = viewModelScope.launch {
        repository.signUpPlayer(playerIn).collect { result ->
            player.emit(result.data!!)
        }
    }
    fun getAllAchievements() = viewModelScope.launch {
        repository.getAchievements().collect {
            achievements.emit(it.data!!)
        }
    }

    fun getPlayer(): Player? {
        return player.value
    }
}