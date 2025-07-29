package com.example.todolist.domain.interfaces

import com.example.todolist.domain.model.TodoModel
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
     fun getTodos():Result<Flow<List<TodoModel>>>
    suspend fun upsert(todo: TodoModel) : Result<Unit>
    suspend fun delete(todo: TodoModel) : Result<Unit>
}