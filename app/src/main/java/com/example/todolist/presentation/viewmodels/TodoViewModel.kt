package com.example.todolist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.usecases.DeleteTodoUseCase
import com.example.todolist.domain.usecases.GetActiveTodosUseCase
import com.example.todolist.domain.usecases.GetCompletedTodosUseCase
import com.example.todolist.domain.usecases.GetTodosUseCase
import com.example.todolist.domain.usecases.UpsertTodoUseCase
import com.example.todolist.presentation.intents.ToDoIntent
import com.example.todolist.presentation.mappers.toDomain
import com.example.todolist.presentation.mappers.toUi
import com.example.todolist.presentation.models.TodoUi
import com.example.todolist.presentation.states.ToDoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val upsertTodoUseCase: UpsertTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val getTodosUseCase: GetTodosUseCase,
    private val getActiveTodosUseCase: GetActiveTodosUseCase,
    private val getCompletedTodosUseCase: GetCompletedTodosUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ToDoState())
    val state: StateFlow<ToDoState> get() = _state.asStateFlow()


    fun onIntent(intent: ToDoIntent) {
        when (intent) {
            is ToDoIntent.upsertTodo -> upsertTodo(intent.todo)


            is ToDoIntent.deleteTodo -> deleteTodo(intent.todo)


            is ToDoIntent.getAllTodos ->getAllTodos()

            is ToDoIntent.getActiveTodos -> getActiveTodos()
            is ToDoIntent.getCompletedTodos -> getCompletedTodos()
            is ToDoIntent.onValueChange -> {onValueChange(intent.value)}

            else -> getAllTodos()
        }

    }

    fun upsertTodo(todoUi: TodoUi) {
        viewModelScope.launch(Dispatchers.IO) {
            upsertTodoUseCase(todoUi.toDomain())
             _state.value = _state.value.copy(task = "")
        }

    }

    fun onValueChange(value: String) {
        _state.value = _state.value.copy(task = value)
    }

    fun deleteTodo(todoUi: TodoUi) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTodoUseCase(todoUi.toDomain())
        }
    }

    fun getAllTodos() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val result = getTodosUseCase()
            if (result.isSuccess) {
                result.getOrNull()?.collect { flow ->
                    _state.value = _state.value.copy(
                        allTodoList = flow.map { it.toUi() },
                        isLoading = false
                    )
                }
            } else {
                _state.value = _state.value.copy(isLoading = false, error = result.exceptionOrNull()?.message)
            }
        }
    }


    fun getActiveTodos(){
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = getActiveTodosUseCase()
            if (result.isSuccess) {
                result.getOrNull()?.collect { flow ->
                    _state.value = _state.value.copy(activeTodoList = flow.map { it.toUi() },isLoading = false)
                }
            }else {
                _state.value = _state.value.copy(isLoading = false, error = result.exceptionOrNull()?.message)
            }
        }

    }
    fun getCompletedTodos(){
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = getCompletedTodosUseCase()
            if (result.isSuccess) {
                result.getOrNull()?.collect { flow ->
                    _state.value = _state.value.copy(completedTodoList = flow.map { it.toUi() },isLoading = false)
                }
            }else {
                _state.value = _state.value.copy(isLoading = false, error = result.exceptionOrNull()?.message)
            }
        }
    }



}