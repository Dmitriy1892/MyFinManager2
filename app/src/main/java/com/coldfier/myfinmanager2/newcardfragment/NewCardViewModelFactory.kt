package com.coldfier.myfinmanager2.newcardfragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewCardViewModelFactory(private val application: Application): ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewCardViewModel::class.java)) {
            return NewCardViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}