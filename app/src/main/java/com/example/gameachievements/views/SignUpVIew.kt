@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gameachievements.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gameachievements.ui.theme.GameAchievementsTheme
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gameachievements.R
import com.example.gameachievements.models.Player
import com.example.gameachievements.viewmodels.AchievementsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpView(viewModel: AchievementsViewModel? = null, onLogin: () -> Unit, onSignUp: () -> Unit) {
    val username = remember{
        mutableStateOf(TextFieldValue())
    }
    val password = remember {
        mutableStateOf(TextFieldValue())
    }
    val desc = remember {
        mutableStateOf(TextFieldValue())
    }
    val email = remember {
        mutableStateOf(TextFieldValue())
    }
    AppImage(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        resource = R.drawable.azynoriin_by_ondrejhrdina_d5gor9v
    )
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        AchievementsAppBar()
        Text(stringResource(R.string.preferred_username))
        TextField(value = username.value, onValueChange = {username.value = it})
        Spacer(modifier = Modifier
            .width(width = 100.dp)
            .height(20.dp))
        Text("Password")
        TextField(value = password.value, onValueChange = {password.value = it})
        Spacer(modifier = Modifier
            .width(width = 100.dp)
            .height(20.dp))
        Text("Email")
        TextField(value = email.value, onValueChange = {email.value = it})
        Spacer(modifier = Modifier
            .width(width = 100.dp)
            .height(20.dp))
        Text("Description")
        TextField(value = desc.value, onValueChange = {desc.value = it})
        Spacer(modifier = Modifier
            .width(width = 100.dp)
            .height(20.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { onLogin() },
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(5.dp)) {
                    Text(stringResource(R.string.login))
            }
            Button(
                onClick = {
                    val player = Player(
                    0,
                    username.value.text,
                    password.value.text,
                    desc.value.text,
                    0,
                    0,
                    email.value.text,
                    null,
                    null,
                    false,
                    false
                )
                    viewModel?.signUpPlayer(player)
                          }, shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(5.dp)) {
                Text(stringResource(id = R.string.sign_up))
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.NEXUS_6)
@Composable
fun SignUpPreview() {
    GameAchievementsTheme {
        SignUpView(onLogin = {}, onSignUp = {})
    }
}