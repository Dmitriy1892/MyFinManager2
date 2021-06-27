package com.coldfier.myfinmanager2

import androidx.lifecycle.MutableLiveData
import com.coldfier.myfinmanager2.models.Card

fun <T> MutableLiveData<List<T>>.addCard(card: T) {
    val buffer = this.value?.toMutableList() ?: mutableListOf()
    buffer.add(card)
    this.value = buffer
}

fun <T> MutableLiveData<List<T>>.deleteCard(card: T) {
    val buffer = this.value?.toMutableList() ?: mutableListOf()
    buffer.remove(card)
    this.value = buffer
}

fun MutableLiveData<List<Card>>.updateCard(card: Card) {
    val buffer = this.value?.toMutableList() ?: mutableListOf()
    buffer.mapIndexed { index, cardInList ->
        if (cardInList.id == card.id) buffer[index] = card
    }
    this.value = buffer
}