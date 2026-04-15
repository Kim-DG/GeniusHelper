package com.kdg.genius.ui.game.racing

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kdg.genius.LocalNavController
import com.kdg.genius.Routes
import com.kdg.genius.ui.game.zombie.ZombieViewModel
import com.kdg.genius.ui.game.zombie.dialog.OriginalZombieDialog
import com.kdg.genius.ui.game.zombie.dialog.TouchDialog
import com.kdg.genius.ui.game.zombie.dialog.UseCureDialog
import com.kdg.genius.ui.game.zombie.intent.ZombieDialogState
import com.kdg.genius.ui.game.zombie.intent.ZombieEvent
import com.kdg.genius.ui.game.zombie.intent.ZombieScreenState
import com.kdg.genius.ui.game.zombie.intent.ZombieSideEffect
import com.kdg.genius.ui.game.zombie.screen.GameScreen
import com.kdg.genius.ui.game.zombie.screen.InitScreen
import com.kdg.genius.ui.select.intent.SelectScreenState
import com.kdg.genius.ui.select.intent.SelectSideEffect
import com.kdg.genius.ui.select.screen.MainMatchScreen
import com.kdg.genius.ui.select.screen.SelectScreen
import com.kdg.genius.ui.select.screen.SubMatchScreen
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addRacing() {
    composable(route = Routes.Zombie.route) {
        val navController = LocalNavController.current
        val viewModel: ZombieViewModel = viewModel()
        val state = viewModel.container.stateFlow.collectAsState().value
        val context = LocalContext.current

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is ZombieSideEffect.NavigateTo -> {
                    navController.navigate(sideEffect.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }

        when (state.screenState) {
            ZombieScreenState.InitScreen -> {
                InitScreen {
                    viewModel.sendEvent(it)
                }
            }

            ZombieScreenState.GameScreen -> {
                GameScreen(state) {
                    viewModel.sendEvent(it)
                }
            }
        }

        when (state.dialogState) {
            ZombieDialogState.Touch -> {
                TouchDialog(state) {
                    viewModel.sendEvent(it)
                }
            }

            ZombieDialogState.OriginalZombie -> {
                OriginalZombieDialog(state) {
                    viewModel.sendEvent(it)
                }
            }

            ZombieDialogState.UseCure -> {
                UseCureDialog(state) {
                    viewModel.sendEvent(it)
                }
            }

            else -> Unit
        }
    }
}