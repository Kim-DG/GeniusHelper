package com.kdg.genius

import androidx.compose.foundation.focusable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kdg.genius.ui.game.zombie.addZombie
import com.kdg.genius.ui.select.addSelect

@Composable
fun SetupNavGraph() {
    val navController = LocalNavController.current
    NavHost(
        navController = navController,
        startDestination = Routes.Select.route,
    ) {
        addSelect()
        addZombie()
    }
}