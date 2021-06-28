package com.coldfier.myfinmanager2.transactionsfragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class TransactionsVewModelFactory(private val initCardId: Long, private val application: Application): ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionsViewModel::class.java)) {
            return TransactionsViewModel(initCardId, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}