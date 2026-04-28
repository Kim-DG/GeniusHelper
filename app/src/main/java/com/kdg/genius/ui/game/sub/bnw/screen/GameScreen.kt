package com.kdg.genius.ui.game.sub.bnw.screen

import android.util.Log
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
import com.kdg.genius.Routes
import com.kdg.genius.ui.game.main.zombie.data.ParticipantState
import com.kdg.genius.ui.game.main.zombie.data.ZombieParticipant
import com.kdg.genius.ui.game.sub.bnw.intent.BnwEvent
import com.kdg.genius.ui.game.sub.bnw.intent.BnwState
import com.kdg.genius.ui.game.sub.same_number.intent.SameNumberEvent
import com.kdg.genius.ui.game.sub.same_number.intent.SameNumberState
import com.kdg.genius.ui.theme.DeathMatchBlue
import com.kdg.genius.ui.theme.GeniusDarkBlue
import com.kdg.genius.ui.theme.GeniusGold
import com.kdg.genius.ui.theme.GeniusTheme
import com.kdg.genius.ui.widget.GeniusButton
import com.kdg.genius.ui.widget.Topbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GameScreen(state: BnwState, sendEvent: (BnwEvent) -> Unit) {
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
        Topbar(onClick = {
            sendEvent.invoke(BnwEvent.MoveScreen(Routes.Select))
        })
        Row(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                LevelBar(value = state.participants[0].point)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = {
                        sendEvent.invoke(BnwEvent.MinusPoint(0,10))
                    }) {
                        Text(
                            text = "-10",
                            color = Color.Gray,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    TextButton(onClick = {
                        sendEvent.invoke(BnwEvent.MinusPoint(0))
                    }) {
                        Text(
                            text = "-1",
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
                        sendEvent.invoke(BnwEvent.PlusPoint(0))
                    }) {
                        Text(
                            text = "+1",
                            color = Color.Gray,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    TextButton(onClick = {
                        sendEvent.invoke(BnwEvent.PlusPoint(0, 10))
                    }) {
                        Text(
                            text = "+10",
                            color = Color.Gray,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
                Text(
                    text = state.participants[0].name,
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = {
                        sendEvent.invoke(BnwEvent.MinusScore(0))
                    }) {
                        Text(
                            text = "-",
                            color = Color.Gray,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    Text(
                        text = state.participants[0].score.toString(),
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    TextButton(onClick = {
                        sendEvent.invoke(BnwEvent.PlusScore(0))
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
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                LevelBar(value = state.participants[1].point)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = {
                        sendEvent.invoke(BnwEvent.MinusPoint(1, 10))
                    }) {
                        Text(
                            text = "-10",
                            color = Color.Gray,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    TextButton(onClick = {
                        sendEvent.invoke(BnwEvent.MinusPoint(1))
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
                        sendEvent.invoke(BnwEvent.PlusPoint(1))
                    }) {
                        Text(
                            text = "+",
                            color = Color.Gray,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    TextButton(onClick = {
                        sendEvent.invoke(BnwEvent.PlusPoint(1, 10))
                    }) {
                        Text(
                            text = "+10",
                            color = Color.Gray,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
                Text(
                    text = state.participants[1].name,
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = {
                        sendEvent.invoke(BnwEvent.MinusScore(1))
                    }) {
                        Text(
                            text = "-",
                            color = Color.Gray,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    Text(
                        text = state.participants[1].score.toString(),
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    TextButton(onClick = {
                        sendEvent.invoke(BnwEvent.PlusScore(1))
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
    }
}

@Composable
fun LevelBar(
    value: Int,
    modifier: Modifier = Modifier
) {
    Log.d("!!!!!!!!!!",value.toString())
    val filledCount = (value / 20).coerceIn(0, 4) + 1

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // 위에서부터 그리되, 아래부터 채워야 하니까 index 반대로 계산
        for (i in 0 until 5) {
            val isFilled = i >= (5 - filledCount)

            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 20.dp)
                    .border(1.dp, Color.Gray)
                    .background(
                        if (isFilled) GeniusGold else Color.Transparent
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GeniusTheme {
        GameScreen(state = BnwState(), {})
    }
}