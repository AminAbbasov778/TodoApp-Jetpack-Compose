package com.example.todolist.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.R
import com.example.todolist.presentation.components.TodoItem
import com.example.todolist.presentation.intents.ToDoIntent
import com.example.todolist.presentation.models.TodoUi
import com.example.todolist.presentation.viewmodels.TodoViewModel


@Composable
fun CompletedTodosScreen(viewModel : TodoViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.onIntent(ToDoIntent.getCompletedTodos)
    }
    val todos = viewModel.state.collectAsState()
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
            items(todos.value.completedTodoList) { item ->
                TodoItem(
                    item,
                    onDeleteClick = { viewModel.onIntent(ToDoIntent.deleteTodo(item)) },
                    onCheckedChange = {
                        viewModel.onIntent(
                            ToDoIntent.upsertTodo(
                                item.copy(isCompleted = !item.isCompleted)
                            )
                        )
                    })


            }

        }}
    }

}