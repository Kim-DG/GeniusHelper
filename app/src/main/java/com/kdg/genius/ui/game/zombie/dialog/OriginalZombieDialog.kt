package com.kdg.genius.ui.game.zombie.dialog

import android.R.attr.fontWeight
import android.util.Log.i
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.kdg.genius.ui.game.zombie.data.ParticipantState
import com.kdg.genius.ui.game.zombie.data.ZombieParticipant
import com.kdg.genius.ui.game.zombie.intent.ZombieDialogState
import com.kdg.genius.ui.game.zombie.intent.ZombieEvent
import com.kdg.genius.ui.game.zombie.intent.ZombieState
import com.kdg.genius.ui.theme.DeathMatchBlue
import com.kdg.genius.ui.theme.GeniusDarkBlue
import com.kdg.genius.ui.theme.GeniusGold
import com.kdg.genius.ui.theme.GeniusTheme
import com.kdg.genius.ui.theme.white
import com.kdg.genius.ui.theme.zombieGreen

@Composable
fun OriginalZombieDialog(state: ZombieState, sendEvent: (ZombieEvent) -> Unit) {
    val context = LocalContext.current
    var select1 by remember { mutableStateOf(-1) }
    var select2 by remember { mutableStateOf(-1) }

    Dialog(onDismissRequest = {

    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .border(2.dp, GeniusGold, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .background(GeniusDarkBlue),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 0..2) {
                    ParticipantButton(
                        participant = state.participants[i]
                    ) {
                        if (select1 < 0) {
                            select1 = i
                        } else if (select2 < 0) {
                            select2 = i
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 3..5) {
                    ParticipantButton(
                        participant = state.participants[i]
                    ) {
                        if (select1 < 0) {
                            select1 = i
                        } else if (select2 < 0) {
                            select2 = i
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 6..8) {
                    ParticipantButton(
                        participant = state.participants[i]
                    ) {
                        if (select1 < 0) {
                            select1 = i
                        } else if (select2 < 0) {
                            select2 = i
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TouchActionButton(
                    "취소"
                ) {
                    sendEvent.invoke(ZombieEvent.SetDialogState(ZombieDialogState.None))
                }

                ParticipantButton(
                    participant = state.participants[9]
                ) {
                    if (select1 < 0) {
                        select1 = 9
                    } else if (select2 < 0) {
                        select2 = 9
                    }
                }
                TouchActionButton(
                    "확인"
                ) {
                    if (select1 >= 0 && select2 >= 0) {
                        sendEvent.invoke(ZombieEvent.SelectOriginalZombie(select1 = select1, select2 = select2))
                    } else {
                        Toast.makeText(context, "최초 좀비 두 명을 골라주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            Text(
                text = "최초 좀비 선정",
                color = white,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TouchActionButton(
                    if (select1 >= 0) state.participants[select1].name else "없음"
                ) {
                    select1 = -1
                }
                TouchActionButton(
                    if (select2 >= 0) state.participants[select2].name else "없음"
                ) {
                    select2 = -1
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OriginalZombieDialogPreview() {
    val time = System.currentTimeMillis() - 60000L
    GeniusTheme {
        OriginalZombieDialog(
            state = ZombieState(
                participants = listOf(
                    ZombieParticipant(
                        name = "test1",
                        state = ParticipantState.Human,
                        point = 0,
                        zombieTime = 0
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
                        zombieTime = 0
                    ),
                    ZombieParticipant(
                        name = "test4",
                        state = ParticipantState.Human,
                        point = 0,
                        zombieTime = 0
                    ),
                    ZombieParticipant(
                        name = "test5",
                        state = ParticipantState.Human,
                        point = 0,
                        zombieTime = 0
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
                        zombieTime = 0
                    ),
                    ZombieParticipant(
                        name = "test8",
                        state = ParticipantState.Human,
                        point = 0,
                        zombieTime = 0
                    ),
                    ZombieParticipant(
                        name = "test9",
                        state = ParticipantState.OriginalZombie,
                        point = 0,
                        zombieTime = 0
                    ),
                    ZombieParticipant(
                        name = "test10",
                        state = ParticipantState.Human,
                        point = 0,
                        zombieTime = time
                    )
                )
            ), {})
    }
}