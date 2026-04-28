package com.kdg.genius.ui.game.sub.bnw.data

data class BnwParticipant(
    val name: String,
    val point: Int,
    val score: Int,
) {
    companion object {
        val Init = BnwParticipant(
            name = "",
            point = 99,
            score = 0,
        )
    }
}