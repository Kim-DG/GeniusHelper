package com.kdg.genius.ui.game.sub.same_number

import android.util.Log.i
import androidx.lifecycle.ViewModel
import com.kdg.genius.ui.game.main.zombie.intent.ZombieEvent
import com.kdg.genius.ui.game.main.zombie.intent.ZombieScreenState
import com.kdg.genius.ui.game.sub.same_number.intent.SameNumberEvent
import com.kdg.genius.ui.game.sub.same_number.intent.SameNumberScreenState
import com.kdg.genius.ui.game.sub.same_number.intent.SameNumberSideEffect
import com.kdg.genius.ui.game.sub.same_number.intent.SameNumberState
import com.kdg.genius.ui.select.intent.SelectEvent
import com.kdg.genius.ui.select.intent.SelectSideEffect
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class SameNumberViewModel : ViewModel(),
    ContainerHost<SameNumberState, SameNumberSideEffect> {
    override val container = container<SameNumberState, SameNumberSideEffect>(SameNumberState())

    fun sendEvent(event: SameNumberEvent) = intent {
        when (event) {
            is SameNumberEvent.SetScreenState -> {
                reduce {
                    state.copy(screenState = event.screenState)
                }
            }
            is SameNumberEvent.InputParticipant -> {
                reduce {
                    state.copy(
                        participants = event.participants,
                        screenState = SameNumberScreenState.GameScreen
                    )
                }
            }
            is SameNumberEvent.PlusPoint -> {
                val updatedParticipants =
                    state.participants.mapIndexed { index, item ->
                        if (index == event.index) {
                            item.copy(point = item.point + 1)
                        } else {
                            item
                        }
                    }
                reduce {
                    state.copy(
                        participants = updatedParticipants,
                    )
                }
            }
            is SameNumberEvent.MinusPoint -> {
                val updatedParticipants =
                    state.participants.mapIndexed { index, item ->
                        if (index == event.index) {
                            item.copy(point = item.point - 1)
                        } else {
                            item
                        }
                    }
                reduce {
                    state.copy(
                        participants = updatedParticipants,
                    )
                }
            }
            is SameNumberEvent.MoveScreen -> {
                postSideEffect(SameNumberSideEffect.NavigateTo(event.screen.route))
            }
        }
    }
}