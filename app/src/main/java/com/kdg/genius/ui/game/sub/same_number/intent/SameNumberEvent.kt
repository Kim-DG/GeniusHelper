package com.kdg.genius.ui.game.sub.same_number.intent

import com.kdg.genius.Routes
import com.kdg.genius.ui.game.main.zombie.data.ZombieParticipant
import com.kdg.genius.ui.game.main.zombie.intent.ZombieEvent
import com.kdg.genius.ui.game.sub.same_number.data.SameNumberParticipant
import com.kdg.genius.ui.select.intent.SelectEvent

sealed class SameNumberEvent {
    data class SetScreenState(val screenState: SameNumberScreenState): SameNumberEvent()
    data class InputParticipant(val participants: List<SameNumberParticipant>): SameNumberEvent()
    data class PlusPoint(val index: Int): SameNumberEvent()
    data class MinusPoint(val index: Int): SameNumberEvent()
    data class MoveScreen(val screen: Routes): SameNumberEvent()
}