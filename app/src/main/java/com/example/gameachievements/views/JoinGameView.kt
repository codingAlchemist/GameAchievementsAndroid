@file:JvmName("JoinGameViewKt")

package com.example.gameachievements.views

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameachievements.R
import com.example.gameachievements.viewmodels.AchievementsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinGameView(achievementsViewModel: AchievementsViewModel) {
    val context = LocalContext.current
    val gameCode = remember {
        mutableStateOf(TextFieldValue())
    }
    val gameJoinMessage = achievementsViewModel?.gameJoinMessage?.collectAsState()
    AppImage(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        resource = R.drawable.azynoriin_by_ondrejhrdina_d5gor9v
    )
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
            onValueChange = {
                gameCode.value = it
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            value = gameCode.value,
            singleLine = true)
        Button(onClick = {
            GlobalScope.launch(Dispatchers.IO) {
                gameCode.value?.let {
                    achievementsViewModel?.joinGame(it.text)
                }
                if (gameJoinMessage?.value?.isNotEmpty()!!) {
                    Toast.makeText(context, "You joined game", Toast.LENGTH_LONG).show()
                }
            }
        }, shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.buttonElevation(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)) {
            Text(stringResource(R.string.join_game), fontSize = 13.sp)
        }
    }
}