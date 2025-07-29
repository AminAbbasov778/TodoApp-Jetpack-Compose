package com.example.todolist.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.R
import com.example.todolist.presentation.components.TodoItem
import com.example.todolist.presentation.intents.ToDoIntent
import com.example.todolist.presentation.states.ToDoState
import com.example.todolist.presentation.viewmodels.TodoViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AllTodosScreen(viewModel: TodoViewModel = hiltViewModel()) {


    val todos = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onIntent(ToDoIntent.getAllTodos)
    }
    when {
        todos.value.isLoading -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = colorResource(id = R.color.btn_dark))
            }
        }
            todos.value.allTodoList.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "There is no any task",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                }
            }
            else ->{
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    items(todos.value.allTodoList, key = { it.id }) { items ->
                        TodoItem(
                            items,
                            onDeleteClick = { viewModel.onIntent(ToDoIntent.deleteTodo(items)) },
                            onCheckedChange = {
                                viewModel.onIntent(
                                    ToDoIntent.upsertTodo(
                                        items.copy(isCompleted = !items.isCompleted)
                                    )
                                )
                            })


                    }

                }
            }
        }
    }


