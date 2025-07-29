package com.example.todolist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity( val title : String,val isCompleted : Boolean,@PrimaryKey(autoGenerate = true) val id : Int = 0) {
}