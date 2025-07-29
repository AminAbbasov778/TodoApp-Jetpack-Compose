package com.example.todolist.domain.usecases

import com.example.todolist.data.local.TodoEntity
import com.example.todolist.domain.interfaces.DatabaseRepository
import com.example.todolist.domain.model.TodoModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(val todoRepository: DatabaseRepository)  {
     operator fun invoke() = todoRepository.getTodos().map { list ->
          list.map {
               it.sortedWith(
                    compareBy<TodoModel> { it.isCompleted }
                         .thenByDescending { it.id }
               )
          }


     }
}
