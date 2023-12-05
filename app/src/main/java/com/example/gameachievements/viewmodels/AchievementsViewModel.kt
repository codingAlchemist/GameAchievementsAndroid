package com.example.gameachievements.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameachievements.api.requests.CompleteAchievementRequest
import com.example.gameachievements.api.requests.GameRequest
import com.example.gameachievements.api.requests.JoinGameRequest
import com.example.gameachievements.di.DatabaseRepository
import com.example.gameachievements.di.NetworkRepository
import com.example.gameachievements.models.Game
import com.example.gameachievements.models.LoginModel
import com.example.mtgcommanderachievements.models.Achievement
import com.example.gameachievements.models.Player
import com.example.gameachievements.models.PushToken
import com.example.gameachievements.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
interface AchievementsViewModelInterface {
    fun loginPlayer(userName: String, password: String)
    fun signUpPlayer(playerIn: Player)
    fun getAllAchievementsFromNetwork()
    fun saveAllAchievements(achievementsIn: List<Achievement>)
    fun getAllAchievements()
    fun refreshAchievements()
    fun completeAchievement(achievement: Achievement): Job
    fun createGame(gameRequest: GameRequest)
    fun saveFCM(pushToken: PushToken)
    suspend fun getCurrentPushToken(): PushToken
    suspend fun joinGame(gameCode: String)
}
@HiltViewModel
class AchievementsViewModel @Inject constructor(private val repository: NetworkRepository, private val databaseRepo: DatabaseRepository): ViewModel(),AchievementsViewModelInterface {
    var gamePlayers = mutableStateListOf<Player>()
    private val user: MutableStateFlow<User> = MutableStateFlow(User(
        0,
        "",
        "",
        "",
        "",
        level = "0"
    ))
    val _user: StateFlow<User> = user

    private val game: MutableStateFlow<Game> = MutableStateFlow(Game(
        0,
        0,
        0,
        "",
        "",
        "",
        "",
        "" ))
    val _game:StateFlow<Game> = game

    private val achievements: MutableStateFlow<List<Achievement>> = MutableStateFlow(
        listOf(Achievement(0, "", "",0,false, reward = ""))
    )
    val _achievements:StateFlow<List<Achievement>> = achievements

    val joinedPlayers: MutableLiveData<SnapshotStateList<Player>> = MutableLiveData()
    val _joinedPlayers: LiveData<SnapshotStateList<Player>> = joinedPlayers

    val pushToken: MutableStateFlow<PushToken> = MutableStateFlow(PushToken(0,"",""))
    val _pushToken: StateFlow<PushToken> = pushToken

    val gameJoinMessage: MutableStateFlow<String> = MutableStateFlow("")
    val _gameJoinMessage: MutableStateFlow<String> = gameJoinMessage

    fun addPlayerToGame(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            gamePlayers.add(player)
            joinedPlayers.postValue(gamePlayers)
        }
    }
    override fun loginPlayer(userName: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepo.getPlayers().let {
                it.forEach() {
                    databaseRepo.delete(it)
                }
            }
            repository.loginPlayer(LoginModel(username = userName, password = password)).collect { result ->
                viewModelScope.launch {
                    result.data.let {playerData ->
                        playerData?.let {
                            val loggedInUser = User(it.id, it?.username ?: "", it.password ?: "", it.desc ?: "", it.email ?: "", "${it.level!!}")
                            databaseRepo.saveUser(loggedInUser)
                            databaseRepo.getUser().let { value ->
                                user.emit(value)
                            }
                        }

                    }
                }
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            databaseRepo.removeUser()
        }
    }
    override fun signUpPlayer(playerIn: Player)  {
        viewModelScope.launch {
            repository.signUpPlayer(playerIn).collect { result ->
                val newUser = User(result?.data?.id!!, result?.data?.username!!, result?.data?.password!!, result?.data?.desc!!, result?.data?.email!!, "${result?.data?.level!!}")
                databaseRepo.saveUser(newUser)
                user.emit(newUser)
            }
        }
    }

    suspend fun getLoggedInUser(): User? = withContext(Dispatchers.IO) {
        var currentUser: User? = null
        databaseRepo.getUser().let {
            user.value = it
            currentUser = it
        }
        return@withContext user.value
    }
    override fun getAllAchievementsFromNetwork() {
        viewModelScope.launch {
            repository.getAchievements().collect { result ->
                result.data?.let {
                    achievements.emit(it)
                }
            }
        }
    }

    override fun saveAllAchievements(achievementsIn: List<Achievement>) {
        viewModelScope.launch {
            achievementsIn.forEach {
                databaseRepo.insertAchievement(it)
            }
        }
    }

    override fun getAllAchievements()  {
        viewModelScope.launch {
            if (databaseRepo.getAllAchievements().isNotEmpty()) {
                achievements.emit(databaseRepo.getAllAchievements())
            } else {
                getAllAchievementsFromNetwork()
            }
        }
    }

    override fun refreshAchievements()  {
        viewModelScope.launch {
            if (databaseRepo.getAllAchievements().isNotEmpty()) {
                databaseRepo.deleteAllAchievements()
                getAllAchievementsFromNetwork()
                if (achievements.value.isNotEmpty()) {
                    saveAllAchievements(achievementsIn = achievements.value)
                }
            }
        }

    }

    override fun completeAchievement(achievement: Achievement):Job = viewModelScope.launch {
        viewModelScope.launch(Dispatchers.IO) {
            async {
                databaseRepo.completeAchievement(achievement = achievement)
                databaseRepo.getPlayers().let {
                    if (it.isNotEmpty()){
                        val playerAchievementRequest = CompleteAchievementRequest(player_id = it.first().id)
                        repository.completeAchievement(achievement = achievement, playerRequest = playerAchievementRequest).collect { result ->
                            Log.d("TAG", "completeAchievement: ")
                        }
                    }
                }
            }.await()
        }
    }

    override fun createGame(gameRequest: GameRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            async {
                repository.createGame(gameRequest).collect {result ->
                    Log.d("", "")
                    result.data?.let {
                        game.emit(it)
                    }
                }
            }.await()
        }
    }

    override suspend fun joinGame(gameCode: String) {
        val user = databaseRepo.getUser()
        val game = databaseRepo.getGameWithGameCode(gameCode)
        if (game.gameCode != null && user.id != null) {
            val joinGameRequest = JoinGameRequest(user.id, game.gameCode,user.username, user.level)
            repository.joinGame(joinGameRequest).collect {
                it.message?.let {
                    gameJoinMessage.emit(it)
                }
            }

        }
    }
    override suspend fun getCurrentPushToken(): PushToken = withContext(Dispatchers.IO) {
        databaseRepo.getPushToken().let {
            pushToken.value = it
        }
        return@withContext pushToken.value

    }
    override fun saveFCM(pushTokenIn: PushToken) {
        viewModelScope.launch(Dispatchers.IO) {
            async {
                repository.sendFCMToken(pushToken = pushTokenIn).collect{ result ->
                    result.data?.let {
                        Log.d("","${it.fcm}")
                        databaseRepo.savePushToken(pushToken = pushTokenIn)
                    }
                }
            }
        }
    }


}
object Players {
    val playersJoined: MutableLiveData<ArrayList<Player>> by lazy {
        MutableLiveData<ArrayList<Player>>()
    }
}