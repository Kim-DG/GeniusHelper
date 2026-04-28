package com.kdg.genius.ui.game.main.zombie

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kdg.genius.LocalNavController
import com.kdg.genius.Routes
import com.kdg.genius.ui.game.main.zombie.dialog.OriginalZombieDialog
import com.kdg.genius.ui.game.main.zombie.dialog.TouchDialog
import com.kdg.genius.ui.game.main.zombie.dialog.UseCureDialog
import com.kdg.genius.ui.game.main.zombie.intent.ZombieDialogState
import com.kdg.genius.ui.game.main.zombie.intent.ZombieScreenState
import com.kdg.genius.ui.game.main.zombie.intent.ZombieSideEffect
import com.kdg.genius.ui.game.main.zombie.screen.GameScreen
import com.kdg.genius.ui.game.main.zombie.screen.InitScreen
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addZombie() {
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

        BackHandler { }
    }
}