package com.coldfier.myfinmanager2.cardsfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coldfier.myfinmanager2.addCard
import com.coldfier.myfinmanager2.deleteCard
import com.coldfier.myfinmanager2.models.Card
import com.coldfier.myfinmanager2.repository.CardsRepository
import com.coldfier.myfinmanager2.updateCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardsViewModel(application: Application) : AndroidViewModel(application) {

    private val app = application

    private var _cardsList = MutableLiveData<List<Card>>()
    val cardsList: LiveData<List<Card>>
        get() = _cardsList

    init {
        getCardsList()
    }

    fun getCardsList() {
        viewModelScope.launch(Dispatchers.IO) {
            val instance = CardsRepository.getInstance(app.applicationContext)
            val list = instance.getCardsList()
            val list2 = list?.map { item -> item.card }

            if (list2 != null) {
                if (list2.isNotEmpty()) {
                    _cardsList.postValue(list2!!)
                }
            }
        }
    }

    fun addNewCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            val repository = CardsRepository.getInstance(app.applicationContext)
            repository.addNewCard(card)
            _cardsList.addCard(card)
        }
    }

    fun updateCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            val repository = CardsRepository.getInstance(app.applicationContext)
            repository.updateCard(card)
            _cardsList.updateCard(card)
        }
    }

    fun deleteCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            val repository = CardsRepository.getInstance(app.applicationContext)
            repository.deleteCard(card)
            _cardsList.deleteCard(card)
        }
    }

}