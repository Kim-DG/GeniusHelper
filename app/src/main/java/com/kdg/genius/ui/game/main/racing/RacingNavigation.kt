package com.kdg.genius.ui.game.main.racing

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kdg.genius.LocalNavController
import com.kdg.genius.Routes
import com.kdg.genius.ui.game.main.racing.intent.RacingScreenState
import com.kdg.genius.ui.game.main.racing.intent.RacingSideEffect
import com.kdg.genius.ui.game.main.racing.screen.GameScreen
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addRacing() {
    composable(route = Routes.Racing.route) {
        val navController = LocalNavController.current
        val viewModel: RacingViewModel = viewModel()
        val state = viewModel.container.stateFlow.collectAsState().value
        val context = LocalContext.current

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is RacingSideEffect.NavigateTo -> {
                    navController.navigate(sideEffect.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }

        when (state.screenState) {
            RacingScreenState.GameScreen -> {
                GameScreen {
                    viewModel.sendEvent(it)
                }
            }
        }
    }
}