package com.example.todolist.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.presentation.models.Screen
import com.example.todolist.presentation.screens.HomeScreen
import com.example.todolist.presentation.screens.OnboardingScreen

@Composable
fun TodoNavHost(
    paddingValues: PaddingValues,
    navController: NavHostController = rememberNavController(),startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        builder = {
            composable(Screen.Onboarding.route) {
                OnboardingScreen(paddingValues = paddingValues,
                    onContinue = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Home.route) {
                HomeScreen(paddingValues = paddingValues)
            }
        }
    )
}