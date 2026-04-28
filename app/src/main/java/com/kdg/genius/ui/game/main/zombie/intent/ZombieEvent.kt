package com.kdg.genius.ui.game.main.zombie.intent

import com.kdg.genius.Routes
import com.kdg.genius.ui.game.main.racing.intent.RacingEvent
import com.kdg.genius.ui.game.main.zombie.data.ZombieParticipant

sealed class ZombieEvent {
    data class SetScreenState(val screenState: ZombieScreenState): ZombieEvent()
    data class SetDialogState(val dialogState: ZombieDialogState): ZombieEvent()
    data class InputParticipant(val participants: List<ZombieParticipant>): ZombieEvent()
    data class SelectOriginalZombie(val select1: Int, val select2: Int): ZombieEvent()
    data class Touch(val select1: Int, val select2: Int): ZombieEvent()
    data class UseCure(val select: Int): ZombieEvent()
    data class MoveScreen(val screen: Routes): ZombieEvent()
}