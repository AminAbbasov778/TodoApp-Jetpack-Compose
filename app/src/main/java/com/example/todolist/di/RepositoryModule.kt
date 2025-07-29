package com.example.todolist.di

import android.content.SharedPreferences
import com.example.todolist.data.local.TodoDatabase
import com.example.todolist.data.repositories.DatabaseRepositoryImpl
import com.example.todolist.data.repositories.SharedPreferenceRepositoryImpl
import com.example.todolist.domain.interfaces.DatabaseRepository
import com.example.todolist.domain.interfaces.SharedPreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDatabaseRepository(database: TodoDatabase): DatabaseRepository =
        DatabaseRepositoryImpl(database)

    @Provides
    @Singleton
    fun provideSharedPreferencesRepository(sharedPreferences: SharedPreferences): SharedPreferenceRepository =
        SharedPreferenceRepositoryImpl(sharedPreferences)

}