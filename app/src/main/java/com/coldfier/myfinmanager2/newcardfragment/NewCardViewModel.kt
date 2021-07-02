package com.coldfier.myfinmanager2.newcardfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.coldfier.myfinmanager2.models.Card
import com.coldfier.myfinmanager2.repository.CardsRepository
import kotlinx.coroutines.launch

class NewCardViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application

    fun createNewCard(
        cardName: String,
        cardNumber: Int,
        cardBalance: Double,
        cardCurrency: String
    ) {
        viewModelScope.launch {
            val card = Card(
                name = cardName,
                number = cardNumber,
                balance = cardBalance,
                currency = cardCurrency
            )
            CardsRepository.getInstance(app.applicationContext).addNewCard(card)
        }
    }
}