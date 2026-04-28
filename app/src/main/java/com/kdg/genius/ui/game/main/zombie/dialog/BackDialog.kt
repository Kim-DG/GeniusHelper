package com.kdg.genius.ui.game.main.zombie.dialog

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.kdg.genius.Routes
import com.kdg.genius.ui.game.main.zombie.data.ParticipantState
import com.kdg.genius.ui.game.main.zombie.data.ZombieParticipant
import com.kdg.genius.ui.game.main.zombie.intent.ZombieDialogState
import com.kdg.genius.ui.game.main.zombie.intent.ZombieEvent
import com.kdg.genius.ui.game.main.zombie.intent.ZombieState
import com.kdg.genius.ui.theme.GeniusDarkBlue
import com.kdg.genius.ui.theme.GeniusGold
import com.kdg.genius.ui.theme.GeniusTheme
import com.kdg.genius.ui.theme.white
import kotlinx.coroutines.selects.select

@Composable
fun BackDialog(sendEvent: (ZombieEvent) -> Unit) {
    Dialog(onDismissRequest = {

    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .border(2.dp, GeniusGold, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .background(GeniusDarkBlue),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "처음 화면으로\n돌아가시겠습니까?\n모든 내용이 초기화됩니다.",
                color = white,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
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
                TouchActionButton(
                    "확인"
                ) {
                    sendEvent.invoke(ZombieEvent.SetDialogState(ZombieDialogState.None))
                    sendEvent.invoke(ZombieEvent.MoveScreen(Routes.Select))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BackDialogPreview() {
    GeniusTheme {
        BackDialog({})
    }
}