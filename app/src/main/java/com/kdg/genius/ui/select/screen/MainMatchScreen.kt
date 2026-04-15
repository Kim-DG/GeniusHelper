package com.kdg.genius.ui.select.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdg.genius.Routes
import com.kdg.genius.ui.select.intent.SelectEvent
import com.kdg.genius.ui.select.intent.SelectScreenState
import com.kdg.genius.ui.theme.GarnetBright
import com.kdg.genius.ui.theme.GarnetRed
import com.kdg.genius.ui.theme.GeniusDarkBlue
import com.kdg.genius.ui.theme.GeniusTheme
import com.kdg.genius.ui.widget.GameListButton
import com.kdg.genius.ui.widget.MenuButton
import com.kdg.genius.ui.widget.Topbar

@Composable
fun MainMatchScreen(sendEvent: (SelectEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GeniusDarkBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Topbar(onClick = {
            sendEvent.invoke(SelectEvent.SetScreenState(SelectScreenState.SelectScreen))
        })

        LazyColumn(modifier = Modifier
            .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(20.dp)) {
            item {
                GameListButton(
                    title = "사기경마",
                    subtitle = "MAIN MATCH",
                    gradientColors = listOf(GarnetRed, GarnetBright),
                    onClick = { sendEvent.invoke(SelectEvent.SetScreenState(SelectScreenState.MainMatchScreen)) }
                )
            }
            item {
                GameListButton(
                    title = "먹이사슬",
                    subtitle = "MAIN MATCH",
                    gradientColors = listOf(GarnetRed, GarnetBright),
                    onClick = { sendEvent.invoke(SelectEvent.SetScreenState(SelectScreenState.MainMatchScreen)) }
                )
            }
            item {
                GameListButton(
                    title = "좀비게임",
                    subtitle = "MAIN MATCH",
                    gradientColors = listOf(GarnetRed, GarnetBright),
                    onClick = { sendEvent.invoke(SelectEvent.MoveScreen(Routes.Zombie)) }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainMatchScreenPreview() {
    GeniusTheme {
        MainMatchScreen({})
    }
}