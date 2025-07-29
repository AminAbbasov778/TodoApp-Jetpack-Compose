package com.example.todolist.presentation.mappers

import com.example.todolist.domain.model.TodoModel
import com.example.todolist.presentation.models.TodoUi

fun TodoModel.toUi(): TodoUi{
    return TodoUi(title,isCompleted,id)
}

fun TodoUi.toDomain(): TodoModel{
    return TodoModel(id,title,isCompleted)
}