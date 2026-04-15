package com.kdg.genius.ui.game.zombie.intent

sealed class ZombieDialogState {
    data object None: ZombieDialogState()
    data object Back: ZombieDialogState()
    data object Touch: ZombieDialogState()
    data object OriginalZombie: ZombieDialogState()
    data object UseCure: ZombieDialogState()
}