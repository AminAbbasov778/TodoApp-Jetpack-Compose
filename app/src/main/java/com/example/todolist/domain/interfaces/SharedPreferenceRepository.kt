package com.example.todolist.domain.interfaces

interface SharedPreferenceRepository {
    fun firstLaunchCompleted(value : Boolean)
    fun isFirstLaunch() : Boolean
}