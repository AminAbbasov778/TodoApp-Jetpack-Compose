package com.example.todolist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.todolist.domain.usecases.FirstLaunchCompletedUseCase
import com.example.todolist.domain.usecases.IsFirstLaunchUseCase
import com.example.todolist.presentation.intents.ActivityIntent
import com.example.todolist.presentation.intents.OnboardingIntent
import com.example.todolist.presentation.states.OnboardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel  @Inject constructor(val isFirstLaunchUseCase: IsFirstLaunchUseCase,val firstLaunchCompletedUseCase: FirstLaunchCompletedUseCase) : ViewModel(){
    private val _state = MutableStateFlow(OnboardingState())
    val state get() =  _state.asStateFlow()

    fun processIntent(intent: ActivityIntent) {
        _state.value = _state.value.copy(isFirstLaunch = null)
        when (intent) {
            is ActivityIntent.CheckFirstLaunch -> {
                val firstLaunch = isFirstLaunchUseCase()
                _state.value = _state.value.copy(isFirstLaunch = firstLaunch)
            }

        }
    }
}