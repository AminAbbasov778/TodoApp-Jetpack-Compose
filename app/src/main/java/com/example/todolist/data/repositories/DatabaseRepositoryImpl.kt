package com.example.todolist.data.repositories

import com.example.todolist.data.local.TodoDatabase
import com.example.todolist.data.mappers.toData
import com.example.todolist.data.mappers.toDomain
import com.example.todolist.domain.interfaces.DatabaseRepository
import com.example.todolist.domain.model.TodoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatabaseRepositoryImpl(private val database: TodoDatabase) : DatabaseRepository {

    override suspend fun upsert(todo: TodoModel): Result<Unit> {
        return try {
            database.todoDao().upsert(todo.toData())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)

        }
    }

    override  fun getTodos(): Result<Flow<List<TodoModel>>> {
        return try {
            val todos = database.todoDao().getTodos()
            Result.success(todos.map { it.map { it.toDomain() } })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun delete(todo: TodoModel): Result<Unit> {
        return try {
            database.todoDao().delete(todo.toData())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}