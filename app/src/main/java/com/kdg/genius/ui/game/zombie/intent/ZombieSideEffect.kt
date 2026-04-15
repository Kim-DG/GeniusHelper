package com.kdg.genius.ui.game.zombie.intent

sealed class ZombieSideEffect {
    data class NavigateTo(val route: String) : ZombieSideEffect()
}