package com.kdg.genius.ui.game.main.racing.screen

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.kdg.genius.R
import com.kdg.genius.Routes
import com.kdg.genius.ui.game.main.racing.intent.RacingEvent
import com.kdg.genius.ui.select.intent.SelectEvent
import com.kdg.genius.ui.select.intent.SelectScreenState
import com.kdg.genius.ui.theme.DeathMatchBlue
import com.kdg.genius.ui.theme.GarnetBright
import com.kdg.genius.ui.theme.GarnetRed
import com.kdg.genius.ui.theme.GeniusDarkBlue
import com.kdg.genius.ui.theme.GeniusGold
import com.kdg.genius.ui.theme.GeniusTheme
import com.kdg.genius.ui.widget.GeniusButton
import com.kdg.genius.ui.widget.Topbar

@Composable
fun GameScreen(sendEvent: (RacingEvent) -> Unit) {
    val context = LocalContext.current

    val cellSize = 65.dp

    // 말 8마리 위치
    var positions by remember {
        mutableStateOf(
            MutableList(8) { 0 }
        )
    }

    var turn by remember {
        mutableStateOf(0)
    }

    val moveTable = listOf(
        listOf(1, 3, 1, 1, 2, 2, 1, 1),
        listOf(2, 1, 2, 1, 2, 1, 3, 1),
        listOf(3, 0, 2, 3, 2, 2, 2, 2),
        listOf(3, 1, 0, 2, 3, 0, 2, 0),
        listOf(0, 0, 1, 0, 2, 3, 0, 1),
        listOf(1, 0, 1, 1, 0, 3, 1, 1)
    )

    DisposableEffect(Unit) {
        val activity = context as? Activity
        activity?.requestedOrientation =
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        onDispose {
            activity?.requestedOrientation =
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GeniusDarkBlue)
            .padding(36.dp),
    ) {
        Topbar(onClick = {
            sendEvent.invoke(RacingEvent.MoveScreen(Routes.Select))
        })
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Box(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // 트랙
                    Box {
                        Column() {
                            repeat(8) { row ->
                                Row {
                                    repeat(11) { col ->
                                        Box(
                                            modifier = Modifier
                                                .size(cellSize)
                                                .background(
                                                    if (col >= 9)
                                                        Brush.verticalGradient(
                                                            listOf(
                                                                GarnetRed,
                                                                GarnetBright
                                                            )
                                                        )
                                                    else if (col == 0) {
                                                        Brush.verticalGradient(
                                                            listOf(
                                                                Color(0xFF2C3E50), DeathMatchBlue
                                                            )
                                                        )
                                                    } else {
                                                        Brush.verticalGradient(
                                                            listOf(
                                                                Color.Transparent,
                                                                Color.Transparent
                                                            )
                                                        )
                                                    }
                                                )
                                                .border(1.dp, GeniusGold)
                                        ) {
                                            if (col == 0) {
                                                Text(
                                                    "${row + 1}",
                                                    modifier = Modifier.padding(6.dp),
                                                    color = Color.White
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // 말들
                        repeat(8) { horse ->

                            val x by animateDpAsState(
                                targetValue = positions[horse] * cellSize + 5.dp,
                                label = ""
                            )

                            val y = horse * cellSize + 5.dp

                            Icon(
                                painter = painterResource(R.drawable.icon_horse),
                                contentDescription = null,
                                modifier = Modifier
                                    .offset(x = x, y = y)
                                    .size(cellSize - 10.dp),
                                tint = Color.White
                            )
                        }
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Box(modifier = Modifier.weight(4f)) {
                        GeniusButton("출발") {
                            if (turn < moveTable.size) {

                                val steps = moveTable[turn]

                                positions = positions.mapIndexed { i, pos ->
                                    (pos + steps[i])
                                        .coerceAtMost(11)
                                }.toMutableList()

                                turn++
                            }
                        }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        GeniusButton("초기화") {
                            turn = 0
                            positions = MutableList(8) { 0 }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GeniusTheme {
        GameScreen({})
    }
}