package com.example.todolist.presentation.states

import com.example.todolist.presentation.models.TodoUi

data class ToDoState(val allTodoList : List<TodoUi> = emptyList(),val activeTodoList : List<TodoUi> = emptyList(),val completedTodoList : List<TodoUi> = emptyList(), val error : String? = null, val isLoading : Boolean = false, val task : String = "") {
}