package com.kdg.genius.ui.game.sub.same_number.intent

sealed class SameNumberSideEffect {
    data class NavigateTo(val route: String) : SameNumberSideEffect()
}