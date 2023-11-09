package com.example.gameachievements.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameachievements.R
import com.example.gameachievements.api.requests.GameRequest
import com.example.gameachievements.ui.theme.GameAchievementsTheme
import com.example.gameachievements.viewmodels.AchievementsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGameScreen(achievementsViewModel: AchievementsViewModel? = null) {

    val game = achievementsViewModel?._game?.collectAsState()

    val gameCode = remember {
        mutableStateOf(game?.value?.gameCode)
    }
    AppImage(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        resource = R.drawable.azynoriin_by_ondrejhrdina_d5gor9v
    )

    Column(modifier =  Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AchievementsAppBar()
        Spacer(modifier = Modifier.height(20.dp))
        Text(stringResource(R.string.event_code), color = colorResource(id = R.color.white))
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            val gameRequest = GameRequest(event_id = 0, player_id = achievementsViewModel?._player?.value?.id!!)
            achievementsViewModel?.createGame(gameRequest)
        }, shape = RoundedCornerShape(10.dp), elevation = ButtonDefaults.buttonElevation(5.dp)) {
            Text(stringResource(R.string.create_game), fontSize = 13.sp)

        }
        Spacer(modifier = Modifier.height(20.dp))
        TextField(modifier = Modifier
            .width(200.dp)
            .height(40.dp),
            onValueChange = {
                            gameCode.value = it
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            value = game?.value?.gameCode!!,
            label = {
                Text(gameCode.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,

                )
            })
    }

}

@Preview(
    showBackground = true,
    device = Devices.NEXUS_6)
@Composable
fun GameScreenPreview() {
    GameAchievementsTheme {
        CreateGameScreen()
    }
}