package com.example.gameachievements.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gameachievements.viewmodels.AchievementsViewModel
import com.example.gameachievements.views.LoginScreen
import com.example.gameachievements.views.SignUpView

@Composable
fun NavGraph(navController: NavHostController, viewModel: AchievementsViewModel? = null) {
    NavHost(
        navController = navController,
        startDestination = "login") {
        composable("login") {
            LoginScreen(viewModel = viewModel, onSignUp = {
                navController.navigate(NavRoute.SignUp.path)
            })
        }
        composable("signup") {
            SignUpView(onLogin = {
                navController.popBackStack()
            }, onSignUp = {})
        }
    }
}
sealed class NavRoute(val path: String) {
    object Login: NavRoute("login")

    object SignUp: NavRoute("signup")

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