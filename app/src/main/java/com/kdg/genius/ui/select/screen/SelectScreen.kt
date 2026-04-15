package com.kdg.genius.ui.select.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kdg.genius.ui.select.intent.SelectEvent
import com.kdg.genius.ui.select.intent.SelectScreenState
import com.kdg.genius.ui.theme.DeathMatchBlue
import com.kdg.genius.ui.theme.GarnetBright
import com.kdg.genius.ui.theme.GarnetRed
import com.kdg.genius.ui.theme.GeniusDarkBlue
import com.kdg.genius.ui.theme.GeniusGold
import com.kdg.genius.ui.theme.GeniusTheme
import com.kdg.genius.ui.theme.primary
import com.kdg.genius.ui.widget.MenuButton
import com.kdg.genius.ui.widget.Topbar

@Composable
fun SelectScreen(sendEvent: (SelectEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GeniusDarkBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 타이틀
        Text(
            text = "THE GENIUS\n게임 도우미",
            color = GeniusGold,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 40.sp,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        // 메인매치 버튼
        MenuButton(
            title = "메인매치",
            subtitle = "MAIN MATCH",
            gradientColors = listOf(GarnetRed, GarnetBright),
            onClick = { sendEvent.invoke(SelectEvent.SetScreenState(SelectScreenState.MainMatchScreen)) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 1:1 매치 버튼
        MenuButton(
            title = "1:1 매치",
            subtitle = "DEATH MATCH",
            gradientColors = listOf(Color(0xFF2C3E50), DeathMatchBlue),
            onClick = { sendEvent.invoke(SelectEvent.SetScreenState(SelectScreenState.SubMatchScreen)) }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SelectScreenPreview() {
    GeniusTheme {
        SelectScreen({})
    }
}