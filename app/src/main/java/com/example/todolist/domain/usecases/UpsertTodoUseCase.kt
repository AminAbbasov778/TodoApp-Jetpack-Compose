package com.example.todolist.domain.usecases

import com.example.todolist.domain.interfaces.DatabaseRepository
import com.example.todolist.domain.model.TodoModel
import javax.inject.Inject

class UpsertTodoUseCase @Inject constructor(private val todoRepository: DatabaseRepository)  {
    suspend operator fun invoke(todo: TodoModel) = todoRepository.upsert(todo)


}