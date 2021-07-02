package com.coldfier.myfinmanager2.transactionsfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coldfier.myfinmanager2.models.CardWithTransactions
import com.coldfier.myfinmanager2.repository.CardsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionsViewModel(private val initCardId: Long, application: Application) : AndroidViewModel(application) {

    private val app = application

    private var _cardsWithTransactionsList = MutableLiveData<List<CardWithTransactions>>()
    val cardsWithTransactionsList: LiveData<List<CardWithTransactions>>
        get() = _cardsWithTransactionsList

    private var _currentViewPagerPosition = MutableLiveData<Int>()
    val currentViewPagerPosition: LiveData<Int>
        get() = _currentViewPagerPosition

    init {
        getCardsWithTransactionsList()
    }

    fun getCardsWithTransactionsList() {
        viewModelScope.launch(Dispatchers.IO) {

            _cardsWithTransactionsList.postValue(CardsRepository.getInstance(app.applicationContext).getCardsList())

            _cardsWithTransactionsList.value?.forEachIndexed { index, cardWithTransactions ->
                if (cardWithTransactions.card.cardId == initCardId) {
                    _currentViewPagerPosition.postValue(index)
                }
            }
        }
    }
}