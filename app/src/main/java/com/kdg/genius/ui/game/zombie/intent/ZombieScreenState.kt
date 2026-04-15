package com.kdg.genius.ui.game.zombie.intent

sealed class ZombieScreenState {
    data object InitScreen: ZombieScreenState()
    data object GameScreen: ZombieScreenState()
}