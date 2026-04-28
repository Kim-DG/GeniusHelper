package com.kdg.genius

sealed class Routes(val route : String) {
    data object Select : Routes("select")
    data object Zombie : Routes("zombie")
    data object Racing : Routes("racing")
    data object SameNumber: Routes("same_number")
    data object Bnw: Routes("black_white")
}