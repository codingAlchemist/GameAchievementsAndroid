package com.example.gameachievements.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gameachievements.R
import com.example.gameachievements.nav.NavRoute
import com.example.gameachievements.ui.theme.GameAchievementsTheme
import com.example.gameachievements.viewmodels.AchievementsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

// on below line we are creating a
// composable function for our tab layout
@OptIn(ExperimentalUnitApi::class)
@ExperimentalPagerApi
@Composable
fun MainTabView(
    navHostController: NavHostController? = null,
    achievementsViewModel: AchievementsViewModel? = null
) {

    // on below line we are creating variable for pager state.
    val pagerState = rememberPagerState(pageCount = 3)
    // on below line we are creating a column for our widgets.
    Column(
        // for column we are specifying modifier on below line.
        modifier = Modifier.background(Color.White)
    ) {
        // on the below line we are specifying the top app bar
        // and specifying background color for it.
        TopAppBar(backgroundColor = Color.Blue) {
            // on below line we are specifying a column
            // for our text view to display a text
            // in our top app bar.
            Row(
                modifier = Modifier.fillMaxSize()
                // on below line we are providing alignment for our
                // column to center of our top app bar.
            ) {
                // on below line we are specifying a text and
                // specifying a text as "Tab Layout Example"
                Text(
                    text = "Game Achievements",
                    style = TextStyle(color = Color.White),
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(
                        18F,
                        TextUnitType.Sp
                    ),
                    // on below line we are specifying a modifier
                    // to our text and adding passing from all sides.
                    modifier = Modifier
                        .padding(all = Dp(5F))
                        .weight(.6f),
                    // on below line we are aligning
                    // our text to center.
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(.1f))
                Button(onClick = {
                    achievementsViewModel?.logOut()
                    navHostController?.navigate(NavRoute.Login.path)
                }, modifier = Modifier.weight(.3f)) {
                    Text(text = "Logout")

                }
            }
        }
        // on below line we are calling tabs
        Tabs(pagerState = pagerState)
        // on below line we are calling tabs content
        // for displaying our page for each tab layout
        TabsContent(pagerState = pagerState, achievementsViewModel!!)
    }
}

// on below line we are
// creating a function for tabs
@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    // in this function we are creating a list
    // in this list we are specifying data as
    // name of the tab and the icon for it.
    val list = listOf(
        "Games" to Icons.Default.Home,
        "Events" to ImageVector.vectorResource(id = R.drawable.baseline_event_24),
        "Profile" to Icons.Default.Settings
    )
    // on below line we are creating
    // a variable for the scope.
    val scope = rememberCoroutineScope()
    // on below line we are creating a
    // individual row for our tab layout.
    TabRow(
        // on below line we are specifying
        // the selected index.
        selectedTabIndex = pagerState.currentPage,

        // on below line we are
        // specifying background color.
        backgroundColor = Color.Blue,

        // on below line we are specifying content color.
        contentColor = Color.White,

        // on below line we are specifying
        // the indicator for the tab
        indicator = { tabPositions ->
            // on below line we are specifying the styling
            // for tab indicator by specifying height
            // and color for the tab indicator.
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {
        // on below line we are specifying icon
        // and text for the individual tab item
        list.forEachIndexed { index, _ ->
            // on below line we are creating a tab.
            Tab(
                // on below line we are specifying icon
                // for each tab item and we are calling
                // image from the list which we have created.
                icon = {
                    Icon(imageVector = list[index].second, contentDescription = null)
                },
                // on below line we are specifying the text for
                // the each tab item and we are calling data
                // from the list which we have created.
                text = {
                    Text(
                        list[index].first,
                        // on below line we are specifying the text color
                        // for the text in that tab
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },
                // on below line we are specifying
                // the tab which is selected.
                selected = pagerState.currentPage == index,
                // on below line we are specifying the
                // on click for the tab which is selected.
                onClick = {
                    // on below line we are specifying the scope.
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

// on below line we are creating a tab content method
// in which we will be displaying the individual page of our tab .
@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState, achievementsViewModel: AchievementsViewModel) {
    // on below line we are creating
    // horizontal pager for our tab layout.
    HorizontalPager(state = pagerState) {
        // on below line we are specifying
        // the different pages.
            page ->
        when (page) {
            // on below line we are calling tab content screen
            // and specifying data as Home Screen.
            0 -> CreateJoinGameView(achievementsViewModel)
            // on below line we are calling tab content screen
            // and specifying data as Shopping Screen.
            1 -> EventScreen {

            }

            2 -> ProfileScreen(data = "Profile")
        }
    }
}

// on below line we are creating a Tab Content
// Screen for displaying a simple text message.
@Composable
fun ProfileScreen(data: String) {
    // on below line we are creating a column
    Column(
        // in this column we are specifying modifier
        // and aligning it center of the screen on below lines.
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // in this column we are specifying the text
        Text(
            // on below line we are specifying the text message
            text = data,

            // on below line we are specifying the text style.
            style = MaterialTheme.typography.h5,

            // on below line we are specifying the text color
            color = Color.Green,

            // on below line we are specifying the font weight
            fontWeight = FontWeight.Bold,

            //on below line we are specifying the text alignment.
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(
    showBackground = true,
    device = Devices.NEXUS_6
)
@Composable
fun MainTabViewPreview() {
    GameAchievementsTheme {
        MainTabView()
    }
}
