package com.example.todolist.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.R
import com.example.todolist.presentation.intents.ToDoIntent
import com.example.todolist.presentation.models.TodoUi
import com.example.todolist.presentation.viewmodels.TodoViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen(paddingValues: PaddingValues, viewModel: TodoViewModel = hiltViewModel()) {
    val tabTitles = listOf<String>("ALL", "ACTIVE", "COMPLETED")
    val pagerState = rememberPagerState(pageCount = { tabTitles.size })
    val scope = rememberCoroutineScope()

    val state by viewModel.state.collectAsState()




    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Text(
            text = "Today",
            modifier = Modifier.padding(start = 38.dp, top = 56.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            color = Color.Black
        )

        LazyRow(
            modifier = Modifier
                .padding(top = 32.dp, start = 22.dp, end = 22.dp)
                .fillMaxWidth()
        ) {
            items(tabTitles) {
                Box(
                    modifier = Modifier
                        .width(125.dp)
                        .height(35.dp)
                        .clickable {

                            scope.launch { pagerState.animateScrollToPage(tabTitles.indexOf(it)) }
                        }
                        .background(
                            color = colorResource(
                                if (pagerState.currentPage == tabTitles.indexOf(it)) R.color.btn_brown else R.color.light
                            ), shape = RoundedCornerShape(8.dp)
                        ), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(if (pagerState.currentPage == tabTitles.indexOf(it)) R.color.black else R.color.disable)
                    )

                }
                Spacer(modifier = Modifier.width(12.dp))
            }


        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(top = 17.dp, end = 22.dp, start = 22.dp)
                .fillMaxWidth()
                .weight(1f)
        ) {
             when (it) {
                0 -> AllTodosScreen()
                1 -> ActiveTodosScreen()
                2 -> CompletedTodosScreen()
                else -> AllTodosScreen()

            }

        }



        Row(
            modifier = Modifier
                .padding(bottom = 26.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = state.task,
                onValueChange = { viewModel.onIntent(ToDoIntent.onValueChange(it)) },


                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedContainerColor = colorResource(R.color.light),
                    unfocusedContainerColor = colorResource(R.color.light),
                    disabledContainerColor = colorResource(R.color.light),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                ),

                placeholder = {
                    Text(
                        text = "Write a task...",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                },

                modifier = Modifier
                    .padding(start = 22.dp)
                    .weight(1f)
                    .height(56.dp)
                    .background(
                        color = colorResource(R.color.light),
                        shape = RoundedCornerShape(12.dp)
                    ), shape = RoundedCornerShape(12.dp)
            )

            Button(
                onClick = { viewModel.onIntent(ToDoIntent.upsertTodo(TodoUi(state.task, false))) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(R.color.white),
                    containerColor = colorResource(R.color.btn_dark)
                ),
                modifier = Modifier
                    .padding(start = 12.dp, end = 22.dp)
                    .wrapContentWidth()
                    .height(56.dp)
                    .background(
                        color = colorResource(R.color.btn_dark),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Text(text = "Add", fontSize = 18.sp, fontWeight = FontWeight.Medium)
            }


        }


    }

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    HomeScreen(paddingValues = PaddingValues())

}