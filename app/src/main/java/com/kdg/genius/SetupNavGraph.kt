package com.kdg.genius

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.kdg.genius.ui.game.main.racing.addRacing
import com.kdg.genius.ui.game.main.zombie.addZombie
import com.kdg.genius.ui.game.sub.bnw.addBnw
import com.kdg.genius.ui.game.sub.same_number.addSameNumber
import com.kdg.genius.ui.select.addSelect

@Composable
fun SetupNavGraph() {
    val navController = LocalNavController.current
    NavHost(
        navController = navController,
        startDestination = Routes.Select.route,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        addSelect()
        addZombie()
        addSameNumber()
        addRacing()
        addBnw()
    }
}