package com.example.todolist.di

import android.content.Context
import androidx.room.Room
import com.example.todolist.data.local.TodoDao
import com.example.todolist.data.local.TodoDatabase
import com.example.todolist.data.repositories.DatabaseRepositoryImpl
import com.example.todolist.domain.interfaces.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase  =   Room.databaseBuilder(context, TodoDatabase::class.java, "todo_database").build()



}