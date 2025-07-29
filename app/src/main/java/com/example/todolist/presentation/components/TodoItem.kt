package com.example.todolist.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import com.example.todolist.presentation.models.TodoUi
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun TodoItem(
    item: TodoUi,
    onCheckedChange: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()

    val minWidth = 330.dp
    val maxWidth = LocalConfiguration.current.screenWidthDp.dp

    val swipeWidth = remember { Animatable(maxWidth.value) }



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(56.dp)
                .height(56.dp)
                .background(Color.Red, shape = RoundedCornerShape(12.dp))
                .clickable { onDeleteClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.delete),
                contentDescription = "Delete",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }


        Row(
            modifier = Modifier
                .width(swipeWidth.value.dp)
                .background(
                    color = colorResource(if (item.isCompleted) R.color.white else R.color.light),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = if (item.isCompleted) 1.dp else 0.dp,
                    color = colorResource(R.color.disable),
                    shape = RoundedCornerShape(12.dp)
                )
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            scope.launch {
                                val newWidth = (swipeWidth.value + dragAmount / density.density)
                                    .coerceIn(minWidth.value, maxWidth.value)
                                swipeWidth.snapTo(newWidth)
                            }
                        },
                        onDragEnd = {
                            scope.launch {
                                if (swipeWidth.value < maxWidth.value * 0.8f) {
                                    swipeWidth.animateTo(minWidth.value)
                                } else {
                                    swipeWidth.animateTo(maxWidth.value)
                                }
                            }
                        }
                    )
                },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = item.isCompleted,
                onCheckedChange = { onCheckedChange() },
                modifier = Modifier.padding(20.dp)
                    .border(
                        3.dp,
                        color = colorResource(R.color.disable),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(color = Color.Transparent, shape = RoundedCornerShape(8.dp))
                    .size(26.dp),
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(R.color.disable),
                    uncheckedColor = Color.Transparent,
                    checkmarkColor = Color.White
                )
            )

            Text(
                text = item.title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(if (item.isCompleted) R.color.disable else R.color.black),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f),
                style = if (item.isCompleted)
                    TextStyle(textDecoration = TextDecoration.LineThrough)
                else TextStyle.Default
            )

            Icon(
                painter = painterResource(R.drawable.todoimg),
                contentDescription = null,
                modifier = Modifier
                    .padding(20.dp)
                    .width(10.dp)
                    .height(18.dp)
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
}

