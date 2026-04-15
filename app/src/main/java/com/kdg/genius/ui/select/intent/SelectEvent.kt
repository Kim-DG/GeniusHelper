package com.kdg.genius.ui.select.intent

import com.kdg.genius.Routes

sealed class SelectEvent {
    data class SetScreenState(val screenState: SelectScreenState): SelectEvent()
    data class MoveScreen(val screen: Routes): SelectEvent()
}