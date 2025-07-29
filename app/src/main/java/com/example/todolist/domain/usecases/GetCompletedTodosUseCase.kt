package com.example.todolist.domain.usecases

import com.example.todolist.domain.interfaces.DatabaseRepository
import com.example.todolist.presentation.models.TodoUi
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCompletedTodosUseCase @Inject constructor(private val databaseRepository: DatabaseRepository){
    operator fun invoke() = databaseRepository.getTodos().map { it.map {it.filter { it.isCompleted == true }.sortedByDescending { it.id }  } }

}