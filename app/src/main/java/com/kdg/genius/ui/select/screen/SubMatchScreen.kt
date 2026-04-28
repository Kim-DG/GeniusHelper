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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdg.genius.Routes
import com.kdg.genius.ui.select.intent.SelectEvent
import com.kdg.genius.ui.select.intent.SelectScreenState
import com.kdg.genius.ui.theme.DeathMatchBlue
import com.kdg.genius.ui.theme.GarnetBright
import com.kdg.genius.ui.theme.GarnetRed
import com.kdg.genius.ui.theme.GeniusDarkBlue
import com.kdg.genius.ui.theme.GeniusTheme
import com.kdg.genius.ui.widget.GameListButton
import com.kdg.genius.ui.widget.MenuButton
import com.kdg.genius.ui.widget.Topbar

@Composable
fun SubMatchScreen(sendEvent: (SelectEvent) -> Unit) {
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
                    title = "같은 숫자 찾기",
                    subtitle = "1:1 MATCH",
                    gradientColors = listOf(Color(0xFF2C3E50), DeathMatchBlue),
                    onClick = { sendEvent.invoke(SelectEvent.MoveScreen(Routes.SameNumber)) }
                )
            }
            item {
                GameListButton(
                    title = "흑과백",
                    subtitle = "1:1 MATCH",
                    gradientColors = listOf(Color(0xFF2C3E50), DeathMatchBlue),
                    onClick = { sendEvent.invoke(SelectEvent.MoveScreen(Routes.Bnw)) }
                )
            }
            item {
                GameListButton(
                    title = "인디언 홀덤",
                    subtitle = "1:1 MATCH",
                    gradientColors = listOf(Color(0xFF2C3E50), DeathMatchBlue),
                    onClick = { sendEvent.invoke(SelectEvent.SetScreenState(SelectScreenState.MainMatchScreen)) }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainSubMatchScreenPreview() {
    GeniusTheme {
        SubMatchScreen({})
    }
}