package com.coldfier.myfinmanager2.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.coldfier.myfinmanager2.models.Card
import com.coldfier.myfinmanager2.models.Transaction
import com.coldfier.myfinmanager2.models.CardWithTransactions

@Dao
interface CardsDao {

    @Insert(entity = Card::class)
    suspend fun addNewCard(card: Card)

    @Update(entity = Card::class)
    suspend fun updateCard(card: Card)

    @Query("SELECT * FROM cards_table WHERE cardId = :cardId ")
    suspend fun getCard(cardId: String): CardWithTransactions

    @androidx.room.Transaction
    @Query("SELECT * FROM cards_table")
    suspend fun getAllCards(): List<CardWithTransactions>?

    @Query("DELETE FROM cards_table WHERE cardId = :cardId")
    suspend fun deleteCard(cardId: Long)

    @Query("DELETE FROM cards_table")
    suspend fun clearCards()

    @Insert(entity = Transaction::class)
    suspend fun addNewTransaction(transaction: Transaction)

    @Update(entity = Transaction::class)
    suspend fun updateTransaction(transaction: Transaction)

    @Query("SELECT * FROM transactions_table WHERE transactionId = :transactionId")
    suspend fun getTransaction(transactionId: Long)

    @Query("DELETE FROM transactions_table WHERE transactionId = :transactionId")
    suspend fun deleteTransaction(transactionId: Long)

    @Query("DELETE FROM transactions_table WHERE cardCreatorId = :cardId")
    suspend fun deleteCardTransactions(cardId: Long)

    @Query("DELETE FROM transactions_table")
    suspend fun clearTransactions()

}