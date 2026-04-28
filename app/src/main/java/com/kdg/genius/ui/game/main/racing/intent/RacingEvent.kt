package com.kdg.genius.ui.game.main.racing.intent

import com.kdg.genius.Routes
import com.kdg.genius.ui.game.main.zombie.data.ZombieParticipant
import com.kdg.genius.ui.game.sub.same_number.intent.SameNumberEvent

sealed class RacingEvent {
    data class SetScreenState(val screenState: RacingScreenState): RacingEvent()
    data class MoveScreen(val screen: Routes): RacingEvent()
}