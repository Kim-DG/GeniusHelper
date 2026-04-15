package com.kdg.genius.ui.select.intent

sealed class SelectSideEffect {
    data class NavigateTo(val route: String) : SelectSideEffect()
}