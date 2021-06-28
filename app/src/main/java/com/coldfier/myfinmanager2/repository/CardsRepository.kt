package com.coldfier.myfinmanager2.repository

import android.content.Context
import com.coldfier.myfinmanager2.models.Card
import com.coldfier.myfinmanager2.models.CardWithTransactions
import com.coldfier.myfinmanager2.models.Transaction
import com.coldfier.myfinmanager2.repository.room.CardsDatabase

class CardsRepository private constructor(context: Context) {

    private val roomDao = CardsDatabase.getInstance(context).cardsDao

    suspend fun addNewCard(card: Card) {
        roomDao.addNewCard(card)
    }

    suspend fun updateCard(card: Card) {
        roomDao.updateCard(card)
    }

    suspend fun getCardsList(): List<CardWithTransactions>? {
        return roomDao.getAllCards()
    }

    suspend fun deleteCard(card: Card) {
        roomDao.deleteCard(card.cardId)
        roomDao.deleteCardTransactions(card.cardId)
    }

    suspend fun addNewTransaction(transaction: Transaction) {
        roomDao.addNewTransaction(transaction)
    }

    suspend fun updateTransaction(transaction: Transaction) {
        roomDao.updateTransaction(transaction)
    }

    suspend fun deleteTransaction(transaction: Transaction) {
        roomDao.deleteTransaction(transaction.transactionId)
    }

    suspend fun cleanUserCache() {
        roomDao.clearCards()
        roomDao.clearTransactions()
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