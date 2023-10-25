package com.example.gameachievements.views

import android.annotation.SuppressLint
import android.widget.ScrollView
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameachievements.ui.theme.GameAchievementsTheme
import com.example.gameachievements.viewmodels.AchievementsViewModel
import com.example.mtgcommanderachievements.models.Achievement

enum class CardFace(val angle: Float) {
    Front(0f) {
        override val next: CardFace
            get() = Back
    },
    Back(180f) {
        override val next: CardFace
            get() = Front
    };

    abstract val next: CardFace
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GameView(viewModel: AchievementsViewModel? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.Cyan),
    ) {
        AchievementsAppBar()

        viewModel?._achievements?.value?.let {
            for (i in 0..2){
                GameCardView(achievement = it.get(i), viewModel = viewModel)
            }
        }

        
    }
}

@Composable
fun GameCardView(achievement: Achievement, viewModel: AchievementsViewModel? = null) {
    var cardFace by remember {
        mutableStateOf(CardFace.Front)
    }
    val rotation = animateFloatAsState(
        targetValue = cardFace.angle,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        ), label = "")
    Box(modifier = Modifier
        .graphicsLayer {
            rotationY = rotation.value
            cameraDistance = 12f * density
        }
        .fillMaxSize()){
        if (rotation.value <= 90f){
            AchievementCardView(cardFace = cardFace, achievement = achievement, onClick = {
                cardFace = it.next
            })
        } else {
            RewardCardView(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(height = 300.dp)
                .graphicsLayer {
                    rotationY = 180f
                }, achievement,
                onClick = {
                    viewModel?.let {
                        it.completeAchievement(achievement = achievement)
                    }
                }
            )
        }
    }

}
@Composable
fun AchievementCardView(cardFace: CardFace,
                        achievement: Achievement,
                        onClick: (CardFace) -> Unit) {
    val viewPadding = 5.dp
    val textSize = 18.sp
    Spacer(modifier = Modifier.height(10.dp))
    OutlinedCard(
        colors = CardDefaults.cardColors(
        containerColor = Color.White
    ), border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(viewPadding)
            .height(height = 300.dp)) {
        Column {
            Text(
                text = "Points: ${achievement.points}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(viewPadding),
                fontSize = textSize,
                textAlign = TextAlign.End
            )
            Text(
                text = "Name",
                modifier = Modifier.padding(viewPadding),
                fontWeight = FontWeight.Bold,
                fontSize = textSize,
            )
            Text(
                text = achievement.name,
                modifier = Modifier.padding(viewPadding),
                fontSize = textSize,
            )
            Text(
                text = "Description",
                modifier = Modifier.padding(viewPadding),
                fontWeight = FontWeight.Bold,
                fontSize = textSize,
            )
            Text(
                text = achievement.desc,
                modifier = Modifier.padding(viewPadding),
                fontSize = textSize,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
                horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                    onClick(cardFace)
                }) {
                    Text(text = "Complete")
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun RewardCardView(modifier: Modifier, achievement: Achievement, onClick: () -> Unit) {
    val viewPadding = 5.dp
    OutlinedCard(
        colors = CardDefaults.cardColors(
        containerColor = Color.White
    ), border = BorderStroke(1.dp, Color.Black),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!achievement.reward.isNullOrEmpty()){
                Text(text = achievement.reward, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                onClick()
            }) {
                Text("Use Reward")
            }
        }

    }
}
@Preview(
    showBackground = true,
    device = Devices.NEXUS_6)
@Composable
fun GameViewPreview() {
    GameAchievementsTheme {
        GameView()
    }
}