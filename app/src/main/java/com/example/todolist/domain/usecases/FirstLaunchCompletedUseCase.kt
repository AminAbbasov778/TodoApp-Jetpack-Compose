package com.example.todolist.domain.usecases

import com.example.todolist.domain.interfaces.SharedPreferenceRepository
import javax.inject.Inject

class FirstLaunchCompletedUseCase @Inject constructor(val repository: SharedPreferenceRepository) {
    operator fun invoke(isFirstLaunch: Boolean) = repository.firstLaunchCompleted(isFirstLaunch)


}