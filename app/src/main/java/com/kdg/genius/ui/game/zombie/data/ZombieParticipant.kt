package com.kdg.genius.ui.game.zombie.data

data class ZombieParticipant(
    val name: String,
    val state: ParticipantState,
    val point: Int,
    val zombieTime: Long,
) {
    companion object {
        val Init = ZombieParticipant(
            name = "",
            state = ParticipantState.Human,
            point = 0,
            zombieTime = 0
        )
    }
}