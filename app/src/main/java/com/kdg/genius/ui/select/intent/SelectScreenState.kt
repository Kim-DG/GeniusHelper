package com.kdg.genius.ui.select.intent

sealed class SelectScreenState {
    data object SelectScreen: SelectScreenState()
    data object MainMatchScreen: SelectScreenState()
    data object SubMatchScreen: SelectScreenState()
}