package com.example.todolist.presentation
import androidx.compose.runtime.getValue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.R
import com.example.todolist.domain.usecases.IsFirstLaunchUseCase
import com.example.todolist.presentation.components.TodoNavHost
import com.example.todolist.presentation.intents.ActivityIntent
import com.example.todolist.presentation.intents.OnboardingIntent
import com.example.todolist.presentation.models.Screen
import com.example.todolist.presentation.screens.HomeScreen
import com.example.todolist.presentation.screens.OnboardingScreen
import com.example.todolist.presentation.viewmodels.ActivityViewModel
import com.example.todolist.presentation.viewmodels.OnboardingViewModel
import com.example.todolist.ui.theme.ToDoListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ToDoListTheme {
                val viewModel: ActivityViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(Unit) {
                    viewModel.processIntent(ActivityIntent.CheckFirstLaunch)
                }


                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        when (state.isFirstLaunch) {
                            null -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(color = colorResource(id = R.color.btn_dark))
                                }
                            }

                            true -> {
                                TodoNavHost(
                                    paddingValues = innerPadding,
                                    startDestination = Screen.Onboarding.route
                                )
                            }

                            false -> {
                                TodoNavHost(
                                    paddingValues = innerPadding,
                                    startDestination = Screen.Home.route
                                )
                            }
                        }
                    }


            }
        }
    }
}

