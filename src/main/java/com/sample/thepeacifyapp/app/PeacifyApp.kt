package com.sample.thepeacifyapp.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sample.thepeacifyapp.navigation.PeacifyRouter
import com.sample.thepeacifyapp.navigation.Screen
import com.sample.thepeacifyapp.screens.HomeScreen
import com.sample.thepeacifyapp.screens.LoginScreen
import com.sample.thepeacifyapp.screens.SignUpScreen

@Composable
fun PeacifyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Crossfade(targetState = PeacifyRouter.currentScreen) {
            currentState ->
            when(currentState.value){
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }
                is Screen.LoginScreen -> {
                    LoginScreen()
                }
                is Screen.HomeScreen -> {
                    HomeScreen()
                }
            }
        }
    }
}