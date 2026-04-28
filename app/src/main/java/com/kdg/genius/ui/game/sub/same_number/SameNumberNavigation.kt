package com.kdg.genius.ui.game.sub.same_number

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kdg.genius.LocalNavController
import com.kdg.genius.Routes
import com.kdg.genius.ui.game.sub.same_number.intent.SameNumberScreenState
import com.kdg.genius.ui.game.sub.same_number.intent.SameNumberSideEffect
import com.kdg.genius.ui.game.sub.same_number.screen.GameScreen
import com.kdg.genius.ui.game.sub.same_number.screen.InitScreen
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addSameNumber() {
    composable(route = Routes.SameNumber.route) {
        val navController = LocalNavController.current
        val viewModel: SameNumberViewModel = viewModel()
        val state = viewModel.container.stateFlow.collectAsState().value
        val context = LocalContext.current

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is SameNumberSideEffect.NavigateTo -> {
                    navController.navigate(sideEffect.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }

        when (state.screenState) {
            SameNumberScreenState.InitScreen -> {
                InitScreen {
                    viewModel.sendEvent(it)
                }
            }

            SameNumberScreenState.GameScreen -> {
                GameScreen (state){
                    viewModel.sendEvent(it)
                }
            }
        }

        BackHandler { }
    }
}