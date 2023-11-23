package com.example.gameachievements.views

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.gameachievements.R

class AchievementsAppBarMain: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsAppBar(){
    TopAppBar(title = { Text(text = stringResource(id = R.string.app_title)) },
        navigationIcon = {
            IconButton(onClick = {  }) {

            }
        })
}

