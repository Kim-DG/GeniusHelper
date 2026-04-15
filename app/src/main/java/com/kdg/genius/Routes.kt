package com.kdg.genius

sealed class Routes(val route : String) {
    data object Select : Routes("select")
    data object Zombie : Routes("zombie")
    data object Racing : Routes("racing")
}