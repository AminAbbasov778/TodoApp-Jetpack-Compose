package com.example.todolist.presentation.intents

import com.example.todolist.presentation.models.TodoUi

sealed class ToDoIntent{
    data class upsertTodo(val todo: TodoUi) : ToDoIntent()
    data class deleteTodo(val todo: TodoUi): ToDoIntent()
    data class onValueChange(val value : String) : ToDoIntent()
    object getAllTodos : ToDoIntent()
    object getActiveTodos : ToDoIntent()
    object getCompletedTodos : ToDoIntent()

}