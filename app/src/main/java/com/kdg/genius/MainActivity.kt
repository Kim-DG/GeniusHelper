package com.kdg.genius

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.kdg.genius.ui.theme.GeniusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            GeniusTheme {
                CompositionLocalProvider(
                    LocalNavController provides navController
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                        Box {
                            SetupNavGraph()
                        }
                    }
                }
            }
        }
    }
}