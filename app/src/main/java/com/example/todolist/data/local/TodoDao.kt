package com.example.todolist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.todolist.data.local.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todos")
     fun getTodos():  Flow<List<TodoEntity>>

    @Upsert
    suspend fun upsert(todo: TodoEntity)

    @Delete
    suspend fun delete(todo: TodoEntity)

}