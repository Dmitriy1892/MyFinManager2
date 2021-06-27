package com.coldfier.myfinmanager2.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.coldfier.myfinmanager2.models.Card

@Dao
interface CardsDao {

    @Insert
    fun addNewCard(card: Card)

    @Update
    fun updateCard(card: Card)

    @Query("SELECT * FROM cards_table WHERE id = :cardId ")
    fun getCard(cardId: String): Card?

    @Query("SELECT * FROM cards_table")
    fun getAllCards(): List<Card>?

    @Query("DELETE FROM cards_table WHERE id = :cardId")
    fun deleteCard(cardId: String)

}