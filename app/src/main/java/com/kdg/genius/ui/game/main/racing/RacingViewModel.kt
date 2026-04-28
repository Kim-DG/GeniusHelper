package com.kdg.genius.ui.game.main.racing

import androidx.lifecycle.ViewModel
import com.kdg.genius.ui.game.main.racing.intent.RacingEvent
import com.kdg.genius.ui.game.main.racing.intent.RacingSideEffect
import com.kdg.genius.ui.game.main.racing.intent.RacingState
import com.kdg.genius.ui.game.main.zombie.data.ParticipantState
import com.kdg.genius.ui.game.main.zombie.intent.ZombieDialogState
import com.kdg.genius.ui.game.main.zombie.intent.ZombieEvent
import com.kdg.genius.ui.game.main.zombie.intent.ZombieScreenState
import com.kdg.genius.ui.game.main.zombie.intent.ZombieSideEffect
import com.kdg.genius.ui.game.main.zombie.intent.ZombieState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class RacingViewModel : ViewModel(),
    ContainerHost<RacingState, RacingSideEffect> {
    override val container = container<RacingState, RacingSideEffect>(RacingState())

    fun sendEvent(event: RacingEvent) = intent {
        when (event) {
            is RacingEvent.SetScreenState -> {
                reduce {
                    state.copy(screenState = event.screenState)
                }
            }
            is RacingEvent.MoveScreen -> {
                postSideEffect(RacingSideEffect.NavigateTo(event.screen.route))
            }
        }
    }
}