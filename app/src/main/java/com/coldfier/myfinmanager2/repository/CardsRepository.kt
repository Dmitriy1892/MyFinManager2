package com.coldfier.myfinmanager2.repository

import android.content.Context
import com.coldfier.myfinmanager2.models.Card
import com.coldfier.myfinmanager2.repository.room.CardsDatabase

class CardsRepository private constructor(context: Context) {

    private val roomDao = CardsDatabase.getInstance(context).cardsDao

    suspend fun addNewCard(card: Card) {
        roomDao.addNewCard(card)
    }

    suspend fun updateCard(card: Card) {
        roomDao.updateCard(card)
    }

    suspend fun getCardsList(): List<Card>? {
        return roomDao.getAllCards()
    }

    suspend fun deleteCard(card: Card) {
        roomDao.deleteCard(card.id)
    }

    companion object {
        private var REPO_INSTANCE: CardsRepository? = null

        fun getInstance(context: Context): CardsRepository {
            return REPO_INSTANCE ?: run {
                REPO_INSTANCE = CardsRepository(context)
                REPO_INSTANCE!!
            }
        }
    }
}