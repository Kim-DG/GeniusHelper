package com.kdg.genius.ui.game.sub.same_number.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kdg.genius.ui.game.main.zombie.data.ParticipantState
import com.kdg.genius.ui.game.main.zombie.data.ZombieParticipant
import com.kdg.genius.ui.game.sub.same_number.intent.SameNumberEvent
import com.kdg.genius.ui.game.sub.same_number.intent.SameNumberState
import com.kdg.genius.ui.theme.DeathMatchBlue
import com.kdg.genius.ui.theme.GeniusDarkBlue
import com.kdg.genius.ui.theme.GeniusGold
import com.kdg.genius.ui.theme.GeniusTheme
import com.kdg.genius.ui.widget.GeniusButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GameScreen(state: SameNumberState, sendEvent: (SameNumberEvent) -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isVisibles = remember {
        mutableStateListOf<Boolean>().apply {
            repeat(16) { add(false) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GeniusDarkBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterVertically
        )
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            for (i in 0..3) {
                SameNumberBlock(
                    alpha = state.a[i],
                    number = state.numbers[i],
                    isVisible = isVisibles[i]
                ) {
                    isVisibles[i] = !isVisibles[i]
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            for (i in 4..7) {
                SameNumberBlock(
                    alpha = state.a[i],
                    number = state.numbers[i],
                    isVisible = isVisibles[i]
                ) {
                    isVisibles[i] = !isVisibles[i]
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            for (i in 8..11) {
                SameNumberBlock(
                    alpha = state.a[i],
                    number = state.numbers[i],
                    isVisible = isVisibles[i]
                ) {
                    isVisibles[i] = !isVisibles[i]
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            for (i in 12..15) {
                SameNumberBlock(
                    alpha = state.a[i],
                    number = state.numbers[i],
                    isVisible = isVisibles[i]
                ) {
                    isVisibles[i] = !isVisibles[i]
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = state.participants[0].name,
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = {
                        sendEvent.invoke(SameNumberEvent.MinusPoint(0))
                    }) {
                        Text(
                            text = "-",
                            color = Color.Gray,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    Text(
                        text = state.participants[0].point.toString(),
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    TextButton(onClick = {
                        sendEvent.invoke(SameNumberEvent.PlusPoint(0))
                    }) {
                        Text(
                            text = "+",
                            color = Color.Gray,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = state.participants[1].name,
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = {
                        sendEvent.invoke(SameNumberEvent.MinusPoint(1))
                    }) {
                        Text(
                            text = "-",
                            color = Color.Gray,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    Text(
                        text = state.participants[1].point.toString(),
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    TextButton(onClick = {
                        sendEvent.invoke(SameNumberEvent.PlusPoint(1))
                    }) {
                        Text(
                            text = "+",
                            color = Color.Gray,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
        GeniusButton(title = "전체공개") {
            scope.launch {
                isVisibles.indices.forEach {
                    isVisibles[it] = true
                }
                delay(5000)
                isVisibles.indices.forEach {
                    isVisibles[it] = false
                }
            }
        }
        GeniusButton(title = "가리기") {
            isVisibles.indices.forEach {
                isVisibles[it] = false
            }
        }
    }
}

@Composable
fun SameNumberBlock(alpha: String, number: String, isVisible: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(width = 75.dp, height = 90.dp)
            .wrapContentHeight()
            .border(2.dp, GeniusGold, RoundedCornerShape(12.dp)) // 골드 테두리
            .clickable {
                onClick.invoke()
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF2C3E50),
                            DeathMatchBlue
                        )
                    )
                ), // 그라데이션 배경
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                targetState = if (isVisible) number else alpha,
                label = ""
            ) { value ->
                Text(
                    text = value,
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GeniusTheme {
        GameScreen(state = SameNumberState(), {})
    }
}