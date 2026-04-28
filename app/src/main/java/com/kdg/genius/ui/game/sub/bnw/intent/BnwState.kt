package com.kdg.genius.ui.game.sub.bnw.intent

import com.kdg.genius.ui.game.sub.bnw.data.BnwParticipant
import com.kdg.genius.ui.game.sub.same_number.data.SameNumberParticipant

data class BnwState(
    val screenState: BnwScreenState = BnwScreenState.InitScreen,
    val participants: List<BnwParticipant> = listOf(),
)
