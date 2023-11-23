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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gameachievements.R
import com.example.gameachievements.ui.theme.GameAchievementsTheme
import com.example.gameachievements.viewmodels.AchievementsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(viewModel: AchievementsViewModel? = null, onSubmit: ()->Unit) {
    val eventCode = remember{ mutableStateOf(TextFieldValue()) }
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
        TextField(value = eventCode.value , onValueChange = {eventCode.value = it})
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            onSubmit()
        }, shape = RoundedCornerShape(10.dp), elevation = ButtonDefaults.buttonElevation(5.dp)) {
            Text(stringResource(R.string.submit))
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.NEXUS_6)
@Composable
fun EventScreenPreview() {
    GameAchievementsTheme {
        EventScreen(onSubmit = {

        })
    }
}