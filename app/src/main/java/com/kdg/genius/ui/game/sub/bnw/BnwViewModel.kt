package com.kdg.genius.ui.game.sub.bnw

import androidx.lifecycle.ViewModel
import com.kdg.genius.ui.game.sub.bnw.intent.BnwEvent
import com.kdg.genius.ui.game.sub.bnw.intent.BnwScreenState
import com.kdg.genius.ui.game.sub.bnw.intent.BnwSideEffect
import com.kdg.genius.ui.game.sub.bnw.intent.BnwState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class BnwViewModel : ViewModel(),
    ContainerHost<BnwState, BnwSideEffect> {
    override val container = container<BnwState, BnwSideEffect>(BnwState())

    fun sendEvent(event: BnwEvent) = intent {
        when (event) {
            is BnwEvent.SetScreenState -> {
                reduce {
                    state.copy(screenState = event.screenState)
                }
            }
            is BnwEvent.InputParticipant -> {
                reduce {
                    state.copy(
                        participants = event.participants,
                        screenState = BnwScreenState.GameScreen
                    )
                }
            }
            is BnwEvent.PlusPoint -> {
                val updatedParticipants =
                    state.participants.mapIndexed { index, item ->
                        if (index == event.index) {
                            item.copy(point = item.point + event.amount)
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
            is BnwEvent.MinusPoint -> {
                val updatedParticipants =
                    state.participants.mapIndexed { index, item ->
                        if (index == event.index) {
                            item.copy(point = item.point - event.amount)
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
            is BnwEvent.PlusScore -> {
                val updatedParticipants =
                    state.participants.mapIndexed { index, item ->
                        if (index == event.index) {
                            item.copy(score = item.score + 1)
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
            is BnwEvent.MinusScore -> {
                val updatedParticipants =
                    state.participants.mapIndexed { index, item ->
                        if (index == event.index) {
                            item.copy(score = item.score - 1)
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
            is BnwEvent.MoveScreen -> {
                postSideEffect(BnwSideEffect.NavigateTo(event.screen.route))
            }
        }
    }
}