package com.kdg.genius.ui.select

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kdg.genius.LocalNavController
import com.kdg.genius.Routes
import com.kdg.genius.ui.select.intent.SelectScreenState
import com.kdg.genius.ui.select.intent.SelectSideEffect
import com.kdg.genius.ui.select.screen.MainMatchScreen
import com.kdg.genius.ui.select.screen.SelectScreen
import com.kdg.genius.ui.select.screen.SubMatchScreen
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addSelect() {
    composable(route = Routes.Select.route) {
        val navController = LocalNavController.current
        val viewModel: SelectViewModel = viewModel()
        val state = viewModel.container.stateFlow.collectAsState().value
        val context = LocalContext.current

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is SelectSideEffect.NavigateTo -> {
                    navController.navigate(sideEffect.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }

        when (state.screenState) {
            SelectScreenState.SelectScreen -> {
                SelectScreen {
                    viewModel.sendEvent(it)
                }
            }

            SelectScreenState.MainMatchScreen -> {
                MainMatchScreen {
                    viewModel.sendEvent(it)
                }
            }

            SelectScreenState.SubMatchScreen -> {
                SubMatchScreen {
                    viewModel.sendEvent(it)
                }
            }
        }
    }
}