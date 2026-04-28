package com.kdg.genius.ui.game.sub.bnw

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kdg.genius.LocalNavController
import com.kdg.genius.Routes
import com.kdg.genius.ui.game.sub.bnw.intent.BnwScreenState
import com.kdg.genius.ui.game.sub.bnw.intent.BnwSideEffect
import com.kdg.genius.ui.game.sub.bnw.screen.GameScreen
import com.kdg.genius.ui.game.sub.bnw.screen.InitScreen
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addBnw() {
    composable(route = Routes.Bnw.route) {
        val navController = LocalNavController.current
        val viewModel: BnwViewModel = viewModel()
        val state = viewModel.container.stateFlow.collectAsState().value
        val context = LocalContext.current

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is BnwSideEffect.NavigateTo -> {
                    navController.navigate(sideEffect.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }

        when (state.screenState) {
            BnwScreenState.InitScreen -> {
                InitScreen {
                    viewModel.sendEvent(it)
                }
            }

            BnwScreenState.GameScreen -> {
                GameScreen (state){
                    viewModel.sendEvent(it)
                }
            }
        }

        BackHandler { }
    }
}