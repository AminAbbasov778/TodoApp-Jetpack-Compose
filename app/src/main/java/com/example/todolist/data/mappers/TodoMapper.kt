package com.example.todolist.data.mappers

import com.example.todolist.data.local.TodoEntity
import com.example.todolist.domain.model.TodoModel

fun TodoModel.toData(): TodoEntity {
    return TodoEntity(title, isCompleted,id)

}

fun TodoEntity.toDomain(): TodoModel{
    return TodoModel(id,title,isCompleted)
}