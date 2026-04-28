package com.kdg.genius.ui.game.sub.same_number.intent

import com.kdg.genius.ui.game.sub.same_number.data.SameNumberParticipant

data class SameNumberState(
    val screenState: SameNumberScreenState = SameNumberScreenState.InitScreen,
    val participants: List<SameNumberParticipant> = listOf(),
    val a: List<String> = listOf(
        "A", "O", "M", "T",
        "Y", "Z", "V", "W",
        "R", "B", "U", "G",
        "S", "N", "E", "D"
    ),
    val numbers: List<String> = listOf(
        "5", "-", "3", "11",
        "10", "4", "2", "x",
        "6", "9", "+", "8",
        "÷", "7", "12", "1"
    )
)
