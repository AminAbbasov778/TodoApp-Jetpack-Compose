package com.example.todolist.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.todolist.domain.usecases.FirstLaunchCompletedUseCase
import com.example.todolist.domain.usecases.IsFirstLaunchUseCase
import com.example.todolist.presentation.intents.OnboardingIntent
import com.example.todolist.presentation.states.OnboardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val isFirstLaunchUseCase: IsFirstLaunchUseCase,private val firstLaunchCompletedUseCase: FirstLaunchCompletedUseCase) : ViewModel(){
    private val _state = MutableStateFlow(OnboardingState())
    val state get() =  _state.asStateFlow()

    fun processIntent(intent: OnboardingIntent) {
        when (intent) {

            is OnboardingIntent.ContinueClicked -> {
                firstLaunchCompletedUseCase(false)
                _state.value = _state.value.copy(isFirstLaunch = false)
            }
        }
    }
}