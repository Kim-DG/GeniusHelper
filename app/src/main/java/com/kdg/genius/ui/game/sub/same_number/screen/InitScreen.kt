package com.kdg.genius.ui.game.sub.same_number.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kdg.genius.ui.game.main.zombie.data.ZombieParticipant
import com.kdg.genius.ui.game.main.zombie.intent.ZombieEvent
import com.kdg.genius.ui.game.sub.same_number.data.SameNumberParticipant
import com.kdg.genius.ui.game.sub.same_number.intent.SameNumberEvent
import com.kdg.genius.ui.theme.GeniusDarkBlue
import com.kdg.genius.ui.theme.GeniusGold
import com.kdg.genius.ui.theme.GeniusTheme
import com.kdg.genius.ui.widget.GeniusButton
import com.kdg.genius.ui.widget.GeniusTextField

@Composable
fun InitScreen(sendEvent: (SameNumberEvent) -> Unit) {
    val context = LocalContext.current

    val participants = remember {
        mutableStateListOf<SameNumberParticipant>().apply {
            repeat(2) { add(SameNumberParticipant.Init) }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(GeniusDarkBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "참가자 입력",
                color = GeniusGold,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp,
            )
        }
        items(count = 2) { index ->
            GeniusTextField(
                value = participants[index].name,
                label = "참가자 ${index + 1}"
            ) { newValue ->
                participants[index] = participants[index].copy(
                    name = newValue
                )
            }
        }
        item {
            GeniusButton(title = "다음으로") {
                if (participants.all { it.name.isNotBlank() }) {
                    sendEvent.invoke(SameNumberEvent.InputParticipant(participants))
                } else {
                    Toast.makeText(context, "참가자 10명을 전부 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun InitScreenPreview() {
    GeniusTheme {
        InitScreen({})
    }
}