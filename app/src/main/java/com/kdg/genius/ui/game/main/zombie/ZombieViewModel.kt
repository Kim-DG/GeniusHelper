package com.kdg.genius.ui.game.main.zombie

import androidx.lifecycle.ViewModel
import com.kdg.genius.ui.game.main.zombie.data.ParticipantState
import com.kdg.genius.ui.game.main.zombie.intent.ZombieDialogState
import com.kdg.genius.ui.game.main.zombie.intent.ZombieEvent
import com.kdg.genius.ui.game.main.zombie.intent.ZombieScreenState
import com.kdg.genius.ui.game.main.zombie.intent.ZombieSideEffect
import com.kdg.genius.ui.game.main.zombie.intent.ZombieState
import com.kdg.genius.ui.game.sub.same_number.intent.SameNumberEvent
import com.kdg.genius.ui.game.sub.same_number.intent.SameNumberSideEffect
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class ZombieViewModel : ViewModel(),
    ContainerHost<ZombieState, ZombieSideEffect> {
    override val container = container<ZombieState, ZombieSideEffect>(ZombieState())

    fun sendEvent(event: ZombieEvent) = intent {
        when (event) {
            is ZombieEvent.SetScreenState -> {
                reduce {
                    state.copy(screenState = event.screenState)
                }
            }

            is ZombieEvent.SetDialogState -> {
                reduce {
                    state.copy(dialogState = event.dialogState)
                }
            }

            is ZombieEvent.InputParticipant -> {
                reduce {
                    state.copy(
                        participants = event.participants,
                        screenState = ZombieScreenState.GameScreen
                    )
                }
            }

            is ZombieEvent.SelectOriginalZombie -> {
                reduce {
                    state.copy(
                        dialogState = ZombieDialogState.None,
                        isSelectZombie = true,
                        participants = state.participants.mapIndexed { i, item ->
                            if (i == event.select1 || i == event.select2) {
                                item.copy(state = ParticipantState.OriginalZombie)
                            } else {
                                item
                            }
                        }
                    )
                }
            }

            is ZombieEvent.Touch -> {
                val firstPlayerState = state.participants[event.select1].state
                val secondPlayerState = state.participants[event.select2].state
                if (firstPlayerState == ParticipantState.Human && secondPlayerState == ParticipantState.Human) {
                    reduce {
                        state.copy(
                            participants = state.participants.mapIndexed { i, item ->
                                if (i == event.select1 || i == event.select2) {
                                    item.copy(point = item.point + 1)
                                } else {
                                    item
                                }
                            }
                        )
                    }
                } else if (firstPlayerState == ParticipantState.Human) {
                    val now = System.currentTimeMillis()
                    reduce {
                        state.copy(
                            participants = state.participants.mapIndexed { i, item ->
                                if (i == event.select1) {
                                    item.copy(state = ParticipantState.Zombie, zombieTime = now)
                                } else {
                                    item
                                }
                            }
                        )
                    }
                } else if (secondPlayerState == ParticipantState.Human) {
                    val now = System.currentTimeMillis()
                    reduce {
                        state.copy(
                            participants = state.participants.mapIndexed { i, item ->
                                if (i == event.select2) {
                                    item.copy(state = ParticipantState.Zombie, zombieTime = now)
                                } else {
                                    item
                                }
                            }
                        )
                    }
                }
                reduce { state.copy(dialogState = ZombieDialogState.None) }
            }

            is ZombieEvent.UseCure -> {
                val user = state.participants[event.select]
                val now = System.currentTimeMillis()
                if (user.state == ParticipantState.Zombie && (now - user.zombieTime < 60000)) {
                    reduce {
                        state.copy(
                            participants = state.participants.mapIndexed { i, item ->
                                if (i == event.select) {
                                    item.copy(state = ParticipantState.Human)
                                } else {
                                    item
                                }
                            }
                        )
                    }
                }
                reduce {
                    state.copy(dialogState = ZombieDialogState.None)
                }
            }

            is ZombieEvent.MoveScreen -> {
                postSideEffect(ZombieSideEffect.NavigateTo(event.screen.route))
            }
        }
    }
}