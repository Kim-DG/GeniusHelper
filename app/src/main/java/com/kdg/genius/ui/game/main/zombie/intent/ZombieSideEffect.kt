package com.kdg.genius.ui.game.main.zombie.intent

sealed class ZombieSideEffect {
    data class NavigateTo(val route: String) : ZombieSideEffect()
}