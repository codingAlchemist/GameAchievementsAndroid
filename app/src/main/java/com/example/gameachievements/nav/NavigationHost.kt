package com.example.gameachievements.nav

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gameachievements.models.Player
import com.example.gameachievements.viewmodels.AchievementsViewModel
import com.example.gameachievements.views.CreateGameScreen
import com.example.gameachievements.views.EventScreen
import com.example.gameachievements.views.GameView
import com.example.gameachievements.views.LoginScreen
import com.example.gameachievements.views.MainTabView
import com.example.gameachievements.views.SignUpView
import com.example.mtgcommanderachievements.models.Achievement
import com.google.accompanist.pager.ExperimentalPagerApi
import androidx.compose.runtime.setValue // only if using var
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavGraph(navController: NavHostController, viewModel: AchievementsViewModel? = null) {
    val achievements: List<Achievement> by viewModel!!._achievements.collectAsState()
    var startDestination by remember { mutableStateOf(NavRoute.Login.path ) }
    LaunchedEffect(key1 = "login"){
        if (viewModel!!.getLoggedInPlayer().id > 0) {
            startDestination = NavRoute.MainTabView.path
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination) {
        composable("login", enterTransition = {
            fadeIn(animationSpec = tween(2000))
        }, exitTransition = {
            when(initialState.destination.route) {
                NavRoute.Event.path -> slideOutVertically(animationSpec = tween(100))
                else -> fadeOut (animationSpec = tween(2000))
            }
        }) {
            LoginScreen(viewModel = viewModel,
                onSignUp = {
                    navController.navigate(NavRoute.SignUp.path)
            }, onLogin = {
                    GlobalScope.launch(Dispatchers.Main) {
                        if (viewModel!!.getLoggedInPlayer() != null) {
                            navController.navigate(NavRoute.MainTabView.path)
                            viewModel.getAllAchievementsFromNetwork()
                        }
                    }

                    if (!achievements.isNullOrEmpty()) {
                        viewModel?.saveAllAchievements(achievements)
                    }
                })
        }
        composable("signup") {
            SignUpView(onLogin = {
                navController.popBackStack()

            }, onSignUp = {})
        }
        composable("event") {
            EventScreen {

            }
        }
        composable("maintabview"){
            MainTabView(navHostController = navController, achievementsViewModel = viewModel)
        }
        composable("gameView") {
            GameView(viewModel = viewModel)
        }
        composable("createGameScreen") {
            CreateGameScreen()
        }
    }
}
sealed class NavRoute(val path: String) {
    object Login: NavRoute("login")

    object SignUp: NavRoute("signup")

    object Event: NavRoute("event")

    object CreateGameScreen: NavRoute("createGameScreen")

    object  GameView: NavRoute("gameView")
    object MainTabView: NavRoute("maintabview")
    fun withArgs(vararg args: String): String {
        return  buildString {
            append(path)
            args.forEach {arg ->
                append("/$arg")
            }
        }
    }

    fun withArgsFormat(vararg args: String) : String {
        return buildString {
            append(path)
            args.forEach{ arg ->
                append("/{$arg}")
            }
        }
    }
}