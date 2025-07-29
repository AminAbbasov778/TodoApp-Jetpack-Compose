package com.example.todolist.presentation.intents

sealed class ActivityIntent {
    object CheckFirstLaunch : ActivityIntent()
}