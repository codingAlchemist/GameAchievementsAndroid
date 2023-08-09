package com.example.gameachievements.views

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asAndroidColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.gameachievements.R
import com.example.gameachievements.models.Player
import com.example.gameachievements.ui.theme.GameAchievementsTheme
import com.example.gameachievements.viewmodels.AchievementsViewModel
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: AchievementsViewModel? = null, onSignUp: () -> Unit){

    val username = remember{ mutableStateOf(TextFieldValue()) }
    val password = remember {
        mutableStateOf(TextFieldValue())
    }
   val player:Player by viewModel!!._player.collectAsState()
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
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = player?.username!!, color = Color.White)
        Text(stringResource(R.string.username), color = colorResource(id = R.color.white))
        TextField(value = username.value, onValueChange = {username.value = it})
        Spacer(modifier = Modifier
            .width(width = 100.dp)
            .height(20.dp))
        Text(stringResource(R.string.password),color = colorResource(id = R.color.white))
        TextField(value = password.value, onValueChange = {password.value = it})
        Spacer(modifier = Modifier
            .width(width = 100.dp)
            .height(20.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                             viewModel!!.loginPlayer(userName = username.value.text, password = password.value.text)

            }, shape = RoundedCornerShape(10.dp), elevation = ButtonDefaults.buttonElevation(5.dp)) {
                Text("Login")
            }
            Button(onClick = { onSignUp() }, shape = RoundedCornerShape(10.dp), elevation = ButtonDefaults.buttonElevation(5.dp)) {
                Text("Sign Up")
            }
        }

    }

}


@Preview(
    showBackground = true,
    device = Devices.NEXUS_6)
@Composable
fun LoginPreview() {
    GameAchievementsTheme {
        LoginScreen(onSignUp = {

        })
    }
}

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    @DrawableRes resource: Int,
    colorFilter: ColorFilter? = null
){
    AndroidView(modifier = modifier,
    factory = { context ->
        ImageView(context).apply {
            setImageResource(resource)
            setColorFilter(colorFilter?.asAndroidColorFilter())
        }

    },
        update = {
            it.setImageResource(resource)
            it.colorFilter = colorFilter?.asAndroidColorFilter()
        }
    )
}