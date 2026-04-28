package com.kdg.genius.ui.game.main.zombie.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.Composable
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
import com.kdg.genius.ui.game.main.zombie.intent.ZombieDialogState
import com.kdg.genius.ui.game.main.zombie.intent.ZombieEvent
import com.kdg.genius.ui.game.main.zombie.intent.ZombieState
import com.kdg.genius.ui.theme.DeathMatchBlue
import com.kdg.genius.ui.theme.GeniusDarkBlue
import com.kdg.genius.ui.theme.GeniusGold
import com.kdg.genius.ui.theme.GeniusTheme
import com.kdg.genius.ui.widget.GeniusButton
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun GameScreen(state: ZombieState, sendEvent: (ZombieEvent) -> Unit) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(GeniusDarkBlue)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    for (i in 0..4) {
                        ZombieParticipant(state.participants[i])
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    for (i in 5..9) {
                        ZombieParticipant(state.participants[i])
                    }
                }
            }
        }
        item {
            GeniusButton(title = "최조좀비 선정") {
                if (state.isSelectZombie) {
                    Toast.makeText(context, "이미 최초좀비 선정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    sendEvent.invoke(ZombieEvent.SetDialogState(ZombieDialogState.OriginalZombie))
                }
            }
        }
        item {
            GeniusButton(title = "터치") {
                if (state.isSelectZombie) {
                    sendEvent.invoke(ZombieEvent.SetDialogState(ZombieDialogState.Touch))
                } else {
                    Toast.makeText(context, "최초좀비 선정을 먼저 진행해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
        item {
            GeniusButton(title = "치료") {
                if (state.isSelectZombie) {
                    sendEvent.invoke(ZombieEvent.SetDialogState(ZombieDialogState.UseCure))
                } else {
                    Toast.makeText(context, "최초좀비 선정을 먼저 진행해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun ZombieParticipant(participant: ZombieParticipant) {
    val zombieTime = participant.zombieTime
    val now = System.currentTimeMillis()
    val date = Date(zombieTime)

    val sdf = SimpleDateFormat("HH:mm:ss")
    val getTime = sdf.format(date)

    Card(
        modifier = Modifier
            .size(width = 150.dp, height = 120.dp)
            .wrapContentHeight()
            .border(2.dp, GeniusGold, RoundedCornerShape(12.dp)), // 골드 테두리
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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = participant.name,
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = participant.state.title,
                    color = if (participant.state == ParticipantState.Human) Color.White else Color.Green,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                if (participant.state == ParticipantState.Human) {
                    Text(
                        text = participant.point.toString(),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                if (participant.state == ParticipantState.Zombie) {
                    Text(
                        text = getTime.toString(),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    val time = System.currentTimeMillis() - 60000L

    GeniusTheme {
        GameScreen(
            state = ZombieState(
                participants = listOf(
                    ZombieParticipant(
                        name = "test1",
                        state = ParticipantState.Human,
                        point = 0,
                        zombieTime = time
                    ),
                    ZombieParticipant(
                        name = "test2",
                        state = ParticipantState.Zombie,
                        point = 0,
                        zombieTime = time
                    ),
                    ZombieParticipant(
                        name = "test3",
                        state = ParticipantState.Human,
                        point = 0,
                        zombieTime = time
                    ),
                    ZombieParticipant(
                        name = "test4",
                        state = ParticipantState.Human,
                        point = 0,
                        zombieTime = time
                    ),
                    ZombieParticipant(
                        name = "test5",
                        state = ParticipantState.Human,
                        point = 0,
                        zombieTime = time
                    ),
                    ZombieParticipant(
                        name = "test6",
                        state = ParticipantState.Zombie,
                        point = 0,
                        zombieTime = time
                    ),
                    ZombieParticipant(
                        name = "test7",
                        state = ParticipantState.Human,
                        point = 0,
                        zombieTime = time
                    ),
                    ZombieParticipant(
                        name = "test8",
                        state = ParticipantState.Human,
                        point = 0,
                        zombieTime = time
                    ),
                    ZombieParticipant(
                        name = "test9",
                        state = ParticipantState.OriginalZombie,
                        point = 0,
                        zombieTime = time
                    ),
                    ZombieParticipant(
                        name = "test10",
                        state = ParticipantState.Human,
                        point = 0,
                        zombieTime = time
                    )
                )
            )
        ) {}
    }
}