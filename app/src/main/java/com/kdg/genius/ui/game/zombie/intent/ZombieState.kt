package com.kdg.genius.ui.game.zombie.intent

import com.kdg.genius.ui.game.zombie.data.ZombieParticipant

data class ZombieState(
    val screenState: ZombieScreenState = ZombieScreenState.InitScreen,
    val dialogState: ZombieDialogState = ZombieDialogState.None,
    val participants: List<ZombieParticipant> = listOf(),
    val isSelectZombie: Boolean = false,
)
