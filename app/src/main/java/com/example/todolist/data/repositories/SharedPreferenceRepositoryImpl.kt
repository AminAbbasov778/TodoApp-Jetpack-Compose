package com.example.todolist.data.repositories

import android.content.SharedPreferences
import android.util.Log
import com.example.todolist.Constants
import com.example.todolist.domain.interfaces.SharedPreferenceRepository
import javax.inject.Inject
import androidx.core.content.edit

class SharedPreferenceRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :SharedPreferenceRepository{
    override fun firstLaunchCompleted(value: Boolean) = sharedPreferences.edit() {
        putBoolean(
            Constants.PREF_KEY,
            value
        )
    }


    override fun isFirstLaunch(): Boolean  {
      return  sharedPreferences.getBoolean(Constants.PREF_KEY, true)
    }


}