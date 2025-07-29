package com.example.todolist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.data.local.TodoEntity

@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase(){
    abstract fun todoDao(): TodoDao
}