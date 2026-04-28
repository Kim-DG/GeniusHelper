package com.kdg.genius.ui.game.sub.bnw.intent

sealed class BnwSideEffect {
    data class NavigateTo(val route: String) : BnwSideEffect()
}