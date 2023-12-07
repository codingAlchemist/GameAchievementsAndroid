package com.example.gameachievements.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
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
import com.example.gameachievements.models.Player
import com.example.gameachievements.ui.theme.GameAchievementsTheme
import com.example.gameachievements.viewmodels.AchievementsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGameView(achievementsViewModel: AchievementsViewModel? = null) {
    val game = achievementsViewModel?._game?.observeAsState()

    AppImage(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        resource = R.drawable.azynoriin_by_ondrejhrdina_d5gor9v
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = game?.value?.gameCode!!,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.white),
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth())
        Button(onClick = {
            val gameRequest = GameRequest(event_id = 0, player_id = achievementsViewModel?._user?.value?.id!!)
            achievementsViewModel.createGame(gameRequest)
        }, shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.buttonElevation(5.dp),
            modifier = Modifier.height(30.dp)) {
            Text(stringResource(R.string.create_game), fontSize = 13.sp)
        }

        playersJoinedView(achievementsViewModel)
    }
}


@Composable
fun playersJoinedView(viewModel: AchievementsViewModel) {
    val joined = viewModel._joinedPlayers.observeAsState()
    Column {
        joined.value?.forEach {
            Spacer(modifier = Modifier.height(5.dp))
            playerRow(player = Player(0,it.username,"","",0,20,"","",0, true, false))
        }
    }
}

@Composable
fun playerRow(player: Player) {
    Row(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .height(30.dp)) {
        Text(text = player.username ?: "", modifier = Modifier
            .fillMaxHeight()
            .wrapContentHeight(align = Alignment.CenterVertically)
            .padding(horizontal = 5.dp), style = TextStyle.Default.copy(fontSize = 12.sp, drawStyle = Stroke(miter = 5f, width = 2f)))

    }
}

@Preview(
    showBackground = true,
    device = Devices.NEXUS_6)
@Composable
fun GameScreenPreview() {
    GameAchievementsTheme {
        CreateGameView()
    }
}