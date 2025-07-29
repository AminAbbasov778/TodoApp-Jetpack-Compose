package com.example.todolist.presentation.screens
import androidx.compose.runtime.getValue

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.R
import com.example.todolist.presentation.intents.OnboardingIntent
import com.example.todolist.presentation.viewmodels.OnboardingViewModel
import com.example.todolist.presentation.viewmodels.TodoViewModel

@Composable
fun OnboardingScreen(paddingValues: PaddingValues,viewModel: OnboardingViewModel = hiltViewModel(),onContinue: () -> Unit ) {

    val state by viewModel.state.collectAsState()



    val recommendedList = listOf<String>(
        "Exercise",
        "Plan meals",
        "Stretch for 15 mins",
        "Read books",
        "Water plants",
        "Review goals before",
        "Meditate",
        "Journal"
    )
    val rows = 3
    val columns = (recommendedList.size + rows - 1) / rows

      Column(
          modifier = Modifier
              .padding(paddingValues)
              .fillMaxSize()
              .background(color = Color.White)
      ) {
          Box(modifier = Modifier.fillMaxWidth()) {

              Image(
                  painter = painterResource(R.drawable.onbordimgimg),
                  contentDescription = null,
                  modifier = Modifier.size(835.dp)
              )
              Column(modifier = Modifier.fillMaxWidth()) {
                  Text(
                      text = "Pick some new habits to get started",
                      fontWeight = FontWeight.SemiBold,
                      color = Color.Black,
                      fontSize = 36.sp,
                      modifier = Modifier
                          .padding(top = 256.dp, start = 22.dp, end = 22.dp)
                          .width(386.dp),
                      lineHeight = 40.sp
                  )
                  Text(
                      text = "RECOMMENDED",
                      color = colorResource(R.color.brown),
                      fontSize = 12.sp,
                      fontWeight = FontWeight.SemiBold,
                      modifier = Modifier.padding(start = 32.dp, top = 241.dp)
                  )

                  Box(modifier = Modifier.fillMaxWidth()) {
                      Column(
                          verticalArrangement = Arrangement.spacedBy(12.dp),
                          modifier = Modifier
                              .padding(start = 20.dp)
                              .padding(top = 24.dp)
                      ) {
                          for (rowIndex in 0 until rows) {
                              Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                  for (colIndex in 0 until columns) {
                                      val itemIndex = colIndex * rows + rowIndex
                                      if (itemIndex < recommendedList.size) {
                                          Box(
                                              modifier = Modifier
                                                  .wrapContentWidth()
                                                  .background(
                                                      color = colorResource(R.color.light).copy(alpha = 0.4f),
                                                      shape = RoundedCornerShape(12.dp)
                                                  )
                                                  .padding(vertical = 12.dp)
                                                  .padding(horizontal = 20.dp)
                                          ) {
                                              Text(
                                                  text = recommendedList[itemIndex],
                                                  color = Color.Black,
                                                  fontSize = 17.sp,
                                                  fontWeight = FontWeight.Medium, maxLines = 1
                                              )
                                          }
                                      }
                                  }
                              }
                          }
                      }

                      Box(
                          modifier = Modifier
                              .height(180.dp)
                              .width(200.dp)
                              .align(Alignment.CenterEnd)
                              .background(
                                  brush = Brush.horizontalGradient(
                                      colors = listOf(Color.Transparent, Color.White)
                                  )
                              )
                      )
                  }

                  Button(
                      colors = ButtonDefaults.buttonColors(
                          containerColor = colorResource(R.color.btn_dark),
                          contentColor = colorResource(R.color.white),
                          disabledContainerColor = colorResource(R.color.btn_dark),
                          disabledContentColor = colorResource(R.color.white)
                      ),
                      modifier = Modifier
                          .padding(top = 34.dp)
                          .padding(horizontal = 22.dp)
                          .fillMaxWidth()
                          .height(56.dp),
                      onClick = {viewModel.processIntent(OnboardingIntent.ContinueClicked)
                          onContinue()
                      }, shape = RoundedCornerShape(12.dp)
                  ) {
                      Text(
                          text = "Continue",
                          fontSize = 18.sp,
                          fontWeight = FontWeight.Medium,

                          )
                  }


              }

          }


      }




}

