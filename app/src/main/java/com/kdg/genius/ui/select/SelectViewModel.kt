package com.kdg.genius.ui.select

import androidx.lifecycle.ViewModel
import com.kdg.genius.ui.select.intent.SelectEvent
import com.kdg.genius.ui.select.intent.SelectScreenState
import com.kdg.genius.ui.select.intent.SelectSideEffect
import com.kdg.genius.ui.select.intent.SelectState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class SelectViewModel : ViewModel(),
    ContainerHost<SelectState, SelectSideEffect> {
    override val container = container<SelectState, SelectSideEffect>(SelectState())

    fun sendEvent(event: SelectEvent) = intent {
        when (event) {
            is SelectEvent.SetScreenState -> {
                reduce {
                    state.copy(screenState = event.screenState)
                }
            }

            is SelectEvent.MoveScreen -> {
                postSideEffect(SelectSideEffect.NavigateTo(event.screen.route))
            }
        }
    }
}