package com.kdg.genius.ui.game.sub.bnw.intent

import com.kdg.genius.Routes
import com.kdg.genius.ui.game.sub.bnw.data.BnwParticipant
import com.kdg.genius.ui.game.sub.same_number.data.SameNumberParticipant

sealed class BnwEvent {
    data class SetScreenState(val screenState: BnwScreenState): BnwEvent()
    data class InputParticipant(val participants: List<BnwParticipant>): BnwEvent()
    data class PlusPoint(val index: Int, val amount: Int = 1): BnwEvent()
    data class MinusPoint(val index: Int, val amount: Int = 1): BnwEvent()
    data class PlusScore(val index: Int): BnwEvent()
    data class MinusScore(val index: Int): BnwEvent()
    data class MoveScreen(val screen: Routes): BnwEvent()
}