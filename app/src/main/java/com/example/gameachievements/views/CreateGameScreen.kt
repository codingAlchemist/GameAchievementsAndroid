package com.example.gameachievements.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gameachievements.R
import com.example.gameachievements.ui.theme.GameAchievementsTheme

@Composable
fun CreateGameScreen(onSubmit: ()->Unit) {
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
            onSubmit()
        }, shape = RoundedCornerShape(10.dp), elevation = ButtonDefaults.buttonElevation(5.dp)) {
            Text(stringResource(R.string.create_game))
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.NEXUS_6)
@Composable
fun GameScreenPreview() {
    GameAchievementsTheme {
        CreateGameScreen(onSubmit = {

        })
    }
}