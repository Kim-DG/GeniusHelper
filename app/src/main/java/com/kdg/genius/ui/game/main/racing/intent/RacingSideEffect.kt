package com.kdg.genius.ui.game.main.racing.intent

sealed class RacingSideEffect {
    data class NavigateTo(val route: String) : RacingSideEffect()
}