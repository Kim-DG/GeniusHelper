package com.kdg.genius.ui.game.sub.same_number.data

data class SameNumberParticipant(
    val name: String,
    val point: Int,
) {
    companion object {
        val Init = SameNumberParticipant(
            name = "",
            point = 0,
        )
    }
}