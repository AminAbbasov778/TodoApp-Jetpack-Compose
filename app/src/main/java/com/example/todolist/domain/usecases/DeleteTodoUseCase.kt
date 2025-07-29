package com.example.todolist.domain.usecases

import com.example.todolist.domain.interfaces.DatabaseRepository
import com.example.todolist.domain.model.TodoModel
import com.example.todolist.presentation.models.TodoUi
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(private val repository: DatabaseRepository) {
    suspend operator fun invoke(todoModel: TodoModel)= repository.delete(todoModel)
}