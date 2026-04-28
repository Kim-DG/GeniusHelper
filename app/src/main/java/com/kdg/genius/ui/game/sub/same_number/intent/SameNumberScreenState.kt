package com.kdg.genius.ui.game.sub.same_number.intent

sealed class SameNumberScreenState {
    data object InitScreen: SameNumberScreenState()
    data object GameScreen: SameNumberScreenState()
}