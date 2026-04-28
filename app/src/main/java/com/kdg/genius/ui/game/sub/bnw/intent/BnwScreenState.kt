package com.kdg.genius.ui.game.sub.bnw.intent

sealed class BnwScreenState {
    data object InitScreen: BnwScreenState()
    data object GameScreen: BnwScreenState()
}